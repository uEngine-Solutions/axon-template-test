package team.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import team.aggregate.OrderAggregate;
import team.command.OrderCancelCommand;
import team.event.OrderCanceledEvent;
import team.event.OrderPlacedEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ProcessingGroup("orders")
public class OrdersEventHandler {

    private final Map<Long, OrderAggregate> orders = new HashMap<>();

    @EventHandler
    public void on(OrderPlacedEvent event) {
        Long orderId = event.getId();
        orders.put(orderId, new OrderAggregate(orderId));
    }

    @EventHandler
    public void on(OrderCanceledEvent event) {
        // orders.computeIfPresent(event.getId(), (orderId, order) -> {
        //     orders.rem
        //     return order;
        // });

        orders.remove(event.getId());
    }

    // @EventHandler
    // public void on(ProductCountIncrementedEvent event) {
    //     orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
    //         order.incrementProductInstance(event.getProductId());
    //         return order;
    //     });
    // }

    // @EventHandler
    // public void on(ProductCountDecrementedEvent event) {
    //     orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
    //         order.decrementProductInstance(event.getProductId());
    //         return order;
    //     });
    // }

    // @EventHandler
    // public void on(ProductRemovedEvent event) {
    //     orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
    //         order.removeProduct(event.getProductId());
    //         return order;
    //     });
    // }

    // @EventHandler
    // public void on(OrderConfirmedEvent event) {
    //     orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
    //         order.setOrderConfirmed();
    //         return order;
    //     });
    // }

    // @EventHandler
    // public void on(OrderShippedEvent event) {
    //     orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
    //         order.setOrderShipped();
    //         return order;
    //     });
    // }

    @QueryHandler
    public List<OrderAggregate> handle(FindAllOrdersQuery query) {
        return new ArrayList<>(orders.values());
    }
}