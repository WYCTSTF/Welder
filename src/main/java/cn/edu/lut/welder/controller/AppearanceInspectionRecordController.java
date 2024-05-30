package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.AppearanceInspectionRecordEntity;
import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.service.AppearanceInspectionRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-16 22:11:59
 */
@Controller
@RequestMapping("/")
public class AppearanceInspectionRecordController {
    @Autowired
    private AppearanceInspectionRecordService appearanceInspectionRecordService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/appearanceInspectionRecord")
    public String appearanceInspectionRecord() {
        return "appearanceinspectionrecord/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("appearanceInspectionRecord/add")
    @RequiresPermissions("appearanceInspectionRecord:add")
    @ResponseBody
    public DataResult add(@RequestBody AppearanceInspectionRecordEntity appearanceInspectionRecord){
        appearanceInspectionRecordService.save(appearanceInspectionRecord);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("appearanceInspectionRecord/delete")
    @RequiresPermissions("appearanceInspectionRecord:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        appearanceInspectionRecordService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("appearanceInspectionRecord/update")
    @RequiresPermissions("appearanceInspectionRecord:update")
    @ResponseBody
    public DataResult update(@RequestBody AppearanceInspectionRecordEntity appearanceInspectionRecord){
        appearanceInspectionRecordService.updateById(appearanceInspectionRecord);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("appearanceInspectionRecord/listByPage")
    @RequiresPermissions("appearanceInspectionRecord:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AppearanceInspectionRecordEntity appearanceInspectionRecord){
//        LambdaQueryWrapper<AppearanceInspectionRecordEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(AppearanceInspectionRecordEntity::getId, appearanceInspectionRecord.getId());
//        queryWrapper.orderByDesc(AppearanceInspectionRecordEntity::getId);
        IPage<AppearanceInspectionRecordEntity> iPage = appearanceInspectionRecordService.page(appearanceInspectionRecord.getQueryPage(), null);
        return DataResult.success(iPage);
    }


    @Autowired
    private Environment env;

    //导入excel数据
    @ApiOperation(value = "导入数据")
    @PostMapping("appearanceInspectionRecord/import_excel")
    @RequiresPermissions("appearanceInspectionRecord:import_Data")
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
        Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
        AppearanceInspectionRecordEntity appearanceInspectionRecordEntity = new AppearanceInspectionRecordEntity();

        String project = "";
        String test_piece = "";

        for (Row row : sheet) { // 迭代每一行
            if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2) {
                continue;
            }
            System.out.println(row.getCell(0).getStringCellValue());
            System.out.println(row.getCell(1).getStringCellValue());
            if (row.getCell(0).getStringCellValue() != "") {
                project = row.getCell(0).getStringCellValue();
                appearanceInspectionRecordEntity.setProject(project);
            }
            if (row.getCell(1).getStringCellValue() != "") {
                test_piece = row.getCell(1).getStringCellValue();
                appearanceInspectionRecordEntity.setTestPieceNumber(test_piece);
            }
            appearanceInspectionRecordService.save(appearanceInspectionRecordEntity);
        }
    }

    }
