package cn.edu.lut.welder;

import cn.edu.lut.welder.entity.BendCommissionEntity;
import cn.edu.lut.welder.entity.CirculationCardEntity;
import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import cn.edu.lut.welder.service.BendCommissionService;
import cn.edu.lut.welder.service.CirculationCardService;
import cn.edu.lut.welder.service.ExaminationLedgerService;
import cn.edu.lut.welder.service.NonDestructiveTestingOrderService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Test
    public void test6() throws Exception {
        //设置表名
        String fileName = "检验记录表.xlsx";
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //设置表格名称
        SXSSFSheet sheet = workbook.createSheet("检验记录表");
        //设置表格默认宽度30
        sheet.setDefaultColumnWidth(30);
        //设置 sheet 表格的第0行
        SXSSFRow row = sheet.createRow(0);
        //设置 sheet 表格的第0行第0列的值
        row.createCell(0).setCellValue("商品名称");
        //设置 sheet 表格的第0行第1列的值
        row.createCell(1).setCellValue("数量");
        //将 Excel 表输出到输出流
        FileOutputStream fos = new FileOutputStream("./test.xlsx");
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


    @Test
    public void test7() throws Exception {

        String fileName =  "用户列表信息.xlsx";
//        String filePath = this.getClass().getClassLoader().getResource("/templates/用户列表excel模板.xlsx").getPath();
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("name", "张三");
        beans.put("exam_number", "12356");

        XLSTransformer transformer = new XLSTransformer();
        //第一种方式
//        Resource resource = new ClassPathResource("/templates/用户列表excel模板.xlsx");
//        File sourceFile =  resource.getFile();
//        第二种方式
        InputStream is = this.getClass().getResourceAsStream("/template/template.xlsx");

//        InputStream is = new BufferedInputStream(new FileInputStream(sourceFile));
        FileOutputStream fos = new FileOutputStream("./test.xlsx");
        Workbook workbook = transformer.transformXLS(is, beans);
        workbook.write(fos);
        fos.flush();
        fos.close();
        is.close();
    }


    @Test
    public void export() {
        ExcelWriter excelWriter = null;
        //获取项目resources/template目录下模板的数据流
        InputStream templateInputStream = null;

        //构建第一个sheet页的数据，根据模板填充
        Map<String, Object> sheetMap = new HashMap<>();
        //与模板对应字段-值设置
        sheetMap.put("name", "测试数据1");
        sheetMap.put("exam_number", "测试数据2");
        String filePath = null;
        try {
            //获取项目resources/template目录下模板的数据流
            templateInputStream = new
                    ClassPathResource("/template/inspectionRecord.xlsx").getInputStream();

            //设置文件名，filePath导出路径: 暂存java临时目录 最后再删除 "java.io.tmpdir"
//            String fileName = "inspectionRecord.xlsx";
//            String path = System.getProperty("file.path") + File.separator;
//            filePath = path + fileName;
//            System.out.println(filePath);
            //ExcelWriter通过POI将值写入Excel
            excelWriter = EasyExcel.write("./test.xlsx").withTemplate(templateInputStream).build();
            // sheet-概览
            WriteSheet firstSheet = EasyExcel.writerSheet(0).build();
            //用数据去填充模板 取对应的值显示在模板对应的位置
            excelWriter.fill(sheetMap, firstSheet);

        } catch (IOException e) {
            //log.error("获取模板：examreport 失败。");
            e.printStackTrace();
        } finally {
            //最后记得关闭流
            excelWriter.finish();
            IOUtils.closeQuietly(templateInputStream);
            //删除临时文件
//            if (null != filePath) {
//                ExcelUtils.deleteFile(filePath);
//            }

        }
    }


    @Test
    public void test8()
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
    }
}
