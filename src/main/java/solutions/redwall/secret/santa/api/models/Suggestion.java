package solutions.redwall.secret.santa.api.models;

import br.com.andrewribeiro.ribrest.core.annotations.RibrestModel;
import br.com.andrewribeiro.ribrest.core.model.AbstractModel;
import br.com.andrewribeiro.ribrest.core.annotations.RibrestEndpointConfigurator;
import solutions.redwall.secret.santa.api.commands.CustomLoadModelPersistentChildrenCommand;
import solutions.redwall.secret.santa.api.miners.ClearProfileSuggestionsMiner;
import solutions.redwall.secret.santa.api.daos.ClearProfileSuggestionsDAO;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
@RibrestModel(
    defaultEndpointsConfigurators = {
         @RibrestEndpointConfigurator(method = "POST", beforeCommands = {CustomLoadModelPersistentChildrenCommand.class})
    },
    endpointsConfigurators = {
        @RibrestEndpointConfigurator(
            path = "profile/{profileId}/delete",
            method = "DELETE",
            miner = ClearProfileSuggestionsMiner.class,
            dao = ClearProfileSuggestionsDAO.class
        )
    }
)
@Entity
public class Suggestion extends AbstractModel {

    @ManyToOne(cascade = CascadeType.ALL)
    Profile profile;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Profile getProfile(){
        return profile;
    }
    public void setProfile(Profile profile){
        this.profile = profile;
    }
}
