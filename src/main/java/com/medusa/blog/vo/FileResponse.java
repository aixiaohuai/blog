package com.medusa.blog.vo;

import com.medusa.blog.model.File;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileResponse {
    Integer code;
    String message;
    Data data;
}
