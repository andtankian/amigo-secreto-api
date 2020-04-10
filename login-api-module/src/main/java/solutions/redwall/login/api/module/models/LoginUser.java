package solutions.redwall.login.api.module.models;

import br.com.andrewribeiro.ribrest.core.annotations.RibrestEndpointConfigurator;
import br.com.andrewribeiro.ribrest.core.annotations.RibrestModel;
import br.com.andrewribeiro.ribrest.core.model.AbstractModel;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import solutions.redwall.login.api.module.commands.GenerateJWTAfterLoginCommand;
import solutions.redwall.login.api.module.commands.ValidateLoginUserCommand;
import solutions.redwall.login.api.module.miners.LoginUserMiner;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
@RibrestModel(
        defaultEndpointsConfigurators = {
            @RibrestEndpointConfigurator(
                    method="POST",
                    miner = LoginUserMiner.class,
                    beforeCommands = ValidateLoginUserCommand.class,
                    afterCommands = GenerateJWTAfterLoginCommand.class
            )
        }
)
@Entity
public class LoginUser extends AbstractModel {
    
    @Column(columnDefinition = "text")
    private String currentJTW;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public String getCurrentJTW() {
        return currentJTW;
    }

    public void setCurrentJTW(String currentJTW) {
        this.currentJTW = currentJTW;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
