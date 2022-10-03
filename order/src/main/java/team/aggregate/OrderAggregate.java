package team.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import org.springframework.beans.BeanUtils;
import java.util.List;


import team.command.*;
import team.event.*;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private Long id;
    private String productId;
    private Long qty;
    private String address;

    private OrderAggregate(){}

    @CommandHandler
    public void handle(OrderCommand command){
        // TODO send Event
        // AggregateLifecycle.apply( Event );

        }
    @CommandHandler
    public void handle(OrderCancelCommand command){
        // TODO send Event
        // AggregateLifecycle.apply( Event );

        }


    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        BeanUtils.copyProperties(event, this);
    }


    @EventSourcingHandler
    public void on(OrderCanceledEvent event) {
        BeanUtils.copyProperties(event, this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
