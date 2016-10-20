package friendsofmine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by franck on 20/10/2016.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActiviteControllerITest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InitialisationService initialisationService;


    def "test FindAllActvitesWithResponsable"() {

        when: "requesting all activites"
        String body = this.restTemplate.getForObject("/activitesWithResponsable", String.class);

        then:"the result provides 3 activites with their responsable"
        body.contains(initialisationService.randonnee.titre)
        body.contains(initialisationService.lindyHop.titre)
        body.contains(initialisationService.taekwondo.titre)
        body.contains(initialisationService.mary.nom)
        body.contains(initialisationService.thom.nom)

    }
}
