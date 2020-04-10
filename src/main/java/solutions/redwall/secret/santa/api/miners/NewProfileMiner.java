package solutions.redwall.secret.santa.api.miners;

import br.com.andrewribeiro.ribrest.services.miner.AbstractMiner;
import br.com.andrewribeiro.ribrest.services.miner.RequestMaps;
import org.glassfish.jersey.server.ContainerRequest;
import solutions.redwall.secret.santa.api.models.Profile;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class NewProfileMiner extends AbstractMiner {
    
    @Override
    public void mineRequest(ContainerRequest containerRequest) {
        super.mineRequest(containerRequest);
        
        RequestMaps requestMaps = flowContainer.getRequestMaps();
        
        Profile profile = (Profile) flowContainer.getModel();
        profile.setEmail(profile.getUsername());
        profile.setFullName(profile.getUsername());
        profile.setType("Comum");
        
    }
    
}
