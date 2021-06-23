package com.medusa.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medusa.blog.mapper.FileMapper;
import com.medusa.blog.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public List<File> upload(MultiValueMap<String, MultipartFile> fileMap) {
        String dir="/assets";
        List<File> res = new ArrayList<>();
        /*检查文件
         *fileMap.isEmpty()
         *fileMap.size()
         */
        if(fileMap.isEmpty()){
            return null;
        }
        fileMap.keySet().forEach(key -> fileMap.get(key).forEach(file -> {
            // 生成文件的md5值
            byte[] bytes = null;
            try {
                bytes = file.getBytes();
            } catch (Exception e) {
                //文件读取失败
            }
            String md5 = DigestUtils.md5DigestAsHex(bytes);
            if (!file.isEmpty()) {
                File found = fileMapper.selectByMd5(md5);
                if (found != null) {
                    res.add(found);
                } else {
                    //修改文件名
                    String fileName = file.getOriginalFilename();
                    String ext = fileName.substring(fileName.lastIndexOf('.'));
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    String newFilename = uuid + ext;
                    //获取保存路径
                    Date now = new Date();
                    String format = new SimpleDateFormat("yyyy/MM/dd").format(now);
                    Path path = FileSystems.getDefault()
                            .getPath(System.getProperty("user.dir"), dir,format).toAbsolutePath();
                    System.out.println("path"+path);
                    java.io.File file1 = new java.io.File(path.toString());
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    String storePath = Paths.get(format, newFilename).toString();

                    File fileData = File.builder().
                            name(newFilename).
                            md5(md5).
                            path(storePath).
                            size(bytes.length).
                            type("LOCAL").
                            extension(ext).
                            build();
                    // 处理文件
                    String absolutePath= FileSystems.getDefault()
                            .getPath(System.getProperty("user.dir"), dir,storePath)
                            .toAbsolutePath().toString();
                    System.out.println("地址:"+absolutePath);
                    try {
                        BufferedOutputStream stream =
                                new BufferedOutputStream(new FileOutputStream(new java.io.File(absolutePath)));
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        System.out.println("文件写入失败文件写入失败文件写入失败文件写入失败文件写入失败文件写入失败文件写入失败文件写入失败");
                        // 文件写入失败
                    }
                    System.out.println(fileData);
                    fileMapper.insert(fileData);
                    res.add(fileData);
                }
            }
        }));
        return res;
    }

    public void deleteByPath(String path) {
        QueryWrapper<File> wrapper=new QueryWrapper<>();
        wrapper.eq("path",path);
        fileMapper.delete(wrapper);
        Path path1 = FileSystems.getDefault()
                .getPath(System.getProperty("user.dir"), "/assets",path).toAbsolutePath();
        FileSystemUtils.deleteRecursively(new java.io.File(path1.toString()));
    }
}
