package cn.edu.lut.welder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.lut.welder.entity.SysContentEntity;
import cn.edu.lut.welder.mapper.SysContentMapper;
import cn.edu.lut.welder.service.SysContentService;
import org.springframework.stereotype.Service;

/**
 * 内容 服务类
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysContentService")
public class SysContentServiceImpl extends ServiceImpl<SysContentMapper, SysContentEntity> implements SysContentService {


}