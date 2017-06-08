package com.main.core.config.mvc;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.http.HttpServlet;

import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by superMan791 on 2017/5/6.
 */
public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static Logger log=LogManager.getLogger();
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return new Class<?>[]{
                RootConfig.class
        };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub

        return null;

    }

    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[]{"/"};
    }
    protected Filter[] getServletFilters()
    {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        //用druid监听spring
        WebStatFilter druidWebStatFilter=new WebStatFilter();
        druidWebStatFilter.setSessionStatEnable(true);
        druidWebStatFilter.setProfileEnable(true);
        return new Filter[] {characterEncodingFilter,druidWebStatFilter};
    }
    /**
     * 配置 muiltpart文件上传,上传文件路径,最大上传文件大小,最大请求大小,是否写入磁盘
     */
    @Override
    protected void customizeRegistration(Dynamic registration)
    {
        registration.setMultipartConfig(new MultipartConfigElement(LOCATION,MAX_FILE_SIZE,MAX_REQUEST_SIZE,FILE_SIZE_THRESHOLD));
    }

    private MultipartConfigElement getMultipartConfigElement()
    {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
    /**
     * 添加Servlet
     */
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
        try {
            ServletRegistration.Dynamic dynamic= servletContext.addServlet("DruidStatView", (HttpServlet)Class.forName("com.alibaba.druid.support.http.StatViewServlet").newInstance());
            dynamic.addMapping("/druid/*");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private static final String LOCATION = "/"; // Temporary location where files will be stored

    private static final long MAX_FILE_SIZE = 209715200; // 5MB : Max file size.
    // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 419430400; // 20MB : Total request size containing Multi part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
}
