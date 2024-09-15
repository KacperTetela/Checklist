package checklistData;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChecklistServiceTest {

    @Test
    public void deleteChecklistShouldInformIfWasRemoved() {
        ChecklistRepository mock = Mockito.mock(ChecklistRepository.class);
        long existingId = 10;
        long notExistingId = 20;
        Mockito.when(mock.existsById(existingId)).thenReturn(true);
        Mockito.when(mock.existsById(notExistingId)).thenReturn(false);


        ChecklistService checklistService = new ChecklistService(mock);

        boolean result1 = checklistService.deleteChecklist(existingId);
        boolean result2 = checklistService.deleteChecklist(notExistingId);

        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
    }


}
