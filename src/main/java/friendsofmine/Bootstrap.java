package friendsofmine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Bootstrap of the application
 */
@Component
public class Bootstrap {

    @Autowired
    private ActiviteService activiteService;

    private Utilisateur mary;
    private Utilisateur thom;
    private Activite randonnee;
    private Activite lindyHop;
    private Activite taekwondo;


    @PostConstruct
    @Transactional
    public void init() {
        initMary();
        initAndSaveRandonnee();
        initAndSaveLindyHop();
        initThom();
        initAndSaveTaekwondo();
    }

    public Utilisateur getMary() {
        return mary;
    }

    public Utilisateur getThom() {
        return thom;
    }

    public Activite getRandonnee() {
        return randonnee;
    }

    public Activite getLindyHop() {
        return lindyHop;
    }

    public Activite getTaekwondo() {
        return taekwondo;
    }

    private void initAndSaveTaekwondo() {
        taekwondo = new Activite();
        taekwondo.setTitre("Taekwondo");
        taekwondo.setResponsable(thom);
        activiteService.saveActivite(taekwondo);
    }

    private void initThom() {
        thom = new Utilisateur();
        thom.setNom("Thom");
        thom.setPrenom("Thom");
        thom.setEmail("thom@thom.com");
        thom.setSexe("M");
    }

    private void initAndSaveLindyHop() {
        lindyHop = new Activite();
        lindyHop.setTitre("Lindy Hop");
        lindyHop.setResponsable(mary);
        activiteService.saveActivite(lindyHop);
    }

    private void initAndSaveRandonnee() {
        randonnee = new Activite();
        randonnee.setTitre("Randonnee");
        randonnee.setResponsable(mary);
        activiteService.saveActivite(randonnee);
    }

    private void initMary() {
        mary = new Utilisateur();
        mary.setNom("Mary");
        mary.setPrenom("Mary");
        mary.setEmail("mary@mary.com");
        mary.setSexe("F");
    }

}
