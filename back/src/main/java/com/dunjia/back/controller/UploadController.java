package com.dunjia.back.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dunjia.back.pojo.Result;
import com.dunjia.back.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 上传图片到云端
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("image") MultipartFile image) throws IOException {
        log.info("上传文件, 文件名：{}", image.getOriginalFilename());

        // 上传到阿里云OSS
        String url = aliOSSUtils.upload(image);
        log.info("上传成功，url：{}", url);

        return Result.success(url);
    }

    // 上传信息到云端
    @RequestMapping(value = "/uploadInfo", method = RequestMethod.POST)
    public Result uploadInfo(@RequestParam("info") MultipartFile info) throws IOException {
        log.info("上传文件, 文件名：{}", info.getOriginalFilename());

        // 上传到阿里云OSS
        String url = aliOSSUtils.upload(info);
        log.info("上传成功，url：{}", url);

        return Result.success(url);
    }

//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public Result upload(@RequestParam("image") MultipartFile image) throws IOException {
//        log.info("上传文件, 文件名：{}", image.getOriginalFilename());
//
//        // 上传到本地
//        String fileName = UUID.randomUUID().toString() + image.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + "/images/";
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        image.transferTo(dest);
//        log.info("上传成功，文件路径：{}", filePath + fileName);
//
//        return Result.success(fileName);
//    }
}
