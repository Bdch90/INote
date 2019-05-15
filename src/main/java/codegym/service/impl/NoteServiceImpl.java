package codegym.service.impl;

import codegym.model.Note;
import codegym.model.Type;
import codegym.repository.NoteRepository;
import codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NoteServiceImpl implements NoteService
{
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void remove(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public Page<Note> findAllByNameContaining(String name,Pageable pageable) {
        return noteRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Iterable<Note> findAllByType(Type type) {
        return noteRepository.findAllByType(type);
    }
}
