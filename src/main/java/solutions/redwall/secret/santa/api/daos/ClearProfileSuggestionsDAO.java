package solutions.redwall.secret.santa.api.daos;

import br.com.andrewribeiro.ribrest.Ribrest;
import br.com.andrewribeiro.ribrest.core.persistence.CRUDDAOImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import solutions.redwall.secret.santa.api.models.Suggestion;

/**
 *
 * @author Andrew Ribeiro andrew@redwall.solutions
 */
public class ClearProfileSuggestionsDAO extends CRUDDAOImpl{

    @Override
    public void delete(){
        try {
            Suggestion suggestion = (Suggestion) flowContainer.getModel();
            EntityManager entityManager = flowContainer.getEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            Query query = entityManager.createQuery("delete from Suggestion s where s.profile.id = :p");
            System.out.println(suggestion.getProfile().getId());
            query.setParameter("p", suggestion.getProfile().getId());
            query.executeUpdate();
            transaction.commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }        
    }
    
  
    
}
