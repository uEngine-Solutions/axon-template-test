package team.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.*;
import org.axonframework.spring.stereotype.Aggregate;

import org.springframework.beans.BeanUtils;
import java.util.List;



@Aggregate(snapshotTriggerDefinition = "orderAggregateSnapshotTriggerDefinition")
public class Order {

    @AggregateIdentifier
    private Long id;
    private String productId;
    private Long qty;
    private String address;

    private String status;

    
    protected Order(){}

    public Order(Long id){
        this.id = id;
    }

    @CommandHandler
    public Order(OrderCommand command){
        this.id = command.getId();

        OrderPlaced event = new OrderPlaced();
        BeanUtils.copyProperties(command, event);     
        
        apply(event);
    }

    // @CommandHandler
    // public void handle(OrderCommand command){
    //     // TODO send Event
    //     // AggregateLifecycle.apply( Event );

    //     }

    @CommandHandler
    public void handle(OrderCancelCommand command){

        OrderCanceled orderCanceledEvent = new OrderCanceled();
        BeanUtils.copyProperties(command, orderCanceledEvent);
        apply(orderCanceledEvent);

    }


    @EventSourcingHandler
    public void on(OrderPlaced event) {
        BeanUtils.copyProperties(event, this);
    }


    @EventSourcingHandler
    public void on(OrderCanceled event) {
        this.status = "DELETED";
        markDeleted();
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
