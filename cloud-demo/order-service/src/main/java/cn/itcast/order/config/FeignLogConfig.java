package cn.itcast.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 如果全局配置
//@EnableFeignClients(defaultConfiguration = FeignClientConfiguration.class)
//如果局部配置
//@FeignClient(value = "userservice", configuration = FeignClientConfiguration.class)
public class FeignLogConfig {
    @Bean
    public Logger.Level logConfig(){
        return Logger.Level.BASIC;
    }
}
