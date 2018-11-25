package com.sunbufu.seabird.downloader.service;

import com.sunbufu.seabird.downloader.dto.BaseDataDTO;
import com.sunbufu.seabird.downloader.dto.BaseResponseDTO;
import com.sunbufu.seabird.downloader.dto.PictureDTO;
import com.sunbufu.seabird.downloader.dto.VideoDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.util.List;

/**
 * 海鸟服务
 */
public interface SeabirdService {

    /** 图片目录 */
    String DIR_PIC = "/picture/";
    /** 视频目录 */
    String DIR_VID = "/video/";

    /**
     * 指定页码的相片信息
     * 
     * @param pageNum 页码
     * @return
     */
    BaseResponseDTO<List<PictureDTO>> listPicture(int pageNum);

    /**
     * 获取全部到相片信息
     *
     * @return
     */
    List<PictureDTO> getAllPicture();

    /**
     * 指定页码的视频信息
     *
     * @param pageNum 页码
     * @return
     */
    BaseResponseDTO<List<VideoDTO>> listVideo(int pageNum);

    /**
     * 获取全部到视频信息
     *
     * @return
     */
    List<VideoDTO> getAllVideo();

    /**
     * 下载数据列表
     * 
     * @param baseDataList
     * @param dirMark
     */
    void downloadBaseDataList(List<? extends BaseDataDTO> baseDataList, String dirMark);

}
