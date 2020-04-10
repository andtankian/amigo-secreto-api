package solutions.redwall.secret.santa.api.commands;

import br.com.andrewribeiro.ribrest.core.exceptions.RibrestDefaultException;
import br.com.andrewribeiro.ribrest.core.model.Model;
import br.com.andrewribeiro.ribrest.services.command.AbstractCommand;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.IntStream;
import javax.persistence.OneToOne;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class CustomMergeModelToPersistedModelCommand extends AbstractCommand {

    @Override
    public void execute() throws RibrestDefaultException, Exception {
        Model model = flowContainer.getModel();

        Model persistedModel = (Model) flowContainer.getExtraObject(Model.PERSISTED_MODEL_KEY);
        flowContainer.removeExtraObject(Model.PERSISTED_MODEL_KEY);

        customMerge(persistedModel, model);

        flowContainer.setModel(persistedModel);

    }

    void customMerge(Model persistedModel, Model model) {
        List<Field> thisInstanceAttributes = persistedModel.getAllAttributesExceptsId();
        List<Field> modelToMergeAttributes = model.getAllAttributesExceptsId();
        IntStream.range(0, thisInstanceAttributes.size()).forEach((index) -> {
            Field thisIntanceField = thisInstanceAttributes.get(index);
            Field modelToMergeField = modelToMergeAttributes.get(index);
            thisIntanceField.setAccessible(true);
            modelToMergeField.setAccessible(true);
            try {
                Object attributeInstance = modelToMergeField.get(model);
                attributeInstance = attributeInstance == null ? thisIntanceField.get(persistedModel) : attributeInstance;
                if (modelToMergeField.isAnnotationPresent(OneToOne.class)) {
                    attributeInstance = attributeInstance == null || ((Model) attributeInstance).getId() == null ? thisIntanceField.get(persistedModel) : attributeInstance;
                }
                thisIntanceField.set(persistedModel, attributeInstance);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new RuntimeException(ex.getCause());
            }
        });
    }

}
