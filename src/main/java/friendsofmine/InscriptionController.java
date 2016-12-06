package friendsofmine;

import friendsofmine.repositories.ActiviteRepository;
import friendsofmine.repositories.InscriptionRepository;
import friendsofmine.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/api/v1/inscriptions/search", method = RequestMethod.GET)
    public Page<Inscription> searchInscriptions(@RequestParam(value = "nom_utilisateur",required = false)String nomUtilisateur,
                                                @RequestParam(value = "titre_activite",required = false)String titreActivite,
                                                Pageable pageable) {
        Example<Inscription> example = getInscriptionExample(nomUtilisateur, titreActivite);
        Page<Inscription> res = inscriptionRepository.findAll(example, pageable);
        return res;
    }



    private Example<Inscription> getInscriptionExample(String nomUtilisateur, String titreActivite) {
        Inscription inscription = new Inscription();
        if (nomUtilisateur != null) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(nomUtilisateur);
            inscription.setUtilisateur(utilisateur);
        }
        if (titreActivite != null) {
            Activite activite = new Activite();
            activite.setTitre(titreActivite);
            inscription.setActivite(activite);
        }

        return Example.of(inscription);
    }

}
