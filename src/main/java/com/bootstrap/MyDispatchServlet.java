package com.bootstrap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootstrap.annotation.BasePackage;

@BasePackage({"com.bootstrap.controller", "com.bootstrap.service" })
public class MyDispatchServlet extends HttpServlet {
    
    Set<String> classPath = new HashSet<String>();
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Servlet加载顺序
     * @ 1,扫描指定包下的java文件
     * @ 2,装载beans
     * @ 3,url映射
     */
    @Override
    public void init() throws ServletException {
        System.out.println("=====================init() start=====================");
        this.scanBasePackage();
        System.out.println("=====================init() end=====================");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    
    private void scanBasePackage() {
        // 类绝对路径
        URL clazzPath = this.getClass().getClassLoader().getResource("/");
        System.out.println(clazzPath.getFile());
        Class<?> clazz = this.getClass();
        // 获取扫描包
        if (clazz.isAnnotationPresent(BasePackage.class)) {
            BasePackage basePackage = clazz.getAnnotation(BasePackage.class);
            String[] packageNames = basePackage.value();
            for (String packageName : packageNames) {
                initAnnotationClass(clazzPath + packageName.replaceAll("\\.", "/"));
            }
        } else {
            return;
        }
    }
    
    private void initAnnotationClass(String packageUrl) {
        System.out.println(packageUrl);
        File file = new File(packageUrl);
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            for (File childFile : childFiles) {
                initAnnotationClass(packageUrl + "/" + childFile.getName());
            }
        } else {
            System.out.println("packageUrl = " + packageUrl);
            classPath.add(packageUrl);
        }
    }
    
}
