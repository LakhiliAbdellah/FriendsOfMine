package friendsofmine;

import friendsofmine.repositories.InscriptionRepository;
import friendsofmine.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by franck on 01/11/2016.
 */
@Service
public class InscriptionService {

    @Autowired private UtilisateurRepository utilisateurRepository;
    @Autowired private InscriptionRepository inscriptionRepository;

    /**
     * Save an inscription
     * @param inscription the inscription to save
     * @return the saved inscription
     */
    Inscription saveInscription(Inscription inscription) {
        if (inscription == null) {
            throw new IllegalArgumentException("Inscription must not be null");
        }
        Utilisateur utilisateur = inscription.getUtilisateur();
        if (utilisateur != null){
            utilisateurRepository.save(utilisateur);
        }
        inscription.setDateInscription(new Date());
        return inscriptionRepository.save(inscription) ;
    }



    /**
     * Delete an inscription
     * @param inscription the inscription to delete
     */
    void deleteInscription(Inscription inscription) {
        if (inscription == null) {
            throw new IllegalArgumentException("Inscription must not be null");
        }
        inscriptionRepository.delete(inscription);
    }

    public InscriptionRepository getInscriptionRepository() {
        return inscriptionRepository;
    }

    public void setInscriptionRepository(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }
}
