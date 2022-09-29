package team.domain;

import team.domain.ClaimIssued;
import team.ClaimApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Claim_table")
@Data

public class Claim  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String productId;
    
    
    
    
    
    private String claimContent;
    
    
    
    
    
    private String orderId;

    @PostPersist
    public void onPostPersist(){


        ClaimIssued claimIssued = new ClaimIssued(this);
        claimIssued.publishAfterCommit();

    }

    public static ClaimRepository repository(){
        ClaimRepository claimRepository = ClaimApplication.applicationContext.getBean(ClaimRepository.class);
        return claimRepository;
    }






}
