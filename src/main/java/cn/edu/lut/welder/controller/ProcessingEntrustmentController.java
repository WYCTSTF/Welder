package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.BendCommissionEntity;
import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import cn.edu.lut.welder.entity.ProcessingEntrustmentEntity;
import cn.edu.lut.welder.service.ExaminationLedgerService;
import cn.edu.lut.welder.service.MacroCommissionService;
import cn.edu.lut.welder.service.ProcessingEntrustmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;




/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-18 17:24:15
 */
@Controller
@RequestMapping("/")
public class ProcessingEntrustmentController {
    @Autowired
    private ProcessingEntrustmentService processingEntrustmentService;
    @Autowired
    private ExaminationLedgerService examinationLedgerService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/processingEntrustment")
    public String processingEntrustment() {
        return "processingentrustment/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("processingEntrustment/add")
    @RequiresPermissions("processingEntrustment:add")
    @ResponseBody
    public DataResult add(@RequestBody ProcessingEntrustmentEntity processingEntrustment){
        processingEntrustmentService.save(processingEntrustment);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("processingEntrustment/delete")
    @RequiresPermissions("processingEntrustment:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        processingEntrustmentService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("processingEntrustment/update")
    @RequiresPermissions("processingEntrustment:update")
    @ResponseBody
    public DataResult update(@RequestBody ProcessingEntrustmentEntity processingEntrustment){
        processingEntrustmentService.updateById(processingEntrustment);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("processingEntrustment/listByPage")
    @RequiresPermissions("processingEntrustment:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ProcessingEntrustmentEntity processingEntrustment){
//        LambdaQueryWrapper<ProcessingEntrustmentEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(ProcessingEntrustmentEntity::getId, processingEntrustment.getId());
//        queryWrapper.orderByDesc(ProcessingEntrustmentEntity::getId);
        IPage<ProcessingEntrustmentEntity> iPage = processingEntrustmentService.page(processingEntrustment.getQueryPage(), null);
        return DataResult.success(iPage);
    }



    @ApiOperation(value = "更新加工委托数据")
    @PostMapping("processingEntrustment/updateProcessingEntrust")
    @RequiresPermissions("processingEntrustment:updateProcessingEntrust")
    @ResponseBody
    public DataResult updateProcessingEntrust(){

//        ①根据考试台账，自动识别“F”、“FG”只做宏观金相四件；
//        ②堆焊（N）的做侧弯二件；
//        ③不管是管材还是板材只要厚度≥10mm只做侧弯二件，其余厚度做面弯一件、背弯一件。

        try {
            QueryWrapper<ExaminationLedgerEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("forensic_projects", "F").or().like("forensic_projects", "FG");
            List<ExaminationLedgerEntity> examinationLedgerEntities = examinationLedgerService.list(queryWrapper);

            for (ExaminationLedgerEntity entity : examinationLedgerEntities) {

                ProcessingEntrustmentEntity processingEntrustmentEntity = new ProcessingEntrustmentEntity();
                processingEntrustmentEntity.setMaterial(entity.getMaterial());
                processingEntrustmentEntity.setSpecification(entity.getSpecification());
                processingEntrustmentEntity.setExaminationLedgerId(entity.getId());

                QueryWrapper<ProcessingEntrustmentEntity> queryWrapper2 = new QueryWrapper<ProcessingEntrustmentEntity>();
                queryWrapper2.eq("examination_ledger_id", entity.getId());
                ProcessingEntrustmentEntity processingEntrustmentEntity1 = processingEntrustmentService.getOne(queryWrapper2);
                if (processingEntrustmentEntity1 != null)
                {
                    QueryWrapper<ProcessingEntrustmentEntity> queryWrapper1 = new QueryWrapper<ProcessingEntrustmentEntity>();
                    queryWrapper1.eq("examination_ledger_id", entity.getId());
                    processingEntrustmentService.update(processingEntrustmentEntity, queryWrapper1);
                }else{
                    // 如果记录不存在,则执行插入操作
                    processingEntrustmentService.save(processingEntrustmentEntity);
                    System.out.println("Inserted record with examination_ledger_id=" + entity.getId());
                }
            }
        }catch(Exception e){
            return DataResult.fail("更新加工委托数据失败！");
        }

        return DataResult.success("更新加工委托数据成功！");
    }

}
