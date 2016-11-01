package friendsofmine

import spock.lang.Specification

/**
 * Created by franck on 19/10/2016.
 */
class BootstrapTest extends Specification {

    void "test bootstrap init method call the initialisation service"() {

        given: "a bootstrap with it's initialization service"
        Bootstrap bootstrap = new Bootstrap()
        bootstrap.initialisationService = Mock(InitialisationService)

        when: "the init method is triggered"
        bootstrap.init()

        then: "the initialisation of activites is triggered on the initialisation service"
        1 * bootstrap.initialisationService.initActivites()
        1 * bootstrap.initialisationService.initInscriptions()

    }

    void "test the init bootstrap method catch runtime exception comming from initActivites"() {
        given: "a bootstrap with it's initialization service"
        Bootstrap bootstrap = new Bootstrap()
        bootstrap.initialisationService = Mock(InitialisationService) {
            initActivites() >> { throw new RuntimeException()}
        }

        when: "the init method is triggered"
        bootstrap.init()

        then: "no exception is thrown"
        noExceptionThrown()

    }

}
