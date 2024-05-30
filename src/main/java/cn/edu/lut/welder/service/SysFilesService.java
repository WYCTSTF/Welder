package cn.edu.lut.welder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.lut.welder.common.utils.DataResult;
import cn.edu.lut.welder.entity.SysFilesEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传 服务类
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysFilesService extends IService<SysFilesEntity> {

    DataResult saveFile(MultipartFile file);

    void removeByIdsAndFiles(List<String> ids);
}

