package friendsofmine

import friendsofmine.repositories.ActiviteRepository
import friendsofmine.repositories.InscriptionRepository
import friendsofmine.repositories.UtilisateurRepository
import spock.lang.Specification

/**
 * Created by franck on 04/11/2016.
 */
class InscriptionControllerTest extends Specification {

    InscriptionController inscriptionController
    InscriptionRepository inscriptionRepository
    ActiviteRepository activiteRepository
    UtilisateurRepository utilisateurRepository

    void setup() {
        inscriptionRepository = Mock()
        utilisateurRepository = Mock()
        activiteRepository = Mock()
        inscriptionController = new InscriptionController(
                inscriptionRepository: inscriptionRepository,
                utilisateurRepository: utilisateurRepository,
                activiteRepository: activiteRepository
        )
    }

    def "test ajout d'une inscription"() {
        when: "une requête d'ajout est déclenchée"
        inscriptionController.addInscription(1,1)

        then: "différentes actions sur les différents repository sont déclenchées"
        1 * activiteRepository.findOne(1)
        1 * utilisateurRepository.findOne(1)
        1 * inscriptionRepository.save(_)
    }

    def "test suppression d'une inscription"() {
        when: "une requête de suppression est déclenchée"
        inscriptionController.deleteInscription(1)

        then: "l'action est déléguée au repository"
        1 * inscriptionRepository.delete(1)
    }
}
