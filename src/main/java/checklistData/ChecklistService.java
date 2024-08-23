package checklistData;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {
    private ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    Checklist addChecklist(Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    Checklist getChecklist(long id) {
        return checklistRepository.findById(id).orElseThrow();
    }

    List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    Checklist updateChecklist(long id, Checklist checklist) {
        Checklist existingChecklist = checklistRepository.findById(id)
                .orElseThrow();

        existingChecklist.setRoomNumber(checklist.getRoomNumber());
        existingChecklist.setComplete(checklist.isComplete());
        existingChecklist.setSigner(checklist.getSigner());
        existingChecklist.setComments(checklist.getComments());

        return checklistRepository.save(existingChecklist);
    }

    Checklist getLastChecklist() {
        return checklistRepository.findAll().getLast();
    }
}
