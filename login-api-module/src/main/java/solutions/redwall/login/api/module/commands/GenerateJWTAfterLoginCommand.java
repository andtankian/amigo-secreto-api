package solutions.redwall.login.api.module.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import br.com.andrewribeiro.ribrest.utils.RibrestJWTPayload;
import br.com.andrewribeiro.ribrest.utils.RibrestUtils;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import solutions.redwall.login.api.module.models.LoginUser;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class GenerateJWTAfterLoginCommand extends AbstractCommand{

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        
        LoginUser loginUser = (LoginUser) flowContainer.getModel();
        User user = loginUser.getUser();
        
        loginUser.setCurrentJTW(RibrestUtils.RibrestJWT.create(new RibrestJWTPayload(
                user.getType(),
                user.getEmail(), 
                "all", new Date(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() * (10 * (6000 * 30)))  , new Date())));
    }

}
