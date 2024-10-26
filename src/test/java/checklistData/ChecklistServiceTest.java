package checklistData;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Optional;

import java.util.List;

public class ChecklistServiceTest {

    @Test
    public void deleteChecklistShouldInformIfWasRemoved() {
        ChecklistRepository mock = Mockito.mock(ChecklistRepository.class);
        long existingId = 10;
        long notExistingId = 20;
        Mockito.when(mock.existsById(existingId)).thenReturn(true);
        //Mockito.when(mock.existsById(notExistingId)).thenReturn(false);

        ChecklistService checklistService = new ChecklistService(mock);

        boolean existingDeleted = checklistService.deleteChecklist(existingId);
        boolean nonExistingDeleted = checklistService.deleteChecklist(notExistingId);

        Assertions.assertTrue(existingDeleted);
        Assertions.assertFalse(nonExistingDeleted);
    }

    @Test
    public void updateChecklistShouldDataBeUpdatedCorrectly() {
        ChecklistRepository repositoryMock = Mockito.mock(ChecklistRepository.class);

        long existingId = 1;
        Checklist checklistOld = new Checklist(1L);
        checklistOld.setRooms(List.of(new ChecklistRoom(1L)));
        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryMock.findById(existingId)).thenReturn(Optional.of(checklistOld));

        ChecklistService checklistService = new ChecklistService(repositoryMock);

        Checklist checklistNew = new Checklist(1L);
        checklistNew.setRooms(List.of(new ChecklistRoom(12L)));

        Mockito.when(repositoryMock.save(new Checklist(1))).thenReturn(checklistNew);

        Checklist checklistResultUpdated = checklistService.updateChecklist(1L, checklistNew);

        Assertions.assertEquals(checklistNew.getRooms(), checklistResultUpdated.getRooms());
    }

    @Test
    public void updateChecklistShouldDataBeNotUpdatedCorrectly() {
        ChecklistRepository repositoryMock = Mockito.mock(ChecklistRepository.class);

        long notExisting = 15;
        Checklist checklistOld = new Checklist(1L);
        checklistOld.setRooms(List.of(new ChecklistRoom(1L)));
        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(repositoryMock.findById(notExisting)).thenReturn(Optional.of(checklistOld));

        ChecklistService checklistService = new ChecklistService(repositoryMock);

        Checklist checklistNew = new Checklist(1L);
        checklistNew.setRooms(List.of(new ChecklistRoom(12L)));

        Mockito.when(repositoryMock.save(new Checklist(1))).thenReturn(checklistNew);

        Assertions.assertThrows(RuntimeException.class, () -> checklistService.updateChecklist(1L, checklistNew));
        Mockito.verify(repositoryMock,Mockito.never()).save(Mockito.any());
    }

}
