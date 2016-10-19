package friendsofmine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by franck on 19/10/2016.
 */
@RestController
public class ActiviteController {

    @Autowired private ActiviteService activiteService ;

    @RequestMapping("/activitesWithResponsable")
    public Iterable<Activite> findAllActvitesWithResponsable() {
        return activiteService.findAllActivites();
    }

}
