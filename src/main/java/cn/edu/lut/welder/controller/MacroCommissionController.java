package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.MacroCommissionEntity;
import cn.edu.lut.welder.service.MacroCommissionService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
 * @date 2024-05-18 19:02:23
 */
@Controller
@RequestMapping("/")
public class MacroCommissionController {
    @Autowired
    private MacroCommissionService macroCommissionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/macroCommission")
    public String macroCommission() {
        return "macrocommission/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("macroCommission/add")
    @RequiresPermissions("macroCommission:add")
    @ResponseBody
    public DataResult add(@RequestBody MacroCommissionEntity macroCommission){
        macroCommissionService.save(macroCommission);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("macroCommission/delete")
    @RequiresPermissions("macroCommission:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        macroCommissionService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("macroCommission/update")
    @RequiresPermissions("macroCommission:update")
    @ResponseBody
    public DataResult update(@RequestBody MacroCommissionEntity macroCommission){
        macroCommissionService.updateById(macroCommission);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("macroCommission/listByPage")
    @RequiresPermissions("macroCommission:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody MacroCommissionEntity macroCommission){
//        LambdaQueryWrapper<MacroCommissionEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(MacroCommissionEntity::getId, macroCommission.getId());
//        queryWrapper.orderByDesc(MacroCommissionEntity::getId);
        IPage<MacroCommissionEntity> iPage = macroCommissionService.page(macroCommission.getQueryPage(), null);
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "宏观报告生成")
    @GetMapping("macroCommission/generateReport")
    @RequiresPermissions("macroCommission:generateReport")
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
        sheetMap.put("projectName", "焊工考试试件");
        sheetMap.put("reportNum", "PTR24-HGKS019-0001");

        try {

            templateInputStream = new
                    ClassPathResource("/template/excel/macro_report.xlsx").getInputStream();
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


    @ApiOperation(value = "更新宏观委托数据")
    @GetMapping("macroCommission/updateDetection")
    @RequiresPermissions("macroCommission:updateDetection")
    @ResponseBody
    public DataResult updateDetection(){
        try {
            macroCommissionService.updataDetectionForDataContainingFAndFG();
            macroCommissionService.updataDetectionForDataContainingDiameterLessThan76();
        }catch (Exception e){
            return DataResult.fail("宏观委托数据更新失败！");
        }
        return DataResult.success("宏观委托数据更新成功！");
    }

}
