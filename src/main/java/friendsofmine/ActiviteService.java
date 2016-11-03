package friendsofmine;

import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Class representing service on Activite objects
 */
@Service
public class ActiviteService {

    @Autowired private ActiviteRepository activiteRepository ;
    @Autowired private UtilisateurRepository utilisateurRepository;

    /**
     * Save the given activity
     * @param activite the activity to save
     * @return the saved activity
     */
    public Activite saveActivite(Activite activite) {
        if (activite == null) {
            throw new IllegalArgumentException("Activite must not be null");
        }
        activiteRepository.save(activite);
        Utilisateur responsable = activite.getResponsable();
        responsable.getActivites().add(activite);
        return  activite ;
    }

    /**
     * Find all activities sorted by titre
     * @return an iterable on the sorted fetched collection
     */
    public Iterable<Activite> findAllActivites() {
        return activiteRepository.findAll();
    }


    public ActiviteRepository getActiviteRepository() {
        return activiteRepository;
    }

    public void setActiviteRepository(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
}
