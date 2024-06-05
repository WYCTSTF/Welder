package cn.edu.lut.welder.mapper;


import cn.edu.lut.welder.entity.NonDestructiveTestingOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-17 20:00:10
 */
@Mapper
public interface NonDestructiveTestingOrderMapper extends BaseMapper<NonDestructiveTestingOrderEntity> {
    //需求4 PT委托，考试项目中带堆焊N的项目，更新检测方法为PT
    @Update("UPDATE non_destructive_testing_order SET detection_method = 'PT' WHERE forensic_projects LIKE '%M%' ")
    void updataDetectionForDataContainingN();
    //需求4 RT委托，考试项目中带F和FG的项目，更新检测方法为 无需拍照
    @Update("UPDATE non_destructive_testing_order SET detection_method = '无需拍照' WHERE forensic_projects LIKE '%F%' OR forensic_projects LIKE '%FG%' ")
    void updataDetectionForDataContainingFAndFG();
    //需求4 管径<76，编号在基础上-1-2-3
    //     管板<76，编号在基础上-1-2
    @Update("UPDATE non_destructive_testing_order SET inspection_number = CONCAT(inspection_number, '-1') WHERE test_specification LIKE '%Φ%×%' AND CAST(SUBSTRING(test_specification, LOCATE('%Φ%×%', test_specification) + 2, LOCATE('×', test_specification) - LOCATE('%Φ%×%', test_specification) - 2) AS UNSIGNED) < 76 ")
    void updataDetectionForDataContainingDiameterLessThan76();
}
