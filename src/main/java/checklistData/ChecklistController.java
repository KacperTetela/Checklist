package checklistData;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/checklists")
public class ChecklistController {
    private ChecklistService checkListService;

    public ChecklistController(ChecklistService checkListService) {
        this.checkListService = checkListService;
    }

    @PostMapping
    public Checklist add(@RequestBody Checklist checklist) {
        return checkListService.addChecklist(checklist);
    }

    @PutMapping("/{id}")
    public Checklist update(@PathVariable long id, @RequestBody Checklist checklist) {
        return checkListService.updateChecklist(id, checklist);
    }

    @GetMapping("/last")
    public Checklist getLastChecklist() {
        return checkListService.getLastChecklist();
    }

    // New method
    @GetMapping()
    public List<Checklist> getAll(boolean last) {
        if (last) {
            return Collections.singletonList(checkListService.getLastChecklist());
        }
        return checkListService.getAllChecklists();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return checkListService.deleteChecklist(id);
    }
}