package cn.edu.lut.welder.mapper;
import cn.edu.lut.welder.entity.MacroCommissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**1,定义Mapper接口: 创建一个Mapper接口，
        其中定义一个使用@Update注解的方法来执行SQL更新操作。*/
@Mapper
public interface MacroCommissionMapper extends BaseMapper<MacroCommissionEntity> {
    //需求9 根据考试台账，自动识别带“F”、“FG”做宏观委托。
    @Update("UPDATE macro_Commission SET detection_method = '宏观委托' WHERE forensic_projects LIKE '%F%' OR forensic_projects LIKE '%FG%' ")
    void updataDetectionForDataContainingFAndFG();
    //需求9 带“F”板材：检件编号用原编号,不变
    //    //     带“FG”：管径<76，编号在基础上-1
    //    //             管经>76，用原编号，不变
    @Update("UPDATE macro_Commission \n" +
            "        SET \n" +
            "            code = \n" +
            "                CASE\n" +
            "                    WHEN forensic_projects LIKE '%FG%' AND \n" + "code LIKE '%-%' AND \n" +
            "                        CAST(SUBSTRING_INDEX(SUBSTRING(specification,LOCATE('Φ',specification)+1), '×', 1)AS UNSIGNED) < 76 \n" +
            "                    THEN CONCAT(SUBSTRING_INDEX(code,'-',1),'-', CAST(SUBSTRING(code,LOCATE('-',code)+1) AS SIGNED)+1) \n" +
            "                    WHEN forensic_projects LIKE '%FG%' AND \n" + "code NOT LIKE '%-%' AND \n" +
            "                        CAST(SUBSTRING_INDEX(SUBSTRING(specification,LOCATE('Φ',specification)+1), '×', 1)AS UNSIGNED) < 76 \n" +
            "                    THEN CONCAT(SUBSTRING_INDEX(code,'-',1),'-', '1') \n" +
            "                    ELSE code\n" +
            "                END\n" +
            "        WHERE \n" +
            "            (forensic_projects LIKE '%F%' OR forensic_projects LIKE '%FG%')")
    void updataDetectionForDataContainingDiameterLessThan76();
}
