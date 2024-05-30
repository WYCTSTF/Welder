package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.service.ExaminationLedgerService;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-14 11:24:24
 */
@Controller
@RequestMapping("/")
public class ExaminationLedgerController {
    @Autowired
    private ExaminationLedgerService examinationLedgerService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/examinationLedger")
    public String examinationLedger() {
        return "examinationledger/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("examinationLedger/add")
    @RequiresPermissions("examinationLedger:add")
    @ResponseBody
    public DataResult add(@RequestBody ExaminationLedgerEntity examinationLedger){
        examinationLedgerService.save(examinationLedger);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("examinationLedger/delete")
    @RequiresPermissions("examinationLedger:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        examinationLedgerService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("examinationLedger/update")
    @RequiresPermissions("examinationLedger:update")
    @ResponseBody
    public DataResult update(@RequestBody ExaminationLedgerEntity examinationLedger){
        examinationLedgerService.updateById(examinationLedger);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("examinationLedger/listByPage")
    @RequiresPermissions("examinationLedger:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ExaminationLedgerEntity examinationLedger){
        LambdaQueryWrapper<ExaminationLedgerEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
//        queryWrapper.eq(StringUtils.isNotBlank(examinationLedger.getId()), ExaminationLedgerEntity:: getId, examinationLedger.getId());
//        queryWrapper.orderByDesc(ExaminationLedgerEntity::getId);
        IPage<ExaminationLedgerEntity> iPage = examinationLedgerService.page(examinationLedger.getQueryPage(), null);
        List<ExaminationLedgerEntity> records = iPage.getRecords();
        iPage.setRecords(records);
        return DataResult.success(iPage);
    }





    @Autowired
    private Environment env;

    //导入excel数据
    @ApiOperation(value = "导入数据")
    @PostMapping("examinationLedger/import_excel")
    @RequiresPermissions("examinationLedger:import_Data")
    @ResponseBody
    public DataResult import_excel(MultipartFile file){
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = env.getProperty("file.path");
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            importExcel(filePath + fileName);
        } catch (Exception e) {
            return  DataResult.fail("数据导入失败");
        }
        return DataResult.success("数据导入成功");
    }

    private void importExcel(String excelFilePath)throws Exception {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(2); // 读取第一个工作表
        ExaminationLedgerEntity examinationLedgerEntity = new ExaminationLedgerEntity();

        String name = "";
        String card = "";
        String forensicProjects = "";
        String material = "";
        String specification = "";
        String code = "";
        for (Row row : sheet) { // 迭代每一行
            if (row.getRowNum() == 0 || row.getRowNum() == 1) {
                continue;
            }

            if (row.getCell(1).getStringCellValue() != "")
            {
                name = row.getCell(1).getStringCellValue();
                examinationLedgerEntity.setWelderName(name);
            }
            if (row.getCell(2).getStringCellValue() != ""){
                card = row.getCell(2).getStringCellValue();
                examinationLedgerEntity.setIdCard(card);
            }

            forensicProjects = row.getCell(3).getStringCellValue();
            examinationLedgerEntity.setForensicProjects(forensicProjects);
            material = row.getCell(4).getStringCellValue();
            examinationLedgerEntity.setMaterial(material);
            specification = row.getCell(5).getStringCellValue();
            examinationLedgerEntity.setSpecification(specification);
            code = row.getCell(6).getStringCellValue();
            examinationLedgerEntity.setCode(code);
            examinationLedgerService.save(examinationLedgerEntity);

        }

    }
}
