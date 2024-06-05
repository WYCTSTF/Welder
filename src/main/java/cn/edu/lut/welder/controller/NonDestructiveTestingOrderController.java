package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import cn.edu.lut.welder.service.NonDestructiveTestingOrderService;
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
    @PostMapping("nonDestructiveTestingOrder/generateReport")
    @RequiresPermissions("nonDestructiveTestingOrder:generateReport")
    @ResponseBody
    public DataResult generateReport(@RequestBody NonDestructiveTestingOrderEntity nonDestructiveTestingOrder){


        return null;
    }

    @ApiOperation(value = "带N堆焊的检测方法设置为PT")
    @PostMapping("nonDestructiveTestingOrder/update-detection")
    @RequiresPermissions("nonDestructiveTestingOrder:update-detection")
    @ResponseBody
    public DataResult updateDetection() {
        nonDestructiveTestingOrderService.updataDetectionForDataContainingN();
        nonDestructiveTestingOrderService.updataDetectionForDataContainingFAndFG();
        nonDestructiveTestingOrderService.updataDetectionForDataContainingDiameterLessThan76();
        return DataResult.success();
    }

}
