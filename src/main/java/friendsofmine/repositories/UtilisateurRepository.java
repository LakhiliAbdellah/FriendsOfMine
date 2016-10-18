package friendsofmine.repositories;

import friendsofmine.Utilisateur;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * repository interface fo Utilisateur objetcs
 */
public interface UtilisateurRepository extends PagingAndSortingRepository<Utilisateur, Long> {
}
