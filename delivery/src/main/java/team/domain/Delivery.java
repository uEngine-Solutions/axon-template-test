package team.domain;

import team.domain.ShippingCanceled;
import team.domain.Shipped;
import team.DeliveryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String orderId;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String options;

    @PostPersist
    public void onPostPersist(){


        ShippingCanceled shippingCanceled = new ShippingCanceled(this);
        shippingCanceled.publishAfterCommit();



        Shipped shipped = new Shipped(this);
        shipped.publishAfterCommit();

    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }




    public static void shippingCancel(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        ShippingCanceled shippingCanceled = new ShippingCanceled(delivery);
        shippingCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            ShippingCanceled shippingCanceled = new ShippingCanceled(delivery);
            shippingCanceled.publishAfterCommit();

         });
        */

        
    }
    public static void ship(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        Shipped shipped = new Shipped(delivery);
        shipped.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            Shipped shipped = new Shipped(delivery);
            shipped.publishAfterCommit();

         });
        */

        
    }


}
