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

    public Checklist updateChecklist(long id, Checklist newChecklist) {
        Checklist checklist = checklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Checklist not found"));

        newChecklist.getRooms()
                .forEach(room -> room.setChecklist(checklist));

        checklist.getRooms().clear();
        checklist.getRooms().addAll(newChecklist.getRooms());
        checklist.timeUpdate();

        return checklistRepository.save(checklist);
    }

    Checklist getLastChecklist() {
        return checklistRepository.findAll().getLast();
    }

    public boolean deleteChecklist(long id) {
        checklistRepository.deleteById(id);
        if (checklistRepository.findById(id).isPresent())
            return false;
        return true;
    }
}
