package friendsofmine


import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


class UtilisateurTest extends Specification {

    Validator validator

    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Unroll
    void "test la validite d'un utilisateur valide"(String unNom, String unPrenom, String unEmail, String unSexe, Date uneDateNaissance) {

        given: "un utilisateur initialise correctement"
        Utilisateur utilisateur = new Utilisateur(nom: unNom, prenom: unPrenom, email: unEmail, sexe: unSexe, dateNaissance: uneDateNaissance)

        expect: "l'utilisateur est valide"
        validator.validate(utilisateur).empty

        where:
        unNom    | unPrenom  | unEmail     | unSexe | uneDateNaissance
        "Dupont" | "Jeanne"  | "jd@jd.com" | "F"    | new Date(1972, 6, 17)
        "Durand" | "Jacques" | "jd@jd.com" | "M"    | new Date(1972, 6, 17)
        "Durand" | "Jacques" | "jd@jd.com" | "M"    | null

    }

    @Unroll
    void "test l'invalidite d'un utilisateur non valide"(String unNom, String unPrenom, String unEmail, String unSexe) {

        given: "un utilisateur initialise de maniere non valide"
        Utilisateur utilisateur = new Utilisateur(nom: unNom, prenom: unPrenom, email: unEmail, sexe: unSexe)

        expect: "l'utilisateur est invalide"
        !validator.validate(utilisateur).empty

        where:
        unNom    | unPrenom  | unEmail     | unSexe
        null     | "Jeanne"  | "jd@jd.com" | "F"
        ''       | "Jeanne"  | "jd@jd.com" | "F"
        "Dupont" | null      | "jd@jd.com" | "F"
        "Durand" | ""        | "jd@jd.com" | "M"
        "Durand" | "Jacques" | "jdjd.com"  | "M"
        "Durand" | "Jacques" | null        | "M"
        "Durand" | "Jacques" | "jd@jd.com" | "Z"
        "Durand" | "Jacques" | "jd@jd.com" | null


    }
}