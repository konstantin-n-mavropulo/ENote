package com.epam.university.spring.enote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = Note.DELETE, query = "DELETE FROM Note n WHERE n.id=:id"),
        @NamedQuery(name = Note.DELETE_ALL, query = "DELETE FROM Note"),
        @NamedQuery(name = Note.ALL_SORTED, query = "SELECT n FROM Note n ORDER BY n.title"),
        @NamedQuery(name = Note.GET_BY_NOTEPAD_ID, query = "SELECT n FROM Note n WHERE " +
                "(n.notepad).id=:id"),
})
@Entity
@Table(name = "notes", uniqueConstraints = {@UniqueConstraint(columnNames = {"notepad_id",
        "title"}, name = "notes_unique_notepad_title")})
public class Note extends AbstractBaseEntity {

    public static final String DELETE = "Note.delete";
    public static final String DELETE_ALL = "Note.deleteAll";
    public static final String ALL_SORTED = "Note.getAllSorted";
    public static final String GET_BY_NOTEPAD_ID = "Note.getByNotepadId";

    @Column(name = "title", nullable = false)
    @NotNull
    @Size(min = 1, max = 128)
    public String title;

    @Column(name = "description")
    @Size(max = 120)
    public String description;

    //TODO - optional - try for different cases EAGER vs LAZY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notepad_id", nullable = false)
    @NotNull
    public Notepad notepad;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "notes_tags",
            joinColumns = {@JoinColumn(name = "note_id",
                    referencedColumnName = "id",
                    nullable = false),
            },
            inverseJoinColumns = {@JoinColumn(name = "tag_id",
                    referencedColumnName = "id",
                    nullable = false)})
    Set<Tag> tags = new HashSet<>();

    public Note(Note note) {
        this(note.getId(), note.getTitle(), note.getDescription(), note.getNotepad());
    }

    public Note(Integer id, String title, String description, Notepad notepad) {
        super(id);
        this.title = title;
        this.description = description;
        this.notepad = notepad;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", notepad=" + notepad +
                ", tags=" + tags +
                ", id=" + id +
                '}';
    }
}
