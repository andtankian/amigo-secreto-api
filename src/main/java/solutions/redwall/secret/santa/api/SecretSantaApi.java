package solutions.redwall.secret.santa.api;

import br.com.andrewribeiro.ribrest.Ribrest;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class SecretSantaApi {
    
    public static void main(String[] args) {
        Ribrest.getInstance().appBaseUrl("http://0.0.0.0:2007/")
                .appName("secret-santa")
                .persistenceUnitName("secret-santa")
                .debug(true)
                .init();
    }
    
}
