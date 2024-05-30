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
 * @date 2024-05-15 18:14:44
 */
@Data
@TableName("circulation_card")
public class CirculationCardEntity extends BaseEntity implements Serializable {
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
	 * 考试项目
	 */
	@TableField("exam_items")
	private String examItems;

	/**
	 * 试件编号
	 */
	@TableField("test_piece_code")
	private String testPieceCode;

	/**
	 * 试件材质
	 */
	@TableField("test_piece_material")
	private String testPieceMaterial;

	/**
	 * 试件规格
	 */
	@TableField("test_piece_specifications")
	private String testPieceSpecifications;

	/**
	 * 试件入库号
	 */
	@TableField("test_piece_storage_number")
	private Integer testPieceStorageNumber;

	/**
	 * 试件数量
	 */
	@TableField("test_pieces_num")
	private Integer testPiecesNum;

	/**
	 * 焊材牌号	
	 */
	@TableField("welding_consumables_grade")
	private String weldingConsumablesGrade;

	/**
	 * 焊材规格
	 */
	@TableField("welding_consumables_specifications")
	private String weldingConsumablesSpecifications;

	/**
	 * 焊材入库号	
	 */
	@TableField("welding_material_storage_number")
	private Integer weldingMaterialStorageNumber;

	/**
	 * 焊材数量
	 */
	@TableField("welding_consumable_num")
	private Integer weldingConsumableNum;

	/**
	 * 领用人
	 */
	@TableField("recruiter")
	private String recruiter;

	/**
	 * 焊材id
	 */
	@TableField("welding_consumables_id")
	private Long weldingConsumablesId;

	/**
	 * 试件id
	 */
	@TableField("specimen_id")
	private Long specimenId;


}
