package solutions.redwall.secret.santa.api.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import solutions.redwall.secret.santa.api.models.Profile;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class RaffleCommand extends AbstractCommand {

    List<Profile> raffledProfiles;
    List<Profile> registeredProfiles;
    List<Profile> candidates;

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        raffledProfiles = new ArrayList<>();
        registeredProfiles = getRegisteredProfiles();
        raffle();
        saveRaflled();
        cleanOutput();
    }

    private List<Profile> getRegisteredProfiles() {
        List internalRegisteredProfiles = flowContainer.getEntityManager().createQuery("select p from Profile p").getResultList();
        return internalRegisteredProfiles;
    }

    private void raffle() {
        Collections.shuffle(registeredProfiles);
        if (isGreaterThanOne()) {
            for (int index = 0; index < registeredProfiles.size(); index++) {
                Profile currentProfile = registeredProfiles.get(index);
                Profile nextProfile = getNextProfile(index);
                currentProfile.setFriend(nextProfile);
            }
        }
    }

    private void cleanOutput() {
        Profile profile = (Profile) flowContainer.getModel();
        profile.setFriend(null);
    }

    private Profile getNextProfile(int index) {
        Profile nextProfile;
        if (++index < registeredProfiles.size()) {
            nextProfile = registeredProfiles.get(index);
        } else {
            nextProfile = registeredProfiles.get(0);
        }
        return nextProfile;
    }

    private boolean isGreaterThanOne() {
        return registeredProfiles.size() > 1;
    }

    private void saveRaflled() {
        flowContainer.getEntityManager().getTransaction().begin();
        flowContainer.getEntityManager().getTransaction().commit();
    }

}
