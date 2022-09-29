package team.infra;
import team.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class ClaimHateoasProcessor implements RepresentationModelProcessor<EntityModel<Claim>>  {

    @Override
    public EntityModel<Claim> process(EntityModel<Claim> model) {

        
        return model;
    }
    
}
