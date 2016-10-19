package friendsofmine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Bootstrap of the application
 */
@Component
public class Bootstrap {

    @Autowired private InitialisationService initialisationService;

    @PostConstruct
    public void init() {
        initialisationService.initActivites();
    }

    public InitialisationService getInitialisationService() {
        return initialisationService;
    }
}
