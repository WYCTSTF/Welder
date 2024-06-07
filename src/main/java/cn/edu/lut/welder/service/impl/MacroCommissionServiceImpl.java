package cn.edu.lut.welder.service.impl;

import cn.edu.lut.welder.entity.MacroCommissionEntity;
import cn.edu.lut.welder.mapper.MacroCommissionMapper;
import cn.edu.lut.welder.service.MacroCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("macroCommissionService")
public class MacroCommissionServiceImpl extends ServiceImpl<MacroCommissionMapper, MacroCommissionEntity> implements MacroCommissionService {

    @Autowired
    private MacroCommissionMapper macroCommissionMapper;
    @Override
    public void updataDetectionForDataContainingFAndFG(){
        macroCommissionMapper.updataDetectionForDataContainingFAndFG();
    }

    @Override
    public void updataDetectionForDataContainingDiameterLessThan76(){
        macroCommissionMapper.updataDetectionForDataContainingDiameterLessThan76();
    }

}