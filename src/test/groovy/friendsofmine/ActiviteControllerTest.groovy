package friendsofmine

import spock.lang.Specification

/**
 * Created by franck on 20/10/2016.
 */
class ActiviteControllerTest extends Specification {

    private ActiviteService activiteService
    private ActiviteController activiteController

    void setup() {
        activiteService = Mock()
        activiteController = new ActiviteController(activiteService: activiteService)
    }

    def "test find all activites call to service method"() {

        when: "requesting for all activites with responsable"
        activiteController.findAllActvitesWithResponsable()

        then: "the request is performed by the activite service"
        1 * activiteService.findAllActivites()

    }

}
