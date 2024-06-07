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
 * @date 2024-05-18 18:49:47
 */
@Data
@TableName("bend_commission")
public class BendCommissionEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 姓名
	 */
	@TableField("principal_ID")
	private String principalID;

	/**
	 * 委托单位
	 */
	@TableField("requester")
	private String requester;

	/**
	 * 送样时间
	 */
	@TableField("sample_delivery_time")
	private Date sampleDeliveryTime;

	/**
	 * 委托人
	 */
	@TableField("client")
	private String client;

	/**
	 * 样品数量
	 */
	@TableField("samples_num")
	private Integer samplesNum;

	/**
	 * 品名
	 */
	@TableField("product_name")
	private String productName;

	/**
	 * 报告形式
	 */
	@TableField("report_form")
	private String reportForm;

	/**
	 * 图面
	 */
	@TableField("drawing")
	private String drawing;

	/**
	 * 依据标准
	 */
	@TableField("according_standard")
	private String accordingStandard;

	/**
	 * 试验项目
	 */
	@TableField("pilot_projects")
	private String pilotProjects;

	/**
	 * 计划完成日期
	 */
	@TableField("planned_completion")
	private Date plannedCompletion;

	/**
	 * 规格
	 */
	@TableField("specification")
	private String specification;

	/**
	 * 检件编号
	 */
	@TableField("inspection_num")
	private String inspectionNum;

	/**
	 * 材质
	 */
	@TableField("material")
	private String material;

	/**
	 * 接收人
	 */
	@TableField("receiver")
	private Long receiver;

	/**
	 * 接收日期
	 */
	@TableField("receive_date")
	private Date receiveDate;

	/**
	 * 委托日期
	 */
	@TableField("client_date")
	private Long clientDate;


	/**
	 * 考试台账ID
	 */
	@TableField("examination_ledger_id")
	private Long ExaminationLedgerID;

}
