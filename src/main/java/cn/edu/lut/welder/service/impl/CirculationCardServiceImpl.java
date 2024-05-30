package cn.edu.lut.welder.service.impl;

import cn.edu.lut.welder.entity.CirculationCardEntity;
import cn.edu.lut.welder.mapper.CirculationCardMapper;
import cn.edu.lut.welder.service.CirculationCardService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("circulationCardService")
public class CirculationCardServiceImpl extends ServiceImpl<CirculationCardMapper, CirculationCardEntity> implements CirculationCardService {


}