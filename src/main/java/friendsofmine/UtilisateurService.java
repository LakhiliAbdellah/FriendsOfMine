package friendsofmine;

import friendsofmine.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class representing service on Utilisateur objects
 */
@Service
public class UtilisateurService {

    @Autowired private UtilisateurRepository utilisateurRepository ;

    /**
     * Save the given utilisateur
     * @param utilisateur the utilisateur
     * @return the saved utilisateur
     */
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur) ;
    }

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
}
