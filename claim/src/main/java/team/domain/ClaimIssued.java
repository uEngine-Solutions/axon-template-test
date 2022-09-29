package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ClaimIssued extends AbstractEvent {

    private Long id;
    private String productId;
    private String claimContent;
    private String orderId;

    public ClaimIssued(Claim aggregate){
        super(aggregate);
    }
    public ClaimIssued(){
        super();
    }
}
