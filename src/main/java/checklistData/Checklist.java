package checklistData;

import jakarta.persistence.*;

@Entity
@Table(name = "checklists")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private short roomNumber;
    private boolean complete;
    private String signer;
    private String comments;

    public Checklist() {
    }

    public Checklist(short roomNumber, boolean complete, String signer, String comments) {
        this.roomNumber = roomNumber;
        this.complete = complete;
        this.signer = signer;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public short getRoomNumber() {
        return roomNumber;
    }

    public String getSigner() {
        return signer;
    }

    public boolean isComplete() {
        return complete;
    }

    public String getComments() {
        return comments;
    }

    public void setRoomNumber(short roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Checklist{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", complete=" + complete +
                ", signer='" + signer + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
