package cn.edu.lut.welder.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.edu.lut.welder.mapper.ExaminationLedgerMapper;
import cn.edu.lut.welder.entity.ExaminationLedgerEntity;
import cn.edu.lut.welder.service.ExaminationLedgerService;


@Service("examinationLedgerService")
public class ExaminationLedgerServiceImpl extends ServiceImpl<ExaminationLedgerMapper, ExaminationLedgerEntity> implements ExaminationLedgerService {


}