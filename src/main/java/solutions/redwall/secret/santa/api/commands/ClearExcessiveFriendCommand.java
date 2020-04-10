package solutions.redwall.secret.santa.api.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import java.util.List;
import solutions.redwall.secret.santa.api.models.Profile;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class ClearExcessiveFriendCommand extends AbstractCommand {

    @Override
    public void execute() throws RibrestDefaultException, Exception {

        List<Profile> profiles = flowContainer.getHolder().getModels();

        profiles.forEach(profile -> {
            if (profile.getFriend() != null && profile.getFriend().getFriend() != null) {
                profile.getFriend().setFriend(null);
            }
            if (profile.getFriend() != null && profile.getFriend().getSuggestions() != null) {
                profile.getFriend().getSuggestions().clear();
            }
        });

    }

}
