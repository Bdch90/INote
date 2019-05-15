package codegym.controller;

import codegym.model.Type;
import codegym.service.NoteService;
import codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/type")
public class TypeController
{
    @Autowired
    private TypeService typeService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/list-type")
    public ModelAndView listType()
    {
        Iterable<Type> types = typeService.findAll();
        ModelAndView modelAndView = new ModelAndView("/type/list");
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("/create-type")
    public ModelAndView createTypeForm()
    {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create-type")
    public ModelAndView saveType(@ModelAttribute("type") Type type)
    {
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("/type/create","type",type);
        modelAndView.addObject("message","Add successful");
        return modelAndView;
    }

    @GetMapping("/edit-type/{id}")
    public ModelAndView editTypeForm(@PathVariable Long id) {
        Type type = typeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/type/edit", "type", type);
        return modelAndView;
    }

    @PostMapping("/edit-type")
    public ModelAndView editType(@ModelAttribute("type") Type type)
    {
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("/type/edit", "type", type);
        modelAndView.addObject("message","Edit completed");
        return modelAndView;
    }

    @GetMapping("/delete-type/{id}")
    public ModelAndView deleteForm(@PathVariable Long id)
    {
        Type type = typeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/type/delete", "type", type);
        return modelAndView;
    }

    @PostMapping("/delete-type")
    public String deleteType(@ModelAttribute("type") Type type)
    {
        typeService.remove(type.getId());
        return "redirect:/type/list-type";
    }
}
