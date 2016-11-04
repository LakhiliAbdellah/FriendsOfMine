package friendsofmine;

import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.InscriptionRepository;
import friendsofmine.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by franck on 04/11/2016.
 */
@RestController
public class InscriptionController {

    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    InscriptionRepository inscriptionRepository;


    @RequestMapping(value = "/api/v1/inscriptions", method = RequestMethod.POST)
    public Inscription addInscription(@RequestParam(value = "utilisateur_id") Long utilisateurId, @RequestParam(value = "activite_id") Long activiteId) {
        Inscription inscription = new Inscription();
        Activite activite = activiteRepository.findOne(activiteId);
        Utilisateur utilisateur = utilisateurRepository.findOne(utilisateurId);
        inscription.setActivite(activite);
        inscription.setUtilisateur(utilisateur);
        return inscriptionRepository.save(inscription);
    }

    @RequestMapping(value = "/api/v1/inscriptions/{inscription_id}", method = RequestMethod.DELETE)
    public void deleteInscription(@PathVariable("inscription_id") Long inscriptionId) {
        inscriptionRepository.delete(inscriptionId);
    }

}
