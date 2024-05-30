package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.BendCommissionEntity;
import cn.edu.lut.welder.service.BendCommissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-18 18:49:47
 */
@Controller
@RequestMapping("/")
public class BendCommissionController {
    @Autowired
    private BendCommissionService bendCommissionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/bendCommission")
    public String bendCommission() {
        return "bendcommission/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("bendCommission/add")
    @RequiresPermissions("bendCommission:add")
    @ResponseBody
    public DataResult add(@RequestBody BendCommissionEntity bendCommission){
        bendCommissionService.save(bendCommission);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("bendCommission/delete")
    @RequiresPermissions("bendCommission:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        bendCommissionService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("bendCommission/update")
    @RequiresPermissions("bendCommission:update")
    @ResponseBody
    public DataResult update(@RequestBody BendCommissionEntity bendCommission){
        bendCommissionService.updateById(bendCommission);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("bendCommission/listByPage")
    @RequiresPermissions("bendCommission:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody BendCommissionEntity bendCommission){
//        LambdaQueryWrapper<BendCommissionEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(BendCommissionEntity::getId, bendCommission.getId());
//        queryWrapper.orderByDesc(BendCommissionEntity::getId);
        IPage<BendCommissionEntity> iPage = bendCommissionService.page(bendCommission.getQueryPage(), null);
        return DataResult.success(iPage);
    }

}
