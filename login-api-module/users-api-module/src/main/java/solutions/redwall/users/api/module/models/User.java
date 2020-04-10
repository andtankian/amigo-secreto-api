package solutions.redwall.users.api.module.models;

import br.com.andrewribeiro.ribrest.core.annotations.RibrestEndpointConfigurator;
import br.com.andrewribeiro.ribrest.core.annotations.RibrestModel;
import br.com.andrewribeiro.ribrest.core.model.AbstractModel;
import br.com.andrewribeiro.ribrest.services.command.GetPersistentModelCommand;
import br.com.andrewribeiro.ribrest.services.command.MergeModelToPersistedModelCommand;
import javax.persistence.Column;
import javax.persistence.Entity;
import solutions.redwall.users.api.module.commands.EncryptPassCommand;
import solutions.redwall.users.api.module.commands.ValidateNewUserCommand;

/**
 *
 * @author Andrew Ribeiro
 */
@RibrestModel(defaultEndpointsConfigurators = {
    @RibrestEndpointConfigurator(method = "POST", beforeCommands = {ValidateNewUserCommand.class, EncryptPassCommand.class}),
    @RibrestEndpointConfigurator(),
    @RibrestEndpointConfigurator(method = "PUT", path = "{id}", beforeCommands = {GetPersistentModelCommand.class, MergeModelToPersistedModelCommand.class}),
    @RibrestEndpointConfigurator(method = "DELETE", path = "{id}", beforeCommands = {GetPersistentModelCommand.class, MergeModelToPersistedModelCommand.class})
})
@Entity(name = "Uzer")
public class User extends AbstractModel {

    private String fullName;
    @Column(unique = true)
    private String email;
    private String username;
    private String password;
    private String type;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
