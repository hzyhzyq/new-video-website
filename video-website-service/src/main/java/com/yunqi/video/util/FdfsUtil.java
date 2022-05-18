package com.yunqi.video.util;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class FdfsUtil {


    private FastFileStorageClient fastFileStorageClient;

    public FdfsUtil(FastFileStorageClient fastFileStorageClient) {
        this.fastFileStorageClient = fastFileStorageClient;
    }

    public String getFileType(MultipartFile file) {
        if (file == null) {
            throw new RuntimeException("非法文件");
        }
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileType;
    }


    //上传
    public String upload(MultipartFile file) throws IOException {
        //可以配置元数据
        Set<MetaData> metaDataSet = new HashSet<>();
        String fileType = getFileType(file);
        InputStream is = null;
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), fileType, metaDataSet);
        return storePath.getFullPath();
    }
    //删除
    public void delete(String name){
        fastFileStorageClient.deleteFile(name);
    }


}
