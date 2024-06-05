package cn.edu.lut.welder.service;

import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-17 20:00:10
 */
public interface NonDestructiveTestingOrderService extends IService<NonDestructiveTestingOrderEntity> {
    void updataDetectionForDataContainingN();
    void updataDetectionForDataContainingFAndFG();
    void updataDetectionForDataContainingDiameterLessThan76();
}
