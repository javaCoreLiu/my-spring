package com.bootstrap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDispatchServlet extends HttpServlet {
    
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
        super.init();
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
    
}
