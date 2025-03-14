package checklistData;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/checklists/info")
public class ChecklistInfoController {
    private ChecklistService checklistService;

    public ChecklistInfoController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @GetMapping
    public List<Checklist> getAllChecklists() {
        return checklistService.getAllChecklists();
    }

    @GetMapping("/{id}")
    public Checklist getChecklist(@PathVariable long id) {
        return checklistService.getChecklist(id);
    }

    @GetMapping("/summary")
    public List<ChecklistSummaryDTO> getIdDateFromAllChecklists() {
        return checklistService.getIdDateFromAllChecklists();
    }
}
