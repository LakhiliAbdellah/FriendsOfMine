package friendsofmine

import friendsofmine.repositories.ActiviteRepository
import friendsofmine.repositories.InscriptionRepository
import org.springframework.data.repository.PagingAndSortingRepository
import spock.lang.Specification

/**
 * Created by franck on 18/10/2016.
 */
class InscriptionServiceTest extends Specification {

    InscriptionService inscriptionService
    InscriptionRepository inscriptionRepository

    void setup() {
        inscriptionRepository = Mock()
        inscriptionService = new InscriptionService()
        inscriptionService.inscriptionRepository = inscriptionRepository
    }

    def "check type of inscriptionRepository"() {
        expect: "inscriptionRepository is a Spring repository"
        inscriptionRepository instanceof PagingAndSortingRepository
    }

    def "test delegation of save of an inscription to the repository"() {
        given: "an inscription"
        def inscription = Mock(Inscription)

        when: "the inscription is saved"
        inscriptionService.saveInscription(inscription);

        then: "the save is delegated to the activiteRepository"
        1 * inscriptionRepository.save(inscription)
    }

    def "test delegation of delete of an inscription to the repository"() {
        given: "an inscription"
        def inscription = Mock(Inscription)

        when: "requesting for a deletion"
        inscriptionService.deleteInscription(inscription)

        then: "the request is delegated to the activiteRepository"
        1 * inscriptionRepository.delete(inscription)
    }

}
