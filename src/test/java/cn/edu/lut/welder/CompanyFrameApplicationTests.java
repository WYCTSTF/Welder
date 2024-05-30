package cn.edu.lut.welder;

import cn.edu.lut.welder.entity.BendCommissionEntity;
import cn.edu.lut.welder.entity.CirculationCardEntity;
import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import cn.edu.lut.welder.service.BendCommissionService;
import cn.edu.lut.welder.service.CirculationCardService;
import cn.edu.lut.welder.service.ExaminationLedgerService;
import cn.edu.lut.welder.service.NonDestructiveTestingOrderService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyFrameApplicationTests {
    @Test
    public void test1() throws IOException {
        // 解析路径的file文件
        String excelFilePath = "F:\\idea_workspace\\WelderExaminationMaterialsManage\\src\\main\\resources\\tmp_excel\\1203e315-507d-4651-ab01-1078946ec2e4.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(2); // 读取第一个工作表
        for (Row row : sheet) { // 迭代每一行
            if (row.getRowNum() == 0 || row.getRowNum() == 1) {
                continue;
            }
            System.out.println(row.getCell(1).getStringCellValue());
            System.out.println(row.getCell(2).getStringCellValue());
            System.out.println(row.getCell(3).getStringCellValue());
            System.out.println(row.getCell(4).getStringCellValue());
            System.out.println(row.getCell(5).getStringCellValue());
        }
                // for (Cell cell : row) { // 迭代每一列
                    // 根据不同数据类型，以字符串形式输出数据
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
//                            break;
//                        case FORMULA:
//                            System.out.print(cell.getCellFormula() + "\t");
//                            break;
//                        default: break;
//                    }
    }

    @Autowired
    private CirculationCardService circulationCardService;

    @Autowired
    private ExaminationLedgerService examinationLedgerService;
    @Autowired
    private BendCommissionService bendCommissionService;

    @Autowired
    private NonDestructiveTestingOrderService nonDestructiveTestingOrderService;


    @Test
    public void test2()
    {
        List<ExaminationLedgerEntity> list = examinationLedgerService.list();
        CirculationCardEntity circulationCardEntity = new CirculationCardEntity();
        for (ExaminationLedgerEntity entity : list)
        {
            circulationCardEntity.setName(entity.getWelderName());
            circulationCardEntity.setExamItems(entity.getForensicProjects());
            circulationCardEntity.setTestPieceCode(entity.getCode());
            circulationCardEntity.setTestPieceMaterial(entity.getCode());
            circulationCardEntity.setTestPieceSpecifications(entity.getSpecification());
            circulationCardService.save(circulationCardEntity);
        }
        System.out.println(list);
    }



    @Test
    public void test3() throws IOException {
        // 解析路径的file文件
        String excelFilePath = "F:\\idea_workspace\\WelderExaminationMaterialsManage\\焊工考试资料生成软件\\数据库资料\\弯曲数据.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
        for (Row row : sheet) { // 迭代每一行
            if (row.getRowNum() == 0) {
                continue;
            }
            BendCommissionEntity bendCommissionEntity = new BendCommissionEntity();
            String material = row.getCell(0).getStringCellValue();
            String specification = row.getCell(1).getStringCellValue();
            bendCommissionEntity.setMaterial(material);
            bendCommissionEntity.setSpecification(specification);
            System.out.println(material + "  " + specification);
            bendCommissionService.save(bendCommissionEntity);
        }

    }

    @Test
    public void test4() throws IOException {
        // 解析路径的file文件
        String excelFilePath = "F:\\idea_workspace\\WelderExaminationMaterialsManage\\焊工考试资料生成软件\\数据库资料\\RT数据.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
        for (Row row : sheet) { // 迭代每一行
            if (row.getRowNum() == 0) {
                continue;
            }
            NonDestructiveTestingOrderEntity nonDestructiveTestingOrderEntity = new NonDestructiveTestingOrderEntity();
            String material = row.getCell(0).getStringCellValue();
            String specification = row.getCell(1).getStringCellValue();
            nonDestructiveTestingOrderEntity.setTestMaterial(material);
            nonDestructiveTestingOrderEntity.setTestSpecification(specification);
            nonDestructiveTestingOrderEntity.setDetectionMethod("PT");
            nonDestructiveTestingOrderEntity.setPrincipalId("HCPT23-");
            nonDestructiveTestingOrderEntity.setPrincipalId("HCPT23-");
            nonDestructiveTestingOrderEntity.setTotalInspectedItems(0);
            nonDestructiveTestingOrderEntity.setTestingStandards("NB/T47013.5-2015");
            nonDestructiveTestingOrderEntity.setEligibleLevel("I级");
            nonDestructiveTestingOrderEntity.setDetectionRatio("100%");
            nonDestructiveTestingOrderEntity.setTestQuantity(0);
            nonDestructiveTestingOrderEntity.setReceiverDate(new Date());
            nonDestructiveTestingOrderEntity.setClientDate(new Date());
            System.out.println(material + "  " + specification);
            nonDestructiveTestingOrderService.save(nonDestructiveTestingOrderEntity);
        }

    }


    @Test
    public void test5() throws IOException {
        // 解析路径的file文件
        String excelFilePath = "F:\\idea_workspace\\WelderExaminationMaterialsManage\\焊工考试资料生成软件\\数据库资料\\RT数据.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
        for (Row row : sheet) { // 迭代每一行
            if (row.getRowNum() == 0) {
                continue;
            }
            NonDestructiveTestingOrderEntity nonDestructiveTestingOrderEntity = new NonDestructiveTestingOrderEntity();
            String material = row.getCell(0).getStringCellValue();
            String specification = row.getCell(1).getStringCellValue();
            nonDestructiveTestingOrderEntity.setTestMaterial(material);
            nonDestructiveTestingOrderEntity.setTestSpecification(specification);
            nonDestructiveTestingOrderEntity.setDetectionMethod("PT");
            nonDestructiveTestingOrderEntity.setPrincipalId("HCPT23-");
            nonDestructiveTestingOrderEntity.setPrincipalId("HCPT23-");
            nonDestructiveTestingOrderEntity.setTotalInspectedItems(0);
            nonDestructiveTestingOrderEntity.setTestingStandards("NB/T47013.5-2015");
            nonDestructiveTestingOrderEntity.setEligibleLevel("I级");
            nonDestructiveTestingOrderEntity.setDetectionRatio("100%");
            nonDestructiveTestingOrderEntity.setTestQuantity(0);
            nonDestructiveTestingOrderEntity.setReceiverDate(new Date());
            nonDestructiveTestingOrderEntity.setClientDate(new Date());
            System.out.println(material + "  " + specification);
            nonDestructiveTestingOrderService.save(nonDestructiveTestingOrderEntity);
        }

    }
}
