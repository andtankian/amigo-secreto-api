package solutions.redwall.secret.santa.api.miners;

import br.com.andrewribeiro.ribrest.services.miner.AbstractMiner;
import br.com.andrewribeiro.ribrest.services.miner.RequestMaps;
import org.glassfish.jersey.server.ContainerRequest;
import solutions.redwall.secret.santa.api.models.Suggestion;
import solutions.redwall.secret.santa.api.models.Profile;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class ClearProfileSuggestionsMiner extends AbstractMiner {
    
    @Override
    public void mineRequest(ContainerRequest containerRequest) {
        super.mineRequest(containerRequest); 

        RequestMaps requestMaps = flowContainer.getRequestMaps();

        Profile profile = new Profile();
        profile.setId(Long.parseLong(requestMaps.getPathMap().getFirst("profileId")));

        Suggestion suggestion = (Suggestion) flowContainer.getModel();
        suggestion.setProfile(profile);       
    }
    
}
