package com.sunbufu.seabird.downloader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 相片数据类
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PictureDTO extends BaseDataDTO {
}
