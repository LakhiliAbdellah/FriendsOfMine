package friendsofmine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.validation.ConstraintViolationException

/**
 * Created by franck on 18/10/2016.
 */
@ContextConfiguration
@SpringBootTest
class UtilisateurServiceITest extends Specification {

    @Autowired UtilisateurService utilisateurService

    def "test save a valid utilisateur"() {
        given: "a valid utilisateur"
        Utilisateur bob = new Utilisateur(nom: "Deniro", prenom: "bob", email: "bob@deniro.com",sexe: "M")

        when: "the utilisateur is saved"
        utilisateurService.saveUtilisateur(bob);

        then: "the utilisateur has an id"
        bob.id != null

    }

    def "test save a non valid utilisateur"() {
        given: "a non valid utilisateur"
        Utilisateur bob = new Utilisateur(prenom: "bob", email: "bob@deniro.com",sexe: "M")

        when: "the utilisateur is saved"
        utilisateurService.saveUtilisateur(bob);

        then: "A validation exception is thrown"
        thrown ConstraintViolationException

        and: "bob has still null id"
        bob.id == null
    }


}
