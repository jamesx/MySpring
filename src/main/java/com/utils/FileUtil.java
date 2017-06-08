package com.utils;

import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by xsooy-pc on 2017/5/26.
 */
public class FileUtil {
    /**
     * 将指定路径下的文件读取为byte数组
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readAllBytes(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * 将byte数组写入指定路径下
     * @param path
     * @param bytes
     */
    public static void write (String path,byte[] bytes)throws Exception{
        Files.write(Paths.get(path),bytes);
    }

    /**
     * 将指定文件夹下的文件复制到另一个路径下
     * @param from
     * @param to
     * @throws Exception
     */
    public static void copy(String from,String to)throws Exception{
        Files.copy(Paths.get(from),Paths.get(to));
    }

    /**
     * 将指定路径下的文件剪切到另一个路径下
     * @param from
     * @param to
     * @throws Exception
     */
    public static void move(String from,String to)throws  Exception{
        Files.move(Paths.get(from),Paths.get(to));
    }

    /**
     * 删除一个路径
     * @param path
     * @throws Exception
     */
    public static void deleteIfExists(String path)throws Exception{
        Files.deleteIfExists(Paths.get(path));
    }

    /**
     * 删除一个文件夹及该文件夹下的所有子孙文件
     * 文件夹不存在时会报错
     * @param path
     * @throws Exception
     */
    public static void deleteDirectory(String path)throws Exception{
        FileUtils.deleteDirectory(new File(path));
    }
    /**
     * 删除一个文件夹及该文件夹下的所有子孙文件
     * 文件夹不存在时不会报错
     * @param path
     * @throws Exception
     */
    public static void deleteQuietly(String path)throws Exception{
        FileUtils.deleteQuietly(new File(path));
    }
    /**
     * 清空一个文件夹
     * @param path
     * @throws Exception
     */
    public static void cleanDirectory(String path)throws Exception{
        FileUtils.cleanDirectory(new File(path));
    }

    /**
     * 创建一级目录
     * @param path
     * @throws Exception
     */
    public static void createDirectory(String path)throws  Exception{
        Files.createDirectory(Paths.get(path));
    }

    /**
     * 创建多级目录
     * @param path
     * @throws Exception
     */
    public static void createDirectories(String path)throws  Exception{
        Files.createDirectories(Paths.get(path));
    }

    /**
     * 创建一个文件
     * @param file
     * @throws Exception
     */
    public static void createFile(String file)throws  Exception{
        Files.createFile(Paths.get(file));
    }

    /**
     * 获取项目在tomcat下的工作空间路径
     * @return
     * @throws Exception
     */
    public static String rootPath()throws  Exception{
        return FileUtil.class.getResource("/").toString().replace("file:", "").replace("WEB-INF/classes/", "");
    }

    /**
     * 获取绝对路径
     * @param path
     * @return
     * @throws Exception
     */
    public static String workPath(String path)throws Exception{
        File file=new File(path);
        return file.getAbsolutePath();
    }
	    /**
     * 获取项目URL前缀
     * @param request
     * @return
     * @throws Exception
     */
    public static String basePath(HttpServletRequest request)throws Exception{
      String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
				return basePath;
    }
}
