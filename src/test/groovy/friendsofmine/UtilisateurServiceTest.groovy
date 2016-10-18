package friendsofmine

import friendsofmine.repositories.UtilisateurRepository
import org.springframework.data.repository.PagingAndSortingRepository
import spock.lang.Specification

/**
 * Created by franck on 18/10/2016.
 */
class UtilisateurServiceTest extends Specification {

    UtilisateurService utilisateurService
    UtilisateurRepository utilisateurRepository

    void setup() {
        utilisateurRepository = Mock()
        utilisateurService = new UtilisateurService()
        utilisateurService.utilisateurRepository = utilisateurRepository
    }

    def "check type of utilisateurRepository"() {
        expect: "utilisateurRepository is a Spring repository"
        utilisateurRepository instanceof PagingAndSortingRepository
    }

    def "test delegation of save of an Utilisateur to the repository"() {
        given: "an utilisateur"
        def utilisateur = Mock(Utilisateur)

        when: "the utilisateur is saved"
        utilisateurService.saveUtilisateur(utilisateur);

        then: "the save is delegated to the utilisateurRepository"
        1 * utilisateurRepository.save(utilisateur)
    }


}
