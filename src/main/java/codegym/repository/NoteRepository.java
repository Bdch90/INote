package codegym.repository;

import codegym.model.Note;
import codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note,Long>
{
    Iterable<Note> findAllByType(Type type);

    Page<Note> findAllByNameContaining(String name, Pageable pageable);
}
