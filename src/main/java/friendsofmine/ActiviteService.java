package friendsofmine;

import friendsofmine.repositories.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class representing service on Activite objects
 */
@Service
public class ActiviteService {

    @Autowired private ActiviteRepository activiteRepository ;

    /**
     * Save the given activity
     * @param activite the activity to save
     * @return the saved activity
     */
    public Activite saveActivite(Activite activite) {
        return  activiteRepository.save(activite) ;
    }

    public ActiviteRepository getActiviteRepository() {
        return activiteRepository;
    }

    public void setActiviteRepository(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }
}
