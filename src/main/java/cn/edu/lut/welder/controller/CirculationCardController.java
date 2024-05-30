package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.CirculationCardEntity;
import cn.edu.lut.welder.service.CirculationCardService;
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
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-15 16:46:58
 */
@Controller
@RequestMapping("/")
public class CirculationCardController {

    @Autowired
    private CirculationCardService circulationCardService;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/circulationCard")
    public String circulationCard() {
        return "circulationcard/list";
    }

    @ApiOperation(value = "新增")
    @PostMapping("circulationCard/add")
    @RequiresPermissions("circulationCard:add")
    @ResponseBody
    public DataResult add(@RequestBody CirculationCardEntity circulationCard){
        circulationCardService.save(circulationCard);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("circulationCard/delete")
    @RequiresPermissions("circulationCard:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        circulationCardService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("circulationCard/update")
    @RequiresPermissions("circulationCard:update")
    @ResponseBody
    public DataResult update(@RequestBody CirculationCardEntity circulationCard){
        circulationCardService.updateById(circulationCard);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("circulationCard/listByPage")
    @RequiresPermissions("circulationCard:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody CirculationCardEntity circulationCard){
//        LambdaQueryWrapper<CirculationCardEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        queryWrapper.eq(CirculationCardEntity::getId, circulationCard.getId());
//        queryWrapper.orderByDesc(CirculationCardEntity::getId);
        IPage<CirculationCardEntity> iPage = circulationCardService.page(circulationCard.getQueryPage(), null);
        return DataResult.success(iPage);
    }

}
