package solutions.redwall.login.api.module.miners;

import br.com.andrewribeiro.ribrest.services.miner.AbstractMiner;
import br.com.andrewribeiro.ribrest.services.miner.RequestMaps;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.StringTokenizer;
import org.glassfish.jersey.server.ContainerRequest;
import solutions.redwall.login.api.module.models.LoginUser;
import solutions.redwall.users.api.module.models.User;

/**
 *
 * @author Andrew Ribeiro
 */
public class LoginUserMiner extends AbstractMiner{
    
    @Override
    public void mineRequest(ContainerRequest containerRequest) {
        super.mineRequest(containerRequest); 
        
        LoginUser loginUser = (LoginUser) flowContainer.getModel();
        
        loginUser.setDateReg(new Timestamp(System.currentTimeMillis()));
        
        
        User user = new User();
        
        Credentials credentials = getCredentials();
        
        user.setEmail(credentials.identification);
        user.setUsername(credentials.identification);
        user.setPassword(credentials.password);
        
        loginUser.setUser(user);
    }
    
    private String getToken(){
        String token = flowContainer.getRequestMaps().getHeaderMap().getFirst("Authorization");
        token = token.replaceFirst("Basic ", "");
        return token;
    }
    
    private String getHash(){
        return new String(Base64.getDecoder().decode(getToken().getBytes()));
    }
    
    private StringTokenizer getTokenizer() {
        String hashedCredentials = getHash();
        return new StringTokenizer(hashedCredentials,":");
    }
    
    private Credentials getCredentials() {
       StringTokenizer tokenizer = getTokenizer();
       return new Credentials(tokenizer.nextToken(), tokenizer.nextToken());
    }
    
    class Credentials {

        public Credentials(String identification, String password) {
            this.identification = identification;
            this.password = password;
        }
        
        String identification;
        String password;
    }

}
