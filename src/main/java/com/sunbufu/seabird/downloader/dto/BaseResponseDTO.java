package com.sunbufu.seabird.downloader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;

/**
 * 返回数据基类
 * 
 * @param <D>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponseDTO<D> {

    public static final int SUCCESS = 2000;

    private Integer success;
    /** 当前页码 */
    private Integer currentPage;
    /** 总页数 */
    private Integer totalPage;
    /** 数据 */
    private D data;

}
