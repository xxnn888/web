package com.awei.server.utils;


import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author AWEI
 * @version 1.0
 * @description: FastDFS工具类
 * @date 2022/4/2 11:57
 */
public class FastDFSUtils {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);

    /**
     * 初始化客户端
     * ClientGlobal.init 读取配置文件，并初始化对应的属性
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("初始化FastDFS失败",e);
        }
    }

    /**
     * @description: 上传文件
     * @param: file
     * @return
     */
    public static String[] upload(MultipartFile file){
        String name = file.getOriginalFilename();
        logger.info("文件名：",name);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try {
            //获取storage客户端
            storageClient = getStorageClient();
            //上传
            uploadResults = storageClient.upload_file(file.getBytes(),name.substring(name.lastIndexOf(".")+1),null);
        } catch (Exception e) {
            logger.error("上传文件失败",e.getMessage());
        }
        if(null ==uploadResults){
            logger.error("上传失败",storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * @description: 获取文件信息
     * @param: groupName
     * @param: remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (Exception e) {
            logger.error("文件信息获取失败",e.getMessage());
        }
        return null;
    }

    /**
     * @description: 下载文件
     * @param: groupName
     * @param: remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(fileByte);
            return inputStream;
        } catch (Exception e) {
            logger.error("文件下载失败",e.getMessage());
        }
        return null;
    }

    /**
     * @description: 删除文件
     * @param: groupName
     * @param: remoteFileName
     * @return
     */
    public static void deleteFile(String groupName,String remoteName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName,remoteName);
        } catch (Exception e) {
            logger.error("文件删除失败",e.getMessage());
        }
    }

    /** 
     * @description: 生成Storage客户端
     * @param:  
     * @return
     */ 
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * @description: 生成TrackerServer服务器
     * @param:
     * @return
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }

    /**
     * @description: 获取文件路径
     * @param:
     * @return
     */
    public static String getTrackerUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storeStorage = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("文件路径获取失败",e.getMessage());
        }
        return "http://"+storeStorage.getInetSocketAddress().getHostString()+":8888/";
    }

}
