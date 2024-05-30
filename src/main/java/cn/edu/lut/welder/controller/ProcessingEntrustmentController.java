package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.ProcessingEntrustmentEntity;
import cn.edu.lut.welder.service.ProcessingEntrustmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<ProcessingEntrustmentEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.eq(ProcessingEntrustmentEntity::getId, processingEntrustment.getId());
        queryWrapper.orderByDesc(ProcessingEntrustmentEntity::getId);
        IPage<ProcessingEntrustmentEntity> iPage = processingEntrustmentService.page(processingEntrustment.getQueryPage(), queryWrapper);
        return DataResult.success(iPage);
    }

}
