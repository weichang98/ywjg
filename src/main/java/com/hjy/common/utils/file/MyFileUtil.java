package com.hjy.common.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class MyFileUtil {

    //判断是否为图片文件
    public static boolean PictureFileUtil(MultipartFile file){
        if(file !=null){
            //文件全名
            String fileName = file.getOriginalFilename();
            //文件名后缀，即文件类型
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            if(suffixName.equals(".jpg")){
                return true;
            }else {
               return false;
            }
        }
        return false;
    }
    //
    public static String[] FileUtil(String fenlei, MultipartFile[] files, String IDcard){
        StringBuffer dbfilePathsb = new StringBuffer();
        StringBuffer dbfilePathsb2 = new StringBuffer();
        String[]strings = new String[2];
        if(files!=null && files.length>0){
            for(int i = 0,j = 0,k = 0;i < files.length; i++){
                MultipartFile file = files[i];
                if(!file.isEmpty()){
                    //获取时间
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
                    String time = sdf.format(date.getTime());
                    String times[] = time.split("_");
                    //拿到的该文件名
                    String fileName = file.getOriginalFilename();
                    //判断是否为图片文件
                    if(PictureFileUtil(file)){
                        j++;
                        //重命名文件名
                        if(j>0){
                            fileName = times[0] + "_" + times[1] + "_" + times[2] + "_"+IDcard+"_"+j+".jpg";
                            if(j==1){
                                dbfilePathsb.append(fileName);
                            }else {
                                dbfilePathsb.append("-"+fileName);
                            }
                        }
                    }else {
                        //文件名后缀，即文件类型
                        String suffixName = file.getOriginalFilename().substring(fileName.lastIndexOf("."));
                        k++;
                        //重命名文件名
                        if(k>0){
                            fileName = times[0] + "_" + times[1] + "_" + times[2] + "_"+IDcard+"_"+k+suffixName;
                            if(k==1){
                                dbfilePathsb2.append(fileName);
                            }else {
                                dbfilePathsb2.append("-"+fileName);
                            }
                        }
                    }
                    //调用文件上传工具类
                    String filePath = FileUpload(fenlei,fileName,file);
                    if(i==0){
                        dbfilePathsb.insert(0,filePath+"applyBook_");
                    }else if(i==1){
                        dbfilePathsb2.insert(0,filePath+"codeCertificates_");
                    }

                }
            }
        }
        //为文件1路径及其名称
        strings[0] =dbfilePathsb.toString();
        //为文件2路径及其名称
        strings[1] =dbfilePathsb2.toString();
        return strings;
    }

    //文件上传工具
    public static String FileUpload(String fenlei, String fileName, MultipartFile file){
        //获取时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String time = sdf.format(date.getTime());
        String times[] = time.split("_");
        //1.创建目录
        StringBuffer dirpath = new StringBuffer(times[0] + "/" + times[1] + "/" + times[2]);
        String filePath = null;
        if(fenlei.equals("红名单")){
            dirpath.insert(0,"D:/aTest/ywjg/redList/");
            filePath = "D:/aTest/ywjg/redList/";
        }else if(fenlei.equals("黑名单")){
            dirpath.insert(0,"D:/aTest/ywjg/blackList/");
            filePath = "D:/aTest/ywjg/blackList/";
        }
        File targetFile = new File(dirpath.toString());
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File saveFile = new File(targetFile, fileName);
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
        }
        return filePath;
    }
}
