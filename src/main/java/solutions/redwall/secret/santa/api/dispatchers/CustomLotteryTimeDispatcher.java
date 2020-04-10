package solutions.redwall.secret.santa.api.dispatchers;

import br.com.andrewribeiro.ribrest.services.dispatcher.DispatcherImpl;
import com.google.gson.Gson;
import java.sql.Timestamp;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class CustomLotteryTimeDispatcher extends DispatcherImpl {

    @Override
    public Response send() {
        Timestamp time = Timestamp.valueOf("2019-12-11 21:30:00");
        Gson gson = new Gson();
        return Response.ok().entity(gson.toJson(time)).build();

    }

}
