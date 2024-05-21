package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 *
 * 通用接口
 */
@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);

        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 截取后缀名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 通过UUID构造新的唯一文件名
            String objectName = UUID.randomUUID().toString() + extension;

            file.transferTo(new File("F:\\code\\sky-take-out-project\\sky-take-out\\sky-server\\src\\upload\\" + objectName));
            String filePath =  "http://localhost:8080/" + objectName;
            return Result.success(filePath);
        } catch (Exception e) {
            log.error("文件上传失败:{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
