package com.sunbufu.seabird.downloader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sunbufu.seabird.downloader.dto.PictureDTO;
import com.sunbufu.seabird.downloader.dto.VideoDTO;
import com.sunbufu.seabird.downloader.service.SeabirdService;

@SpringBootApplication
public class Application {

    private static List<String> argList;

    private static final String PICTURE = "pic";
    private static final String VIDEO = "vid";

    @Autowired
    private SeabirdService seabirdService;

    @PostConstruct
    private void run() {
        System.out.println("argList=" + argList);

        if (argList.contains(PICTURE) || argList.contains(VIDEO)) {
            if (argList.contains(PICTURE)) {
                downloadPictures();
            }
            if (argList.contains(VIDEO)) {
                downloadViodes();
            }
        } else {
            downloadPictures();
            downloadViodes();
        }

    }

    /**
     * 下载图片信息
     */
    private void downloadPictures() {
        System.out.println("开始获取相片信息");
        List<PictureDTO> pictureList = seabirdService.getAllPicture();
        System.out.println("相片共计[" + pictureList.size() + "]张");
        seabirdService.downloadBaseDataList(pictureList, SeabirdService.DIR_PIC);
    }

    /**
     * 下载视频信息
     */
    private void downloadViodes() {
        System.out.println("开始获取视频信息");
        List<VideoDTO> videoList = seabirdService.getAllVideo();
        System.out.println("视频共计[" + videoList.size() + "]段");
        seabirdService.downloadBaseDataList(videoList, SeabirdService.DIR_VID);
    }

    public static void main(String[] args) {
        argList = Arrays.asList(args);
        SpringApplication.run(Application.class, args);
    }
}
