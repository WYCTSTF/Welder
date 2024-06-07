package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.BendCommissionEntity;
import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.service.BendCommissionService;
import cn.edu.lut.welder.service.ExaminationLedgerService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-18 18:49:47
 */
@Controller
@RequestMapping("/")
public class BendCommissionController {
    @Autowired
    private BendCommissionService bendCommissionService;

    @Autowired
    private ExaminationLedgerService examinationLedgerService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/bendCommission")
    public String bendCommission() {
        return "bendcommission/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("bendCommission/add")
    @RequiresPermissions("bendCommission:add")
    @ResponseBody
    public DataResult add(@RequestBody BendCommissionEntity bendCommission){
        bendCommissionService.save(bendCommission);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("bendCommission/delete")
    @RequiresPermissions("bendCommission:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        bendCommissionService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("bendCommission/update")
    @RequiresPermissions("bendCommission:update")
    @ResponseBody
    public DataResult update(@RequestBody BendCommissionEntity bendCommission){
        bendCommissionService.updateById(bendCommission);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("bendCommission/listByPage")
    @RequiresPermissions("bendCommission:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody BendCommissionEntity bendCommission){
//        LambdaQueryWrapper<BendCommissionEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(BendCommissionEntity::getId, bendCommission.getId());
//        queryWrapper.orderByDesc(BendCommissionEntity::getId);
        IPage<BendCommissionEntity> iPage = bendCommissionService.page(bendCommission.getQueryPage(), null);
        return DataResult.success(iPage);
    }


    /**
     *  author 张润岩
     * @return
     */
    @ApiOperation(value = "弯曲委托数据更新")
    @PostMapping("bendCommission/autoTransferDataToBendCommission")
    @RequiresPermissions("bendCommission:autoTransferDataToBendCommission")
    @ResponseBody
    public DataResult autoTransferDataToBendCommission(){
        try {
            // 4. 从 examination_ledger 表中查询需要写入的数据
            String sql = "SELECT forensic_projects, material, specification, code, id FROM examination_ledger";
            List<ExaminationLedgerEntity> examinationLedgerEntities = examinationLedgerService.list();

            // 5. 将数据批量写入 bend_commission 表
            for (ExaminationLedgerEntity entity : examinationLedgerEntities) {

                String forensicProjects = entity.getForensicProjects();
                String specification = entity.getSpecification();
                String originalCode = entity.getCode();
                Long examinationLedgerId = entity.getId();
                String code = "";
                // 如果 forensic_projects 包含 "F" 或 "FG"，则跳过该条记录
                if (forensicProjects != null && (forensicProjects.contains("F-") || forensicProjects.contains("FG-"))) {
                    continue;
                }
                // 提取 specification 中的 Φ 数字
                int phiIndex = specification.indexOf("Φ");
                int phiNumber = -1;
                if (phiIndex != -1) {
                    // 从"Φ"的下一个字符开始提取
                    StringBuilder phiStrBuilder = new StringBuilder();
                    for (int i = phiIndex + 1; i < specification.length(); i++) {
                        char c = specification.charAt(i);
                        // 如果字符是数字，则添加到StringBuilder中
                        if (Character.isDigit(c)) {
                            phiStrBuilder.append(c);
                        } else {
                            // 遇到非数字字符时停止提取
                            break;
                        }
                    }
                    // 尝试将提取的字符串转换为整数
                    if (phiStrBuilder.length() > 0) {
                        try {
                            phiNumber = Integer.parseInt(phiStrBuilder.toString());
                        } catch (NumberFormatException e) {
                            // 处理转换失败的情况
                            phiNumber = -1; // 选择抛出异常、记录日志等
                        }
                    }
                }

                // 如果 Φ 数字小于 76，则在 code 字段后面加上 "-1"
                if (phiNumber < 76 && phiNumber > 0) {
                    code = originalCode + "-1";
                }else if (forensicProjects != null && forensicProjects.contains("N")) {
                    code  = originalCode;
                }else {
                    code  = originalCode;
                }

                BendCommissionEntity bendCommission = new BendCommissionEntity();
                bendCommission.setMaterial(entity.getMaterial());
                bendCommission.setSpecification(entity.getSpecification());
                bendCommission.setInspectionNum(code);
                bendCommission.setExaminationLedgerID(entity.getId());

                ExaminationLedgerEntity examinationLedgerEntity = examinationLedgerService.getById(examinationLedgerId);
                if (examinationLedgerEntity != null)
                {
                    QueryWrapper<BendCommissionEntity> queryWrapper = new QueryWrapper<BendCommissionEntity>();
                    queryWrapper.eq("examination_ledger_id", examinationLedgerEntity.getId());
                    bendCommissionService.update(bendCommission, queryWrapper);
                }else{
                    // 如果记录不存在,则执行插入操作
                    bendCommissionService.save(bendCommission);
                    System.out.println("Inserted record with examination_ledger_id=" + examinationLedgerId);
                }
            }
        }catch (Exception e) {
            return DataResult.fail("弯曲委托数据更新失败！");
        }
        return DataResult.success("弯曲委托数据更新成功");
    }



    @ApiOperation(value = "弯曲报告生成")
    @GetMapping("bendCommission/generateReport")
    @RequiresPermissions("bendCommission:generateReport")
    @ResponseBody
    public void generateReport(HttpServletResponse response){
        response.addHeader("Content-Disposition", "attachment;filename=" + "test.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        /**
         * 构建excel表格
         */
        ExcelWriter excelWriter = null;
        //获取项目resources/template目录下模板的数据流
        InputStream templateInputStream = null;

        //构建第一个sheet页的数据，根据模板填充
        Map<String, Object> sheetMap = new HashMap<>();
        //与模板对应字段-值设置
        sheetMap.put("projectName", "南京华聪");
        sheetMap.put("reportNum", "PTR24-HGKS019-0001");

        try {

            templateInputStream = new
                        ClassPathResource("/template/excel/bend_report.xlsx").getInputStream();
            //ExcelWriter通过POI将值写入Excel
            excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateInputStream).build();
            // sheet-概览
            WriteSheet firstSheet = EasyExcel.writerSheet(0).build();
            //WriteSheet secondSheet = EasyExcel.writerSheet(1).build();
            //用数据去填充模板 取对应的值显示在模板对应的位置
            excelWriter.fill(sheetMap, firstSheet);
            //excelWriter.fill(sheetMap1, secondSheet);

        } catch (IOException e) {
            System.out.println("无损检测获取模板失败!");
            e.printStackTrace();
        } finally {
            //最后记得关闭流
            excelWriter.finish();
            IOUtils.closeQuietly(templateInputStream);
        }
    }

}
