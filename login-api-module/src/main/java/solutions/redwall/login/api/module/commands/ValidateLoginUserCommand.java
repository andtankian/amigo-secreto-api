package solutions.redwall.login.api.module.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.mindrot.jbcrypt.BCrypt;
import solutions.redwall.login.api.module.models.LoginUser;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class ValidateLoginUserCommand extends AbstractCommand{

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        
        LoginUser loginUser = (LoginUser) flowContainer.getModel();
        
        User user = loginUser.getUser();
        
        checkCredentialsValidity(user);
        
        match(user);
        
        
    }
    
    private void checkCredentialsValidity(User user) {
        if(isCredentialsInvalid(user)){
            throw new RuntimeException("invalid credentials");
        }
    }
    
    private void match(User user){
        
        if(!isValidMatch(user)){
            throw new RuntimeException("credentials does not match");
        }
        
    }
    
    private void setRetrievedUserToModel(User loadedUser) {
        LoginUser loginUser = (LoginUser) flowContainer.getModel();
        loginUser.setUser(loadedUser);
    }
    
    private boolean isCredentialsInvalid(User user){
        return user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty();
    }
    
    private boolean isValidMatch(User user){
        
        boolean isValidMatch = false;
        
        User loadedUser = retrieveUserByCredentials(user);
        
        if(loadedUser != null){
            if(BCrypt.checkpw(user.getPassword(), loadedUser.getPassword())){
                isValidMatch = true;
            }
        }
        
        setRetrievedUserToModel(loadedUser);
        
        return isValidMatch;
         
    }
    
    private User retrieveUserByCredentials(User user){
        CriteriaBuilder criteriaBuilder = flowContainer.getEntityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root root = criteriaQuery.from(user.getClass());
        criteriaQuery.select(root).where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("email"), user.getEmail()),
                        criteriaBuilder.equal(root.get("username"), user.getPassword())
                )
        );
        
        List<User> users = flowContainer.getEntityManager().createQuery(criteriaQuery).getResultList();
        if(users.size() > 0){
            return users.get(0);
        } else {
            return null;
        }

    }
    
}
