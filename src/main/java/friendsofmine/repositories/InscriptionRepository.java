package friendsofmine.repositories;

import friendsofmine.Inscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Repository interface for Activite objects
 */
public interface InscriptionRepository extends PagingAndSortingRepository<Inscription, Long>, QueryByExampleExecutor<Inscription> {


}
