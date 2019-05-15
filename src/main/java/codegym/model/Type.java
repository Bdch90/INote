package codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
public class Type
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Note> notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
