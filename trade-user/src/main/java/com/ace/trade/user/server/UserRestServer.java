package com.ace.trade.user.server;

import com.ace.trade.common.contants.TradeEnums;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class UserRestServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(TradeEnums.RestServerEnum.USERS.getPort());

        ServletContextHandler springMvcHanlder = new ServletContextHandler();
        springMvcHanlder.setContextPath("/" + TradeEnums.RestServerEnum.USERS.getContextPath());
        XmlWebApplicationContext ctx = new XmlWebApplicationContext();
        ctx.setConfigLocation("classpath:xml/spring-web.xml");
        springMvcHanlder.addEventListener(new ContextLoaderListener(ctx));
        springMvcHanlder.addServlet(new ServletHolder(new DispatcherServlet(ctx)), "/*");

        server.setHandler(springMvcHanlder);
        server.start();
        server.join();

    }
}
