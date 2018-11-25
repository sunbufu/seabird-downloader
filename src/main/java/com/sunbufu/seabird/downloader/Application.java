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

    @Autowired
    private SeabirdService seabirdService;

    @PostConstruct
    private void getPicture() {
        if (argList.isEmpty() || argList.contains("pic") || argList.contains("picture")) {
            System.out.println("开始获取相片信息");
            List<PictureDTO> pictureList = seabirdService.getAllPicture();
            System.out.println("相片共计[" + pictureList.size() + "]张");
            seabirdService.downloadBaseDataList(pictureList, SeabirdService.DIR_PIC);
        }

        if (argList.isEmpty() || argList.contains("vid") || argList.contains("video")) {
            System.out.println("开始获取视频信息");
            List<VideoDTO> videoList = seabirdService.getAllVideo();
            System.out.println("视频共计[" + videoList.size() + "]段");
            seabirdService.downloadBaseDataList(videoList, SeabirdService.DIR_VID);
        }
    }

    public static void main(String[] args) {
        argList = Arrays.asList(args);
        SpringApplication.run(Application.class, args);
    }
}
