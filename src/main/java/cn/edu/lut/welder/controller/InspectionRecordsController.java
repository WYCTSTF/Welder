package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.InspectionRecordsEntity;
import cn.edu.lut.welder.service.InspectionRecordsService;
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
 * @date 2024-05-18 20:00:33
 */
@Controller
@RequestMapping("/")
public class InspectionRecordsController {
    @Autowired
    private InspectionRecordsService inspectionRecordsService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/inspectionRecords")
    public String inspectionRecords() {
        return "inspectionrecords/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("inspectionRecords/add")
    @RequiresPermissions("inspectionRecords:add")
    @ResponseBody
    public DataResult add(@RequestBody InspectionRecordsEntity inspectionRecords){
        inspectionRecordsService.save(inspectionRecords);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("inspectionRecords/delete")
    @RequiresPermissions("inspectionRecords:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        inspectionRecordsService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("inspectionRecords/update")
    @RequiresPermissions("inspectionRecords:update")
    @ResponseBody
    public DataResult update(@RequestBody InspectionRecordsEntity inspectionRecords){
        inspectionRecordsService.updateById(inspectionRecords);
        return DataResult.success();
    }





    @ApiOperation(value = "生成检验记录表")
    @PutMapping("inspectionRecords/generateInspectionTable")
    @RequiresPermissions("inspectionRecords:generateInspectionTable")
    @ResponseBody
    public DataResult generateInspectionTable(){
        //inspectionRecordsService.updateById();

        return DataResult.success();
    }


    @ApiOperation(value = "查询分页数据")
    @PostMapping("inspectionRecords/listByPage")
    @RequiresPermissions("inspectionRecords:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody InspectionRecordsEntity inspectionRecords){
        LambdaQueryWrapper<InspectionRecordsEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.eq(InspectionRecordsEntity::getId, inspectionRecords.getId());
        queryWrapper.orderByDesc(InspectionRecordsEntity::getId);
        IPage<InspectionRecordsEntity> iPage = inspectionRecordsService.page(inspectionRecords.getQueryPage(), queryWrapper);
        return DataResult.success(iPage);
    }

}
