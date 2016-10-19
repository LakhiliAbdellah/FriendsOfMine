package friendsofmine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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
    @Autowired Bootstrap bootstrap

    def "test save a valid activite"() {

        given: "a responsable"
        Utilisateur bob = new Utilisateur(nom: "Deniro", prenom: "bob", email: "bob@deniro.com",sexe: "M")

        and: "a valid activite"
        Activite natation = new Activite(titre: "natation", responsable: bob)

        when: "the activite is saved"
        activiteService.saveActivite(natation);

        then: "the activite has an id"
        natation.id != null
        bob.activites.size() == 1
        bob.activites.first().titre == natation.titre

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

        given: "The instance of InitialisationService provided by the bootstrap object"
        InitialisationService initialisationService = bootstrap.initialisationService

        and: "2 utilisateurs provided by the initialisation service"
        initialisationService.mary
        initialisationService.thom

        and:  "3 activities provided by the initialisation service"
        initialisationService.lindyHop
        initialisationService.randonnee
        initialisationService.taekwondo

        when: "requesting all activites"
        Iterable<Activite> iterOnActivites = activiteService.findAllActivites()
        def activites = iterOnActivites as List<Activite>

        then: "the result references 3 activites"
        activites.size() == 3

        and: "the activities are sorted by titre"
        activites[0].titre == initialisationService.lindyHop.titre
        activites[1].titre == initialisationService.randonnee.titre
        activites[2].titre == initialisationService.taekwondo.titre

        and: "fetched activites have the good responsable"
        activites[0].responsable.nom == initialisationService.mary.nom
        activites[1].responsable.nom == initialisationService.mary.nom
        activites[2].responsable.nom == initialisationService.thom.nom

    }
}
