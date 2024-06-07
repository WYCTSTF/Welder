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
 * @date 2024-05-18 17:24:15
 */
@Data
@TableName("processing_entrustment")
public class ProcessingEntrustmentEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId("id")
	private Long id;

	/**
	 * 试件编号
	 */
	@TableField("test_piece_num")
	private Integer testPieceNum;

	/**
	 * 台账编号id
	 */
	@TableField("examination_ledger_id")
	private Long examinationLedgerId;

	/**
	 * 项目名称
	 */
	@TableField("project_name")
	private String projectName;

	/**
	 * 加工时间
	 */
	@TableField("processing_time")
	private Date processingTime;

	/**
	 * 委托时间
	 */
	@TableField("entrustment_time")
	private Date entrustmentTime;

	/**
	 * 完成时间
	 */
	@TableField("finish_time")
	private Date finishTime;

	/**
	 * 材质
	 */
	@TableField("material")
	private String material;

	/**
	 * 规格
	 */
	@TableField("specification")
	private String specification;

	/**
	 * 面弯一件
	 */
	@TableField("one_piece_face_curvature")
	private String onePieceFaceCurvature;

	/**
	 * 背弯一件
	 */
	@TableField("one_piece_back_bend")
	private String onePieceBackBend;

	/**
	 * 侧弯二件
	 */
	@TableField("side_bend_two_pieces")
	private String sideBendTwoPieces;

	/**
	 * 宏观金相四件
	 */
	@TableField("four_pieces_macroscopic_metallography")
	private String fourPiecesMacroscopicMetallography;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

}
