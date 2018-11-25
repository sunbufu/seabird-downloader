package com.sunbufu.seabird.downloader.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunbufu.seabird.downloader.common.ProgressBar;
import com.sunbufu.seabird.downloader.dto.BaseDataDTO;
import com.sunbufu.seabird.downloader.dto.BaseResponseDTO;
import com.sunbufu.seabird.downloader.dto.PictureDTO;
import com.sunbufu.seabird.downloader.dto.VideoDTO;
import com.sunbufu.seabird.downloader.service.SeabirdService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SeabirdServiceImpl implements SeabirdService {

    @Value("${seabird.host}")
    private String host;
    @Value("${seabird.path.pictureList}")
    private String pictureListPath;
    @Value("${seabird.path.videoList}")
    private String videoListPath;

    @Value("${seabird.target}")
    private String targetDirectory = null;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public BaseResponseDTO<List<PictureDTO>> listPicture(int pageNum) {
        BaseResponseDTO<List<PictureDTO>> response = null;
        try {
            String url = host + pictureListPath + "?page=" + pageNum;
            String responseStr = restTemplate.getForEntity(url, String.class).getBody();
            response = objectMapper.readValue(responseStr, new TypeReference<BaseResponseDTO<List<PictureDTO>>>() {});
        } catch (Exception e) {
            log.error("listPicture error", e);
        }
        return response;
    }

    @Override
    public List<PictureDTO> getAllPicture() {
        BaseResponseDTO<List<PictureDTO>> firstResponse = listPicture(1);
        List<PictureDTO> pictureList = new ArrayList<>(firstResponse.getData());
        for (int i = 2; i <= firstResponse.getTotalPage(); i++) {
            BaseResponseDTO<List<PictureDTO>> response = listPicture(i);
            if (response != null) {
                pictureList.addAll(response.getData());
            }
        }
        return pictureList;
    }

    @Override
    public BaseResponseDTO<List<VideoDTO>> listVideo(int pageNum) {
        BaseResponseDTO<List<VideoDTO>> response = null;
        try {
            String url = host + videoListPath + "?page=" + pageNum;
            String responseStr = restTemplate.getForEntity(url, String.class).getBody();
            response = objectMapper.readValue(responseStr, new TypeReference<BaseResponseDTO<List<VideoDTO>>>() {});
        } catch (Exception e) {
            log.error("listVideo error", e);
        }
        return response;
    }

    @Override
    public List<VideoDTO> getAllVideo() {
        BaseResponseDTO<List<VideoDTO>> firstResponse = listVideo(1);
        List<VideoDTO> videoList = new ArrayList<>(firstResponse.getData());
        for (int i = 2; i <= firstResponse.getTotalPage(); i++) {
            BaseResponseDTO<List<VideoDTO>> response = listVideo(i);
            if (response != null) {
                videoList.addAll(response.getData());
            }
        }
        return videoList;
    }

    /**
     * 下载
     * 
     * @param baseDataList
     */
    public void downloadBaseDataList(List<? extends BaseDataDTO> baseDataList, String dirMark) {
        System.out.println("开始下载，目标路径：" + targetDirectory);
        ProgressBar progress = new ProgressBar();
        for (int i = 0; i < baseDataList.size(); i++) {
            BaseDataDTO baseData = baseDataList.get(i);
            String path = targetDirectory + dirMark + baseData.getTitle();
            String url = host + baseData.getPath();
            download(url, path);
            progress.showBarByPoint(100.0 * (i + 1) / baseDataList.size());
        }
        System.out.println();
        System.out.println("下载完成");
    }

    /**
     * 下载文件到指定路径
     * 
     * @param url
     * @param path
     */
    private void download(String url, String path) {
        HttpHeaders headers = new HttpHeaders();
        try {
            ResponseEntity<byte[]> exchange =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), byte[].class);
            if (exchange.getBody() != null) {
                Path filePath = Paths.get(path);
                filePath.getParent().toFile().mkdirs();
                Files.write(filePath, exchange.getBody());
            }
        } catch (Exception e) {
            log.error("download error", e);
        }
    }

}
