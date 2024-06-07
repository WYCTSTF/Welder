package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.InspectionRecordsEntity;
import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import cn.edu.lut.welder.mapper.NonDestructiveTestingOrderMapper;
import cn.edu.lut.welder.service.NonDestructiveTestingOrderService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @date 2024-05-17 20:00:10
 */
@Controller
@RequestMapping("/")
public class NonDestructiveTestingOrderController {
    @Autowired
    private NonDestructiveTestingOrderService nonDestructiveTestingOrderService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/nonDestructiveTestingOrder")
    public String nonDestructiveTestingOrder() {
        return "nondestructivetestingorder/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("nonDestructiveTestingOrder/add")
    @RequiresPermissions("nonDestructiveTestingOrder:add")
    @ResponseBody
    public DataResult add(@RequestBody NonDestructiveTestingOrderEntity nonDestructiveTestingOrder){
        nonDestructiveTestingOrderService.save(nonDestructiveTestingOrder);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("nonDestructiveTestingOrder/delete")
    @RequiresPermissions("nonDestructiveTestingOrder:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        nonDestructiveTestingOrderService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("nonDestructiveTestingOrder/update")
    @RequiresPermissions("nonDestructiveTestingOrder:update")
    @ResponseBody
    public DataResult update(@RequestBody NonDestructiveTestingOrderEntity nonDestructiveTestingOrder){
        nonDestructiveTestingOrderService.updateById(nonDestructiveTestingOrder);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("nonDestructiveTestingOrder/listByPage")
    @RequiresPermissions("nonDestructiveTestingOrder:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody NonDestructiveTestingOrderEntity nonDestructiveTestingOrder){
//        LambdaQueryWrapper<NonDestructiveTestingOrderEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(NonDestructiveTestingOrderEntity::getId, nonDestructiveTestingOrder.getId());
//        queryWrapper.orderByDesc(NonDestructiveTestingOrderEntity::getId);
        IPage<NonDestructiveTestingOrderEntity> iPage = nonDestructiveTestingOrderService.page(nonDestructiveTestingOrder.getQueryPage(), null);
        return DataResult.success(iPage);
    }



    @ApiOperation(value = "RT、PT报告生成")
    @GetMapping("nonDestructiveTestingOrder/generateReport")
    @RequiresPermissions("nonDestructiveTestingOrder:generateReport")
    @ResponseBody
    public void generateReport(HttpServletResponse response, String RPT){
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
            //获取项目resources/template目录下模板的数据流
            if (RPT.equals("PT")){
                templateInputStream = new
                        ClassPathResource("/template/excel/PT_report.xlsx").getInputStream();
            }else {
                templateInputStream = new
                        ClassPathResource("/template/excel/RT_report.xlsx").getInputStream();
            }


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


    @ApiOperation(value = "带N堆焊的检测方法设置为PT")
    @GetMapping("nonDestructiveTestingOrder/updateDetection")
    @RequiresPermissions("nonDestructiveTestingOrder:updateDetection")
    @ResponseBody
    public DataResult updataDetection() {

        try {
            UpdateWrapper<NonDestructiveTestingOrderEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.like("forensic_projects", "N").set("detection_method", "PT");
            // PT委托，考试项目中带堆焊N的项目，更新检测方法为PT
            boolean update = nonDestructiveTestingOrderService.update(null, updateWrapper);
            //RT委托，考试项目中带F和FG的项目，更新检测方法为 无需拍照
            UpdateWrapper<NonDestructiveTestingOrderEntity> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.like("forensic_projects", "F").or().like("forensic_projects", "FG").set("detection_method", "无需拍照");
            boolean update1 = nonDestructiveTestingOrderService.update(null, updateWrapper1);

            //管径<76，编号在基础上-1-2-3  管板<76，编号在基础上-1-2
            QueryWrapper<NonDestructiveTestingOrderEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("test_specification", "Φ")
                    .like("test_specification", "×");

            List<NonDestructiveTestingOrderEntity> records = nonDestructiveTestingOrderService.list(queryWrapper);


            for (NonDestructiveTestingOrderEntity record : records) {
                String testSpecification = record.getTestSpecification();
                int phiIndex = testSpecification.indexOf("Φ");
                int multiplyIndex = testSpecification.indexOf("×");
                if (phiIndex >= 0 && multiplyIndex > phiIndex) {
                    String numberStr = testSpecification.substring(phiIndex + 1, multiplyIndex).trim();
                    try {
                        int number = Integer.parseInt(numberStr);
                        if (number < 76) {
                            UpdateWrapper<NonDestructiveTestingOrderEntity> updateWrapper3 = new UpdateWrapper<>();
                            updateWrapper3.eq("id", record.getId())
                                    .set("inspection_number", record.getInspectionNumber() - 1);
                            nonDestructiveTestingOrderService.update(null, updateWrapper3);
                        }
                    } catch (NumberFormatException e) {
                        //return DataResult.fail("更新RPT委托失败！");
                    }
                }
            }
        }catch (Exception e){
            //return DataResult.fail("更新RPT委托失败！");
        }
        return DataResult.success("更新RPT委托成功");
    }


}
