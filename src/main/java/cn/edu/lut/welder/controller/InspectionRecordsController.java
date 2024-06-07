package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.InspectionRecordsEntity;
import cn.edu.lut.welder.service.InspectionRecordsService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-18 20:00:33
 */
@Controller
@RequestMapping("/")
public class InspectionRecordsController {
    @Autowired
    private InspectionRecordsService inspectionRecordsService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/inspectionRecords")
    public String inspectionRecords() {
        return "inspectionrecords/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("inspectionRecords/add")
    @RequiresPermissions("inspectionRecords:add")
    @ResponseBody
    public DataResult add(@RequestBody InspectionRecordsEntity inspectionRecords){
        inspectionRecordsService.save(inspectionRecords);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("inspectionRecords/delete")
    @RequiresPermissions("inspectionRecords:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        inspectionRecordsService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("inspectionRecords/update")
    @RequiresPermissions("inspectionRecords:update")
    @ResponseBody
    public DataResult update(@RequestBody InspectionRecordsEntity inspectionRecords){
        inspectionRecordsService.updateById(inspectionRecords);
        return DataResult.success();
    }

    @ApiOperation(value = "生成检验记录表")
    @GetMapping(value = "inspectionRecords/generateInspectionTable")
    @RequiresPermissions("inspectionRecords:generateInspectionTable")
    @ResponseBody
    public void generateInspectionTable(HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment;filename=" + "test.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=gb2312");

        /**
         * 构建excel表格
         */
        Long id = 1L;
        InspectionRecordsEntity inspectionRecordsEntity = inspectionRecordsService.getById(id);
        ExcelWriter excelWriter = null;
        //获取项目resources/template目录下模板的数据流
        InputStream templateInputStream = null;

        //构建第一个sheet页的数据，根据模板填充
        Map<String, Object> sheetMap = new HashMap<>();
        Map<String, Object> sheetMap1 = new HashMap<>();
        //与模板对应字段-值设置
        sheetMap.put("name", "测试数据1");
        sheetMap.put("exam_number", "测试数据2");
        sheetMap1.put("inspectName", "测试数据2");


        String filePath = null;
        try {
            //获取项目resources/template目录下模板的数据流
            templateInputStream = new
                    ClassPathResource("/template/excel/inspectionRecord.xlsx").getInputStream();

            //设置文件名，filePath导出路径: 暂存java临时目录 最后再删除 "java.io.tmpdir"
//            String fileName = "测试报告" + System.currentTimeMillis() + ".xlsx";
//            String path = "/tmp_excel/";
//            filePath = path + fileName;
            //ExcelWriter通过POI将值写入Excel
            excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateInputStream).build();
            // sheet-概览
            WriteSheet firstSheet = EasyExcel.writerSheet(0).build();
            WriteSheet secondSheet = EasyExcel.writerSheet(1).build();
            //用数据去填充模板 取对应的值显示在模板对应的位置
            excelWriter.fill(sheetMap, firstSheet);
            excelWriter.fill(sheetMap1, secondSheet);

        } catch (IOException e) {
            System.out.println("获取模板失败!");
            e.printStackTrace();
        } finally {
            //最后记得关闭流
            excelWriter.finish();
            IOUtils.closeQuietly(templateInputStream);
        }
    }


    @ApiOperation(value = "查询分页数据")
    @PostMapping("inspectionRecords/listByPage")
    @RequiresPermissions("inspectionRecords:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody InspectionRecordsEntity inspectionRecords){
        LambdaQueryWrapper<InspectionRecordsEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.eq(InspectionRecordsEntity::getId, inspectionRecords.getId());
        queryWrapper.orderByDesc(InspectionRecordsEntity::getId);
        IPage<InspectionRecordsEntity> iPage = inspectionRecordsService.page(inspectionRecords.getQueryPage(), queryWrapper);
        return DataResult.success(iPage);
    }

}
