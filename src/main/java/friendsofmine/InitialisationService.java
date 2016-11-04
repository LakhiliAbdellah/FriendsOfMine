package friendsofmine;

import friendsofmine.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Bootstrap of the application
 */
@Service
@Transactional
public class InitialisationService {

    @Autowired private ActiviteService activiteService;
    @Autowired private InscriptionRepository inscriptionRepository;

    private Utilisateur mary;
    private Utilisateur thom;
    private Activite randonnee;
    private Activite lindyHop;
    private Activite taekwondo;
    private Inscription maryOnTaekwondo;
    private Inscription thomOnRandonnee;
    private Inscription thomOnLindyhop;

    public void initActivites() {
        initMary();
        initAndSaveRandonnee();
        initAndSaveLindyHop();
        initThom();
        initAndSaveTaekwondo();
    }

    public void initInscriptions() {
        initThomOnLindyhop();
        initThomOnRandonnee();
        initMaryOnTaekwondo();
    }

    private void initMaryOnTaekwondo() {
        maryOnTaekwondo = new Inscription();
        maryOnTaekwondo.setUtilisateur(mary);
        maryOnTaekwondo.setActivite(taekwondo);
        inscriptionRepository.save(maryOnTaekwondo);
    }

    private void initThomOnRandonnee() {
        thomOnRandonnee = new Inscription();
        thomOnRandonnee.setUtilisateur(thom);
        thomOnRandonnee.setActivite(randonnee);
        inscriptionRepository.save(thomOnRandonnee);
    }

    private void initThomOnLindyhop() {
        thomOnLindyhop = new Inscription();
        thomOnLindyhop.setUtilisateur(thom);
        thomOnLindyhop.setActivite(lindyHop);
        inscriptionRepository.save(thomOnLindyhop);
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

    public Inscription getMaryOnTaekwondo() {
        return maryOnTaekwondo;
    }

    public Inscription getThomOnRandonnee() {
        return thomOnRandonnee;
    }

    public Inscription getThomOnLindyhop() {
        return thomOnLindyhop;
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
