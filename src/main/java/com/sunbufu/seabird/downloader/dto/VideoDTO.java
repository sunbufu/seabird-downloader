package com.sunbufu.seabird.downloader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 视频数据类
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoDTO extends BaseDataDTO {

    private String ppath;
    private Integer psize;
    private String videoCodingType;
}
