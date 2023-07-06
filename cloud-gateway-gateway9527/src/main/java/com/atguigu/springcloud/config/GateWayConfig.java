package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/news时会自动转发到地址：http://news.baidu.com/
     * @param builder
     * @return
     */
    @Bean
    //构建路径定位器
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_atguigu", r -> r.path("/news").uri("http://news.baidu.com")).build();
        return routes.build();
    }
}
