package com.dunjia.back.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dunjia.back.pojo.Result;
import com.dunjia.back.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    AliOSSUtils aliOSSUtils = new AliOSSUtils();

    public UploadController() throws ClientException {
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(MultipartFile image) throws IOException {
        log.info("上传文件, 文件名：{}", image.getOriginalFilename());

        // 上传到阿里云OSS
        String url = aliOSSUtils.upload(image);
        log.info("上传成功，url：{}", url);

        return Result.success(url);
    }
}
