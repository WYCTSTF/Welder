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
 * @date 2024-05-14 11:24:24
 */
@Data
@TableName("examination_ledger")
public class ExaminationLedgerEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
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
	 * 编号
	 */
	@TableField("code")
	private String code;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;


}
