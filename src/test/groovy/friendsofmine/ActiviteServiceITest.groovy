package friendsofmine

import friendsofmine.repositories.ActiviteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.validation.ConstraintViolationException

/**
 * Created by franck on 18/10/2016.
 */
@ContextConfiguration
@SpringBootTest
@Transactional
class ActiviteServiceITest extends Specification {

    @Autowired ActiviteService activiteService

    def "test save a valid activite"() {

        given: "a responsable"
        Utilisateur bob = new Utilisateur(nom: "Deniro", prenom: "bob", email: "bob@deniro.com",sexe: "M")

        and: "a valid activite"
        Activite natation = new Activite(titre: "natation", responsable: bob)

        when: "the activite is saved"
        activiteService.saveActivite(natation);

        then: "the activite has an id"
        natation.id != null

    }

    def "test save a non valid activite"() {
        given: "a non valid activite"
        Activite natation = new Activite()

        when: "the activite is saved"
        activiteService.saveActivite(natation);

        then: "A validation exception is thrown"
        thrown ConstraintViolationException

        and: "activite has still null id"
        natation.id == null
    }

    def "test findAllActivites"() {
        given: "a responsable"
        Utilisateur bob = new Utilisateur(nom: "Deniro", prenom: "bob", email: "bob@deniro.com",sexe: "M")

        and: "3 activities"
        activiteService.saveActivite(new Activite(titre: "natation", responsable: bob));
        activiteService.saveActivite(new Activite(titre: "badmington", responsable: bob));
        activiteService.saveActivite(new Activite(titre: "cinema", responsable: bob));

        when: "requesting all activites"
        Iterable<Activite> iterOnActivites = activiteService.findAllActivites()
        def activites = iterOnActivites as List<Activite>

        then: "the result refernces 3 activites"
        activites.size() == 3

        and: "the activities are sorted by titre"
        activites[0].titre == "badmington"
        activites[1].titre == "cinema"
        activites[2].titre == "natation"

    }
}
