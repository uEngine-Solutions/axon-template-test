package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ShippingCanceled extends AbstractEvent {

    private Long id;

    public ShippingCanceled(Delivery aggregate){
        super(aggregate);
    }
    public ShippingCanceled(){
        super();
    }
}
