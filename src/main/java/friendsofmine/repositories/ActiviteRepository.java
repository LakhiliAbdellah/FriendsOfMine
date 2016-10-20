package friendsofmine.repositories;

import friendsofmine.Activite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository interface for Activite objects
 */
public interface ActiviteRepository extends PagingAndSortingRepository<Activite, Long> {

    @Query("select activite from Activite activite join fetch activite.responsable order by activite.titre asc")
    List<Activite> findAll();

}
