package checklistData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "checklist_rooms")
public class ChecklistRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "checklist_id")
    @JsonIgnore
    private Checklist checklist;

    private int roomNumber;
    private boolean complete;
    private String signer;
    private String comment;

    public ChecklistRoom(Long id) {
        this.id = id;
    }

    public ChecklistRoom() {
    }

    public Long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isComplete() {
        return complete;
    }

    public String getSigner() {
        return signer;
    }

    public String getComment() {
        return comment;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
