package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class Shipped extends AbstractEvent {

    private Long id;
    private String orderId;
    private String address;
    private String options;
}


