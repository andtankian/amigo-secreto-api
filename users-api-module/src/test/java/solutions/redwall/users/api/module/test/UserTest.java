package solutions.redwall.users.api.module.test;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import solutions.redwall.users.api.module.RibrestTest;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class UserTest extends RibrestTest{
    MultivaluedMap mvm = new MultivaluedHashMap();
    
    @Test
//    @Ignore
    public void newCompleteUser(){
        mvm.clear();
        mvm.add("fullName", "Andrew Ribeiro");
        mvm.add("email", "andrew@redwall.solutions");
        mvm.add("type", "Comum");
        mvm.add("username", "andrew.ribeiro");
        post(User.class, new Form(mvm));
        wasCreated();
        logResponse();
    }
    
    @Test
    public void newIncompleteUser() {
        mvm.clear();
        post(User.class, new Form(mvm));
        wasPreConditionFailed();
        logResponse();
        mvm.add("fullName", "Andrew Ribeiro");
        post(User.class, new Form(mvm));
        wasPreConditionFailed();
        logResponse();
        mvm.add("type", "ADM");
        post(User.class, new Form(mvm));
        wasPreConditionFailed();
        logResponse();
        mvm.add("username", "andrew.ribeiro");
        post(User.class, new Form(mvm));
        wasPreConditionFailed();
        logResponse();
        mvm.add("email", "andrew@redwall.solutions");
        post(User.class, new Form(mvm));
        wasCreated();
        logResponse();
    }
    
}
