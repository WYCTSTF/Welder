package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.MacroCommissionEntity;
import cn.edu.lut.welder.service.MacroCommissionService;
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
        LambdaQueryWrapper<MacroCommissionEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.eq(MacroCommissionEntity::getId, macroCommission.getId());
        queryWrapper.orderByDesc(MacroCommissionEntity::getId);
        IPage<MacroCommissionEntity> iPage = macroCommissionService.page(macroCommission.getQueryPage(), queryWrapper);
        return DataResult.success(iPage);
    }

}
