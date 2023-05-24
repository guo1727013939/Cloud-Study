package cn.itcast.order.service;


import cn.itcast.feign.clients.UserFeignClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用restTemplate实现服务之前的调用
     * @param orderId
     * @return
     */
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        // 2.编写url
//        String url = "http://userservice/user/" + order.getUserId();
//        // 向user服务发送请求
//        User user = restTemplate.getForObject(url, User.class);
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }

    /**
     * 使用Feign调用服务
     * @param orderId
     * @return
     */
    public Order queryOrderById(Long orderId) {
        Order order = orderMapper.findById(orderId);
        User user = userFeignClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }

}
