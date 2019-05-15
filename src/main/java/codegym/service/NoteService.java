package codegym.service;

import codegym.model.Note;
import codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService
{
    Page<Note> findAll(Pageable pageable);

    Note findById(Long id);

    void save(Note note);

    void remove(Long id);

    Page<Note> findAllByNameContaining(String name, Pageable pageable);

    Iterable<Note> findAllByType(Type type);
}
