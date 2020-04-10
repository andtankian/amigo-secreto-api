package solutions.redwall.login.api.module;

import br.com.andrewribeiro.ribrest.Ribrest;

/**
 *
 * @author ribeiro
 */
public class LoginApiModuleApp {

    public static void main(String[] args) {
        Ribrest.getInstance().appBaseUrl("http://0.0.0.0:2007/")
                .appName("login-api-module")
                .debug(true)
                .persistenceUnitName("login-api-module")
                .init();
    }

}
