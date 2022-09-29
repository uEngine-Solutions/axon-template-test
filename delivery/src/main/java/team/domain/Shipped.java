package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Shipped extends AbstractEvent {

    private Long id;

    public Shipped(Delivery aggregate){
        super(aggregate);
    }
    public Shipped(){
        super();
    }
}
