package com.sunbufu.seabird.downloader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 基础数据类
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDataDTO {

    /** 创建时间 */
    private String created;
    /** 路径 */
    private String path;
    /** 大小 */
    private Long size;
    /** 标题 */
    private String title;
    /** 预览图路径 */
    private String thumb;
}
