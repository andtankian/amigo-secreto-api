package solutions.redwall.secret.santa.api.models;

import br.com.andrewribeiro.ribrest.core.annotations.RibrestEndpointConfigurator;
import br.com.andrewribeiro.ribrest.core.annotations.RibrestModel;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import solutions.redwall.secret.santa.api.commands.ClearExcessiveFriendCommand;
import solutions.redwall.secret.santa.api.commands.CustomMergeModelToPersistedModelCommand;
import solutions.redwall.secret.santa.api.commands.CustomPersistenceModelLoaderCommand;
import solutions.redwall.secret.santa.api.commands.RaffleCommand;
import solutions.redwall.secret.santa.api.commands.ValidateEmailProfileCommand;
import solutions.redwall.secret.santa.api.dispatchers.CustomLotteryTimeDispatcher;
import solutions.redwall.secret.santa.api.miners.NewProfileMiner;
import solutions.redwall.users.api.module.commands.EncryptPassCommand;
import solutions.redwall.users.api.module.commands.ValidateNewUserCommand;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
@Entity
@RibrestModel(defaultEndpointsConfigurators = {
    @RibrestEndpointConfigurator(
            method = "POST",
            miner = NewProfileMiner.class,
            beforeCommands = {
                ValidateNewUserCommand.class,
                EncryptPassCommand.class
            },
            afterCommands = {
                RaffleCommand.class,
                ClearExcessiveFriendCommand.class
            }
    ),
    @RibrestEndpointConfigurator(
            afterCommands = ClearExcessiveFriendCommand.class
    ),
    @RibrestEndpointConfigurator(
            method = "PUT",
            path = "{id}",
            beforeCommands = {
                CustomPersistenceModelLoaderCommand.class,
                ValidateEmailProfileCommand.class,
                CustomMergeModelToPersistedModelCommand.class
            },
            afterCommands = ClearExcessiveFriendCommand.class
    )
}, endpointsConfigurators = {
    @RibrestEndpointConfigurator(
            method = "GET",
            path = "{id}",
            afterCommands = ClearExcessiveFriendCommand.class
    ),
    @RibrestEndpointConfigurator(
            method = "GET",
            path = "lotterytime",
            dispatcher = CustomLotteryTimeDispatcher.class
    )
})
public class Profile extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private Set<Suggestion> suggestions;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile friend;

    public Set<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Set<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public Profile getFriend() {
        return friend;
    }

    public void setFriend(Profile friend) {
        this.friend = friend;
    }
}
