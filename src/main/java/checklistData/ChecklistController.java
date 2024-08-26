package checklistData;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v0/checklists")
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

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return checkListService.deleteChecklist(id);
    }
}