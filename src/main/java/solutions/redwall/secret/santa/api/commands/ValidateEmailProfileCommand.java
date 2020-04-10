package solutions.redwall.secret.santa.api.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import solutions.redwall.secret.santa.api.models.Profile;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class ValidateEmailProfileCommand extends AbstractCommand {

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        Profile profile = (Profile) flowContainer.getModel();
        profile.setEmail(profile.getUsername());
    }

}
