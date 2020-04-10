package solutions.redwall.secret.santa.api.models;

import br.com.andrewribeiro.ribrest.core.annotations.RibrestModel;
import br.com.andrewribeiro.ribrest.core.model.AbstractModel;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
@RibrestModel
@Entity
public class Suggestion extends AbstractModel {

    @ManyToOne(cascade = CascadeType.ALL)
    Profile profile;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
