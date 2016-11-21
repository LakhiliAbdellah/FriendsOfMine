package friendsofmine

import friendsofmine.repositories.ActiviteRepository
import friendsofmine.repositories.InscriptionRepository
import friendsofmine.repositories.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.*

@ContextConfiguration
@SpringBootTest
@Transactional
class InscriptionRepositoryITest extends Specification {

    Activite uneActivite
    Utilisateur unUtilisateur

    @Autowired UtilisateurService utilisateurService
    @Autowired ActiviteService activiteService
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
        Inscription resInscription = inscriptionRepository.save(uneInscription)

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

    def "test la recherche d'inscription"() {

        given: "un exemple d'inscription : les inscriptions de thom"
        Inscription inscription = new Inscription()
        inscription.utilisateur = new Utilisateur()
        inscription.utilisateur.nom = initialisationService.thom.nom
        Example<Inscription> example = Example.of(inscription)

        when: "on recherche les activités de thom"
        List<Inscription> res = inscriptionRepository.findAll(example) as List<Inscription>

        then: "la randonnée et le lindy hop sont trouvées"
        res.size() == 2
        res.contains(initialisationService.thomOnLindyhop);
        res.contains(initialisationService.thomOnRandonnee);

    }

}