package cn.edu.lut.welder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author wenbin
 * @email *****@mail.com
 * @date 2024-05-17 20:00:10
 */
@Data
@TableName("non_destructive_testing_order")
public class NonDestructiveTestingOrderEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 焊工姓名
	 */
	@TableField("welder_name")
	private String welderName;

	/**
	 * 身份证
	 */
	@TableField("id_card")
	private String idCard;

	/**
	 * 取证项目
	 */
	@TableField("forensic_projects")
	private String forensicProjects;

	/**
	 * 委托编号
	 */
	@TableField("principal_id")
	private String principalId;

	/**
	 * 工程名称
	 */
	@TableField("project_name")
	private String projectName;

	/**
	 * 工程编号
	 */
	@TableField("project_number")
	private String projectNumber;

	/**
	 * 检件名称
	 */
	@TableField("inspection_name")
	private String inspectionName;

	/**
	 * 检测方法 RT PT
	 */
	@TableField("detection_method")
	private String detectionMethod;

	/**
	 * 检测标准
	 */
	@TableField("testing_standards")
	private String testingStandards;

	/**
	 * 合格级别
	 */
	@TableField("eligible_level")
	private String eligibleLevel;

	/**
	 * 检件总量
	 */
	@TableField("total_inspected_items")
	private Integer totalInspectedItems;

	/**
	 * 检测比例
	 */
	@TableField("detection_ratio")
	private String detectionRatio;

	/**
	 * 检测数量
	 */
	@TableField("test_quantity")
	private Integer testQuantity;

	/**
	 * 检测材质
	 */
	@TableField("test_material")
	private String testMaterial;

	/**
	 * 检测规格
	 */
	@TableField("test_specification")
	private String testSpecification;

	/**
	 * 检测方法
	 */
	@TableField("welding_method")
	private String weldingMethod;

	/**
	 * 检测编号
	 */
	@TableField("inspection_number")
	private Integer inspectionNumber;

	/**
	 * 委托人
	 */
	@TableField("client")
	private String client;

	/**
	 * 委托日期
	 */
	@TableField("client_date")
	private Date clientDate;

	/**
	 * 接收人
	 */
	@TableField("receiver")
	private String receiver;

	/**
	 * 接收日期
	 */
	@TableField("receiver_date")
	private Date receiverDate;

	/**
	 * 编号
	 */
	@TableField("code")
	private String code;


}
