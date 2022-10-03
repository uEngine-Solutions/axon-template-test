package team.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import team.aggregate.*;
import team.command.*;
import team.query.FindAllOrdersQuery;

@RestController
public class OrderController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
      this.commandGateway = commandGateway;
      this.queryGateway = queryGateway;
  }

  @RequestMapping(value = "/orders",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8")
  public CompletableFuture<String> order(@RequestBody OrderAggregate order)
        throws Exception {
          System.out.println("##### /order/order  called #####");

          OrderCommand command = new OrderCommand();
          BeanUtils.copyProperties(order, command);
          return commandGateway.send(command);
  }


  @RequestMapping(value = "/orders/{id}",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8")
  public void orderCancel(@PathVariable("id") Long id)
        throws Exception {
          System.out.println("##### /order/orderCancel  called #####");
          // make command
          OrderCancelCommand orderCancel = new OrderCancelCommand();
          orderCancel.setId(id);
          // TODO set attribute
          // send command
          commandGateway.send(orderCancel);
  }

  @GetMapping("/orders")
  public CompletableFuture<List<OrderAggregate>> findAllOrders() {
      return queryGateway.query(new FindAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderAggregate.class));
  }

}
