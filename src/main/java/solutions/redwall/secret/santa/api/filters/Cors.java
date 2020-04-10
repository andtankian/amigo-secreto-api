package solutions.redwall.secret.santa.api.filters;

import br.com.andrewribeiro.ribrest.core.annotations.RibrestFilter;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Andrew Ribeiro
 */
@RibrestFilter
@Provider
public class Cors implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "authorization, content-type");
    }
}
