package solutions.redwall.users.api.module.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import org.mindrot.jbcrypt.BCrypt;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class EncryptPassCommand extends AbstractCommand{

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        User user = (User) flowContainer.getModel();
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }
    
    

}
