package codegym.controller;

import codegym.model.Note;
import codegym.model.Type;
import codegym.service.NoteService;
import codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController
{
    @Autowired
    private NoteService noteService;

    @Autowired
    private TypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> types()
    {
        return typeService.findAll();
    }
    @GetMapping("/home")
    public ModelAndView noteList(@RequestParam("s") Optional<String> s,
                             @PageableDefault(size = 5) Pageable pageable )
    {
        Page<Note> notes;

        if(s.isPresent())
        {
            notes = noteService.findAllByNameContaining(s.get(), pageable);
        }
        else
        {
            notes = noteService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/note/home");
        modelAndView.addObject("notes",notes);
        return modelAndView;
    }

    @GetMapping("/create-Note")
    public ModelAndView createNoteForm()
    {
        ModelAndView modelAndView = new ModelAndView("/note/create","note" ,new Note());
        return modelAndView;
    }

    @PostMapping("/save-Note")
    public ModelAndView saveNote(@ModelAttribute("note") Note note)
    {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("message","Add success");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView editForm(@PathVariable Long id)
    {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/edit","note",note);
        return modelAndView;
    }

    @PostMapping("/edit-note")
    public ModelAndView editNote(@ModelAttribute("note") Note note)
    {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note", note);
        modelAndView.addObject("message","Edit completed");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView deleteForm(@PathVariable Long id)
    {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/delete","note",note);
        return modelAndView;
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute("note") Note note)
    {
        noteService.remove(note.getId());
        return "redirect:home";
    }

}
