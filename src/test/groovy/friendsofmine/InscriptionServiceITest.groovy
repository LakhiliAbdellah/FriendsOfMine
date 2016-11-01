package friendsofmine

import friendsofmine.repositories.ActiviteRepository
import friendsofmine.repositories.InscriptionRepository
import friendsofmine.repositories.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.*

@ContextConfiguration
@SpringBootTest
@Transactional
class InscriptionServiceITest extends Specification {

    Activite uneActivite
    Utilisateur unUtilisateur

    @Autowired UtilisateurService utilisateurService
    @Autowired ActiviteService activiteService
    @Autowired InscriptionService inscriptionService
    @Autowired InscriptionRepository inscriptionRepository
    @Autowired UtilisateurRepository utilisateurRepository
    @Autowired ActiviteRepository activiteRepository
    @Autowired InitialisationService initialisationService

    def setup() {
        // l'activité
        uneActivite = new Activite(titre: "act1")
        Utilisateur unResponsable = new Utilisateur(nom: "Dupont", prenom: "Jeanne", sexe: "F", email: "j@j.com")
        uneActivite.responsable = unResponsable
        activiteService.saveActivite(uneActivite)

        // l'utilisateur
        unUtilisateur = new Utilisateur(nom: "Durand", prenom: "paul", sexe: "M", email: "p@j.com")
        utilisateurService.saveUtilisateur(unUtilisateur)
    }

    void "test la création ou la mise à jour d'une inscription"() {

        given: "une inscription"
        Inscription uneInscription = new Inscription(activite: uneActivite, utilisateur: unUtilisateur)


        when: "on insert ou met à jour l'inscription"
        Inscription resInscription = inscriptionService.saveInscription(uneInscription)

        then: "l'inscription insérée est bien celle retournée"
        resInscription == uneInscription

        and:"l'inscription a bien un id"
        uneInscription.id != null

        when:"l'inscription est requêtée en base"
        Inscription fetchInscription = inscriptionRepository.findOne(uneInscription.id)

        then:"elle est bien récupérée"
        fetchInscription != null

        and :"les propriétes sont mises à jours comme attendues"
        fetchInscription.activite == uneActivite
        fetchInscription.utilisateur == unUtilisateur
        fetchInscription.dateInscription != null
    }

    void "test de la suppression d'une inscription"() {

        given:"une inscription existante en base"
        Inscription uneInscription = new Inscription(activite: uneActivite, utilisateur: unUtilisateur)
        inscriptionService.saveInscription(uneInscription)

        when:"on déclenche la suppression de l'inscription"
        inscriptionService.deleteInscription(uneInscription)

        then:"l'inscription est supprimée de la base"
        !inscriptionRepository.exists(uneInscription.id)

        and:"ni l'activité, ni l'utilisateur ne sont supprimés"
        activiteRepository.exists(uneActivite.id)
        utilisateurRepository.exists(unUtilisateur.id)

    }

    def "test initialisation of inscriptions by initialisation service"() {

        expect:"3 inscriptions are initialized"
        initialisationService.maryOnTaekwondo
        initialisationService.thomOnLindyhop
        initialisationService.thomOnRandonnee

        and: "Mary has subscribed taekwondo"
        initialisationService.maryOnTaekwondo.activite == initialisationService.taekwondo
        initialisationService.maryOnTaekwondo.utilisateur == initialisationService.mary

        and:"Thom has subscribed Lindy hop"
        initialisationService.thomOnLindyhop.utilisateur == initialisationService.thom
        initialisationService.thomOnLindyhop.activite == initialisationService.lindyHop

        and:"Thom has subscribed randonnée"
        initialisationService.thomOnRandonnee.utilisateur == initialisationService.thom
        initialisationService.thomOnRandonnee.activite == initialisationService.randonnee



    }

}