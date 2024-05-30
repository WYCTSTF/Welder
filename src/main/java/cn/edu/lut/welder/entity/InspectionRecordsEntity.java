package cn.edu.lut.welder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-18 20:00:33
 */
@Data
@TableName("inspection_records")
public class InspectionRecordsEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 姓名
	 */
	@TableField("name")
	private String name;

	/**
	 * 考试编号
	 */
	@TableField("exam_number")
	private Integer examNumber;

	/**
	 * 焊接方法
	 */
	@TableField("welding_method")
	private String weldingMethod;

	/**
	 * 机动化程度
	 */
	@TableField("degree_of_motorization")
	private String degreeOfMotorization;

	/**
	 * 指导书编号
	 */
	@TableField("instruction_number")
	private String instructionNumber;

	/**
	 * 金属材料类别代号
	 */
	@TableField("metal_material_category_code")
	private String metalMaterialCategoryCode;

	/**
	 * 试件板材厚度
	 */
	@TableField("piece_plate_thickness")
	private Double piecePlateThickness;

	/**
	 * 试件管材外径与壁厚
	 */
	@TableField("outer_diameter_and_wall_thickness")
	private Double outerDiameterAndWallThickness;

	/**
	 * 螺柱直径
	 */
	@TableField("stud_diameter")
	private Double studDiameter;

	/**
	 * 填充金属材料类别代号、型号
	 */
	@TableField("material_category_code_model")
	private String materialCategoryCodeModel;

	/**
	 * 考试项目代号
	 */
	@TableField("exam_item_code")
	private String examItemCode;

	/**
	 * 焊缝表面状况
	 */
	@TableField("weld_surface_condition")
	private String weldSurfaceCondition;

	/**
	 * 焊缝余高
	 */
	@TableField("weld_reinforcement")
	private Double weldReinforcement;

	/**
	 * 焊缝余高差
	 */
	@TableField("weld_seam_height_difference")
	private Double weldSeamHeightDifference;

	/**
	 * 比坡口每侧增宽
	 */
	@TableField("each_side_wider")
	private String eachSideWider;

	/**
	 * 宽度差
	 */
	@TableField("width_difference")
	private Double widthDifference;

	/**
	 * 焊缝边缘直线度
	 */
	@TableField("edge_straightness")
	private Double edgeStraightness;

	/**
	 * 背面焊缝余高
	 */
	@TableField("back_weld_reinforcement")
	private Double backWeldReinforcement;

	/**
	 * 裂纹
	 */
	@TableField("crack")
	private String crack;

	/**
	 * 未熔合
	 */
	@TableField("not_fused")
	private String notFused;

	/**
	 * 夹渣
	 */
	@TableField("slag")
	private String slag;

	/**
	 * 咬边
	 */
	@TableField("undercut")
	private String undercut;

	/**
	 * 未焊透
	 */
	@TableField("not_penetrated")
	private String notPenetrated;

	/**
	 * 背面凹坑
	 */
	@TableField("dimples_back")
	private String dimplesBack;

	/**
	 * 气孔
	 */
	@TableField("stomata")
	private String stomata;

	/**
	 * 焊瘤
	 */
	@TableField("weld")
	private String weld;

	/**
	 * 变形角度
	 */
	@TableField("deformation_angle")
	private String deformationAngle;

	/**
	 * 错边量
	 */
	@TableField("error_margin")
	private String errorMargin;

	/**
	 * 角焊缝凹凸度
	 */
	@TableField("fillet_weld_concavity_convexity")
	private String filletWeldConcavityConvexity;

	/**
	 * 焊脚
	 */
	@TableField("welding_feet")
	private String weldingFeet;

	/**
	 * 堆焊焊道对接不平度
	 */
	@TableField("butt_unevenness_of_surfacing_weld_bead")
	private String buttUnevennessOfSurfacingWeldBead;

	/**
	 * 堆焊焊道高度差
	 */
	@TableField("welding_bead_height_difference")
	private Double weldingBeadHeightDifference;

	/**
	 * 堆焊凹下量
	 */
	@TableField("deposit_surfacing_welding")
	private String depositSurfacingWelding;

	/**
	 * 外观检查结果
	 */
	@TableField("appearance_result")
	private String appearanceResult;

	/**
	 * 检验员
	 */
	@TableField("inspector")
	private String inspector;

	/**
	 * 检验日期
	 */
	@TableField("inspection_date")
	private Date inspectionDate;

	/**
	 * 射线透照质量等级
	 */
	@TableField("radiographic_quality_level")
	private String radiographicQualityLevel;

	/**
	 * 焊缝缺陷等级
	 */
	@TableField("weld_defect_grade")
	private String weldDefectGrade;

	/**
	 * 焊缝缺陷检测结果
	 */
	@TableField("weld_defect_testing_results")
	private String weldDefectTestingResults;

	/**
	 * 报告编号与日期
	 */
	@TableField("report_number_date")
	private Integer reportNumberDate;

	/**
	 * 渗透检测方法				

	 */
	@TableField("penetration_detection_methods")
	private String penetrationDetectionMethods;

	/**
	 * 渗透检测结果
	 */
	@TableField("penetration_testing_results")
	private String penetrationTestingResults;

	/**
	 * 渗透报告编号与日期
	 */
	@TableField("penetration_testing_report_num_date")
	private String penetrationTestingReportNumDate;

	/**
	 * 无损检测结果
	 */
	@TableField("nodestructive_result")
	private String nodestructiveResult;

	/**
	 * 无损检测人员
	 */
	@TableField("nodestructive_testing_personnel")
	private String nodestructiveTestingPersonnel;

	/**
	 * 日期
	 */
	@TableField("no_destructive_date")
	private Date noDestructiveDate;

	/**
	 *   无损检测人员证书编号
	 */
	@TableField("no_destructive_person_certificate_number")
	private String noDestructivePersonCertificateNumber;


}
