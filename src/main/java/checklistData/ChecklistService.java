package checklistData;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return checklistRepository.findById(id)
                .orElseThrow();
    }

    List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    List<ChecklistSummaryDTO> getIdDateFromAllChecklists() {
        return checklistRepository.getIdDateFromAllChecklists();
    }

    public Checklist updateChecklist(long id, Checklist newChecklist) {
        Checklist oldChecklistToOverride = checklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Checklist not found"));

        List<ChecklistRoom> newChecklistToOverride = new ArrayList<>(newChecklist.getRooms());

        oldChecklistToOverride.setRooms(newChecklistToOverride);
        return checklistRepository.save(oldChecklistToOverride);
    }

    Checklist getLastChecklist() {
        return checklistRepository.getLastChecklist();
    }

    public boolean deleteChecklist(long id) {
        if (!checklistRepository.existsById(id))
            return false;
        checklistRepository.deleteById(id);
        return true;
    }
}
