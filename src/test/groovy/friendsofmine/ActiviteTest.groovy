package friendsofmine

import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


/**
 * Created by franck on 17/10/2016.
 */
class ActiviteTest extends Specification {

    Validator validator

    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Unroll
    void "test la validite d'une activite valide ayant pour titre #unTitre"(String unTitre, String unDescriptif) {

        given: "une activite initialise avec un titre non vide et un descriptif"
        Activite activite = new Activite(titre: unTitre, descriptif: unDescriptif)

        expect: "l'activite est valide"
        validator.validate(activite).empty

        where:
        unTitre    | unDescriptif
        "un titre 1" | null
        "un titre 2" | ""
        "un titre 3" | "un descriptif"

    }

    @Unroll
    void "test l'invalidite d'une activite non valide ayant pour titre #unTitre"(String unTitre, String _) {

        given: "une activite initialise avec un titre vide"
        Activite activite = new Activite(titre: unTitre)

        expect: "l'activite est invalide"
        !validator.validate(activite).empty

        where:
        unTitre | _
        null    | _
        ""      | _

    }
}
