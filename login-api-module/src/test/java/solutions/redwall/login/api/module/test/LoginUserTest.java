package solutions.redwall.login.api.module.test;

import java.util.Base64;
import java.util.UUID;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import solutions.redwall.login.api.module.RibrestTest;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class LoginUserTest extends RibrestTest{
    
    private MultivaluedMap<String, String> mvm = new MultivaluedHashMap<>();
    
    @Test
    public void login(){
        createAndReadUser();
        mvm.clear();
        String email,password,emailAndPasswordHashed;
        email = "andrew@redwall.solutions";
        password = "1234";
        emailAndPasswordHashed = Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
        
        mvm.add("Authorization", "Basic " + emailAndPasswordHashed);
        postWithHeaders(APP_URL + "loginusers", new Form(), mvm);
        logResponse();
        wasCreated();
    }
    
    public String createAndReadUser(){
        mvm.clear();
        mvm.add("fullName", "Andrew Ribeiro");
        mvm.add("type", User.class.getSimpleName());
        mvm.add("username", "andrew@redwall.solutions");
        mvm.add("email", "andrew@redwall.solutions");
        mvm.add("password", "1234");
        post(User.class, new Form(mvm));
        logResponse();
        wasCreated();
        return getFirstExistentModel(responseText);
    }

}
