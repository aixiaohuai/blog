package com.medusa.blog.controller;

import com.medusa.blog.common.interceptors.LoginRequired;
import com.medusa.blog.model.File;
import com.medusa.blog.service.FileService;
import com.medusa.blog.vo.Data;
import com.medusa.blog.vo.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;


    @Transactional
    @PostMapping("")
    @LoginRequired
    public FileResponse upLoad(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = ((MultipartHttpServletRequest) request);
        MultiValueMap<String, MultipartFile> fileMap = multipartHttpServletRequest.getMultiFileMap();
        List<File> res=fileService.upload(fileMap);
        FileResponse fileResponse = new FileResponse();
        fileResponse.setCode(0);
        fileResponse.setMessage("成功");
        Data data=new Data();
        data.setSrc("http://192.168.93.129/assets/"+res.get(0).getPath());
        data.setTitle("myGirl");
        fileResponse.setData(data);
        return fileResponse;
    }

    @PostMapping("/delete")
    public FileResponse delete(HttpServletRequest request) {
        String imgPath=request.getParameter("imgpath");
        if(!imgPath.contains("layui")){
            String path = imgPath.substring(imgPath.indexOf("assets"));
            path = path.substring(path.indexOf("/") + 1);
            path = path.replace("/", "\\");
           /*
           * 暂不处理
           * */
            //fileService.deleteByPath(path);
        }
        return new FileResponse();
    }
}
