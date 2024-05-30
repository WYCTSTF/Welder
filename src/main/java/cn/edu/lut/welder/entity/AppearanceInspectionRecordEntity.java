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
 * @date 2024-05-16 22:11:59
 */
@Data
@TableName("appearance_inspection_record")
public class AppearanceInspectionRecordEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 项目
	 */
	@TableField("project")
	private String project;

	/**
	 * 试件编号
	 */
	@TableField("test_piece_number")
	private String testPieceNumber;

	/**
	 * 表面状态
	 */
	@TableField("surface_condition")
	private String surfaceCondition;

	/**
	 * 焊缝余高
	 */
	@TableField("weld_reinforcement")
	private Double weldReinforcement;

	/**
	 * 余高差
	 */
	@TableField("residual_height_difference")
	private Double residualHeightDifference;

	/**
	 * 增宽
	 */
	@TableField("widen")
	private Double widen;

	/**
	 * 宽度差
	 */
	@TableField("width_difference")
	private Double widthDifference;

	/**
	 * 直线度
	 */
	@TableField("straightness")
	private Double straightness;

	/**
	 * 背面焊缝余高
	 */
	@TableField("back_weld_reinforcement")
	private Double backWeldReinforcement;

	/**
	 * 缺陷情况
	 */
	@TableField("defect_condition")
	private String defectCondition;

	/**
	 * 角焊缝凹凸度
	 */
	@TableField("concavity")
	private Double concavity;

	/**
	 * 焊脚最低
	 */
	@TableField("low_leg")
	private Double lowLeg;

	/**
	 * 焊脚最高
	 */
	@TableField("high_leg")
	private Double highLeg;

	/**
	 * 对接不平度
	 */
	@TableField("butt_unevenness")
	private Double buttUnevenness;

	/**
	 * 堆焊焊道高度差
	 */
	@TableField("welding_bead_height_difference")
	private Double weldingBeadHeightDifference;

	/**
	 * 凹下量
	 */
	@TableField("amount_of_depression")
	private Double amountOfDepression;

	/**
	 * 是否合格
	 */
	@TableField("eligibility")
	private Integer eligibility;


}
