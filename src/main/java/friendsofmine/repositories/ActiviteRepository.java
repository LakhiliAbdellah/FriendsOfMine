package friendsofmine.repositories;

import friendsofmine.Activite;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for Activite objects
 */
public interface ActiviteRepository extends PagingAndSortingRepository<Activite, Long> {
}
