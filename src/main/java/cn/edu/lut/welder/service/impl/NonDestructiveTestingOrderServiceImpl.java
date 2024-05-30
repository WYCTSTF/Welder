package cn.edu.lut.welder.service.impl;

import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import cn.edu.lut.welder.mapper.NonDestructiveTestingOrderMapper;
import cn.edu.lut.welder.service.NonDestructiveTestingOrderService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("nonDestructiveTestingOrderService")
public class NonDestructiveTestingOrderServiceImpl extends ServiceImpl<NonDestructiveTestingOrderMapper, NonDestructiveTestingOrderEntity> implements NonDestructiveTestingOrderService {


}