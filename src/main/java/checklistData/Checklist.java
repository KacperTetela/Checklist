package checklistData;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "checklists")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChecklistRoom> rooms;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Checklist(long id) {
        this.id = id;
    }

    public Checklist() {
    }

    public long getId() {
        return id;
    }

    public List<ChecklistRoom> getRooms() {
        return rooms;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    void setRooms(List<ChecklistRoom> rooms) {
        this.rooms = rooms;
        rooms.forEach(room -> room.setChecklist(this));
    }

    void timeUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void clear() {
        rooms.forEach(room -> room.setChecklist(null));
        rooms.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checklist checklist = (Checklist) o;
        return id == checklist.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
