package cn.edu.lut.welder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.lut.welder.entity.SysRoleDeptEntity;
import cn.edu.lut.welder.mapper.SysRoleDeptMapper;
import cn.edu.lut.welder.service.SysRoleDeptService;
import org.springframework.stereotype.Service;


@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDeptEntity> implements SysRoleDeptService {


}