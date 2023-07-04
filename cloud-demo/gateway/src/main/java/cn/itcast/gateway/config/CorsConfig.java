package cn.itcast.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    /**
     * 配置类解决跨域问题
     * # 跨域请求
     * # 域名不同：www.taobao.com 和 www.taobao.org
     * # 域名相同，端口不同 localhost:8080 和 localhost:8081
     * # 跨域问题：浏览器禁止请求发起者与服务端发生跨域Ajax请求，请求被浏览器拦截的问题
     * # 解决方案： CORS 配置文件解决跨域问题
     */
    /**
     * 1.什么是浏览器同源策略：
     * 同源策略（Same origin policy）是一种约定，它是浏览器最核心也最基本的安全功能，如果缺少了同源策略，则浏览器的正常功能可能都会受到影响。可以说 Web 是构建在同源策略基础之上的，浏览器只是针对同源策略的一种实现。
     * 它的核心就在于它认为自任何站点装载的信赖内容是不安全的。当被浏览器半信半疑的脚本运行在沙箱时，它们应该只被允许访问来自同一站点的资源，而不是那些来自其它站点可能怀有恶意的资源。
     * 所谓同源是指：域名、协议、端口相同
     * 2.为什么要有跨域限制
     * 因为存在浏览器同源策略，所以才会有跨域问题。那么浏览器是出于何种原因会有跨域的限制呢。其实不难想到，跨域限制主要的目的就是为了用户的上网安全。
     */
    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有的请求头跨域访问资源，即可以使用任意的请求头。
        corsConfiguration.addAllowedHeader("*");
        //允许所有的请求方法跨域访问资源，即可以使用任意的请求方法（如GET、POST、PUT、DELETE等）。
        corsConfiguration.addAllowedMethod("*");
        //允许所有的请求源（即域名或IP地址）跨域访问资源，即任意源都可以跨域访问该资源。
        corsConfiguration.addAllowedOrigin("*");
        // 当设置为 true 时，表示在进行跨域请求时，可以携带身份凭据，例如在发送请求时附带的 cookies、Authorization 头信息等。这对于需要在跨域请求中进行用户身份认证或授权的场景非常重要。
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source  = new UrlBasedCorsConfigurationSource();
        //对所有经过网关的请求都生效
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}
