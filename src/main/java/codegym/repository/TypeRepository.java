package codegym.repository;

import codegym.model.Type;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TypeRepository extends PagingAndSortingRepository<Type,Long>
{
}
