package solutions.redwall.users.api.module.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class ValidateNewUserCommand extends AbstractCommand{

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        
        User user = (User)flowContainer.getModel();
        
        if(user == null){
            throw new RuntimeException("user is null");
        }
        
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new RuntimeException("empty user email");
        }
        
        if(user.getFullName() == null || user.getFullName().isEmpty()){
            throw new RuntimeException("empty user fullName");
        }
        
        if(user.getType() == null || user.getType().isEmpty()){
            throw new RuntimeException("empty user type");
        }
        
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            throw new RuntimeException("empty username");
        }
    }
    
}
