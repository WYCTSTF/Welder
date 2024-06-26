package cn.edu.lut.welder.controller;

import cn.edu.lut.welder.common.aop.annotation.LogAnnotation;
import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.SysLog;
import cn.edu.lut.welder.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统操作日志
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@RequestMapping("/sys")
@Api(tags = "系统模块-系统操作日志管理")
@RestController
public class SysLogController {
    @Resource
    private LogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查询系统操作日志接口")
    @LogAnnotation(title = "系统操作日志管理", action = "分页查询系统操作日志")
    @RequiresPermissions("sys:log:list")
    public DataResult pageInfo(@RequestBody SysLog vo) {
        LambdaQueryWrapper<SysLog> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getUsername())) {
            queryWrapper.like(SysLog::getUsername, vo.getUsername());
        }
        if (!StringUtils.isEmpty(vo.getOperation())) {
            queryWrapper.like(SysLog::getOperation, vo.getOperation());
        }
        if (!StringUtils.isEmpty(vo.getStartTime())) {
            queryWrapper.gt(SysLog::getCreateTime, vo.getStartTime());
        }
        if (!StringUtils.isEmpty(vo.getEndTime())) {
            queryWrapper.lt(SysLog::getCreateTime, vo.getEndTime());
        }
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        return DataResult.success(logService.page(vo.getQueryPage(), queryWrapper));
    }

    @DeleteMapping("/logs")
    @ApiOperation(value = "删除日志接口")
    @LogAnnotation(title = "系统操作日志管理", action = "删除系统操作日志")
    @RequiresPermissions("sys:log:deleted")
    public DataResult deleted(@RequestBody List<String> logIds) {
        logService.removeByIds(logIds);
        return DataResult.success();
    }
}
