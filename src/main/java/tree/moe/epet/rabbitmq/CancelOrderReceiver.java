package tree.moe.epet.rabbitmq;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tree.moe.epet.entity.OrderList;
import tree.moe.epet.entity.OrderlistVO;
import tree.moe.epet.service.OrderListService;

@Component
@RabbitListener(queues = "mall.order.cancel")//监听消息队列mall.order.cancel获取对应消息
public class CancelOrderReceiver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OrderListService orderService;
    
    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("receive delay message orderId:{}", orderId);
        OrderList order = orderService.getOrderlistByid(orderId);
        if(order !=null && order.getOrder_status() == 0) {
        	OrderlistVO orderlistVO = new OrderlistVO();
        	orderlistVO.setId(orderId);
        	orderlistVO.setOrder_status(-1);
        	orderlistVO.setOrder_settlement_time(new Date());
        	orderService.updateOrderListStatusById(orderlistVO);
        	LOGGER.info("canceled orderId:{}", orderId);
        }
    }
}
