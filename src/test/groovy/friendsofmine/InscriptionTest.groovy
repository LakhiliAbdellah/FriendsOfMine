package friendsofmine

import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class InscriptionTest extends Specification {

    Validator validator

    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Unroll
    void "test la validite d'une inscription valide"() {

        given:"un utilisateur et une activité"
        Utilisateur unUtilisateur = Mock(Utilisateur)
        Activite uneActivite = Mock(Activite)

        and:"une date"
        Date now = new Date()

        when: "une inscription est créée"
        Inscription uneInscription = new Inscription(utilisateur:unUtilisateur, activite: uneActivite, dateInscription: now)

        then: "l'inscription est valide"
        validator.validate(uneInscription).empty

        and: "les propietes de l'inscription sont correctement mises à jour"
        uneInscription.utilisateur == unUtilisateur
        uneInscription.activite == uneActivite
        uneInscription.dateInscription != null

    }

    @Unroll
    void "test l'invalidite d'une inscription non valide"() {

        given:"un utilisateur et une activité"
        Utilisateur unUtilisateur = Mock(Utilisateur)
        Activite uneActivite = Mock(Activite)

        and:"une date"
        Date now = new Date()

        when: "une inscription sans utilisateur est créée"
        Inscription uneInscription = new Inscription(utilisateur:null, activite: uneActivite, dateInscription: now)

        then: "l'inscription n'est pas validée"
        !validator.validate(uneInscription).empty

        when: "une inscription sans activité est créée"
        uneInscription = new Inscription(utilisateur:unUtilisateur, activite: null, dateInscription: now)

        then: "l'inscription n'est pas validée"
        !validator.validate(uneInscription).empty

        when: "une inscription sans date d'inscription est créée"
        uneInscription = new Inscription(utilisateur:unUtilisateur, activite: uneActivite, dateInscription: null)

        then: "l'inscription n'est pas validée"
        !validator.validate(uneInscription).empty
    }

}