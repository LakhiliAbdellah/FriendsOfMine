package friendsofmine.repositories;

import friendsofmine.Inscription;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for Activite objects
 */
public interface InscriptionRepository extends PagingAndSortingRepository<Inscription, Long> {

}
