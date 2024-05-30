package cn.edu.lut.welder.service.impl;

import cn.edu.lut.welder.entity.InspectionRecordsEntity;
import cn.edu.lut.welder.mapper.InspectionRecordsMapper;
import cn.edu.lut.welder.service.InspectionRecordsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("inspectionRecordsService")
public class InspectionRecordsServiceImpl extends ServiceImpl<InspectionRecordsMapper, InspectionRecordsEntity> implements InspectionRecordsService {


}