package cn.edu.lut.welder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.lut.welder.entity.SysJobLogEntity;
import cn.edu.lut.welder.mapper.SysJobLogMapper;
import cn.edu.lut.welder.service.SysJobLogService;
import org.springframework.stereotype.Service;

/**
 * 定时任务 服务类
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysJobLogService")
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLogEntity> implements SysJobLogService {


}