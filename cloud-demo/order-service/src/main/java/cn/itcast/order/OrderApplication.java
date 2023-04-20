package cn.itcast.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    //默认轮询实现类 ZoneAvoidanceRule 以区域可用的服务器为基础进行服务器的选择。
    // 使用Zone对服务器进行分类，这个Zone可以理解为一个机房、一个机架等。而后再对Zone内的多个服务做轮询。
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    //第一种设置负载均衡 全局的配置方案，对所有服务都是随机
    @Bean
    public IRule randomRule(){
        /*随机，实现IRule的实现类都可使用*/
        return new RandomRule();
    }

}