package checklistData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ChecklistControllerTest {

    private ChecklistService checklistService;
    private ChecklistController checklistController;

    @BeforeEach
    void setUp() {
        checklistService = mock(ChecklistService.class);
        checklistController = new ChecklistController(checklistService);
    }

    @Test
    void add() {
        Checklist checklist = new Checklist();
        when(checklistService.addChecklist(any(Checklist.class))).thenReturn(checklist);

        Checklist result = checklistController.add(checklist);

        assertEquals(checklist, result);
        verify(checklistService).addChecklist(checklist);
    }

    @Test
    void update() {
        Checklist checklist = new Checklist();
        when(checklistService.updateChecklist(anyLong(), any(Checklist.class))).thenReturn(checklist);

        Checklist result = checklistController.update(1L, checklist);

        assertEquals(checklist, result);
        verify(checklistService).updateChecklist(1L, checklist);
    }

    @Test
    void getLastChecklist() {
        Checklist checklist = new Checklist();
        when(checklistService.getLastChecklist()).thenReturn(checklist);

        Checklist result = checklistController.getLastChecklist();

        assertEquals(checklist, result);
        verify(checklistService).getLastChecklist();
    }

    @Test
    void delete() {
        when(checklistService.deleteChecklist(anyLong())).thenReturn(true);

        boolean result = checklistController.delete(1L);

        assertTrue(result);
        verify(checklistService).deleteChecklist(1L);
    }
}