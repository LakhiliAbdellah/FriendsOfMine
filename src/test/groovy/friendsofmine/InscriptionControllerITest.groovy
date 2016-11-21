package friendsofmine

import friendsofmine.repositories.InscriptionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class InscriptionControllerITest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitialisationService initialisationService;

    @Autowired
    private InscriptionRepository inscriptionRepository;


    def "test ajout d'une inscription"() {

        when: "un ajout d'incription est demandé"
        MultiValueMap<String, Long> map = new LinkedMultiValueMap<String, Long>();
        map.add("utilisateur_id", initialisationService.mary.id);
        map.add("activite_id", initialisationService.randonnee.id);
        Inscription inscription = restTemplate.postForObject("/api/v1/inscriptions",map, Inscription.class)

        then: "le retour fournit les informations de l'inscription créée"
        inscription.id
        inscription.dateInscription
        inscription.activite.id == initialisationService.randonnee.id
        inscription.utilisateur.id == initialisationService.mary.id


    }

    def "test suppression d'une inscription"() {

        when: "une inscription est supprimeé"
        restTemplate.delete("/api/v1/inscriptions/${initialisationService.maryOnTaekwondo.id}")

        then: "elle est effectivement supprimée de la base de donnée"
        !inscriptionRepository.findOne(initialisationService.maryOnTaekwondo.id)
    }
}
