package checklistData;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChecklistInfoControllerTest {
    //Test End To End

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChecklistRepository checklistRepository;

    @BeforeEach
    void setUp() {
        checklistRepository.deleteAll();
    }

    @Test
    void getAllChecklistsShouldReturnAllChecklists() throws Exception {
        Checklist checklist1 = new Checklist();
        ChecklistRoom room1 = new ChecklistRoom();
        room1.setChecklist(checklist1);
        int temporaryRoomNumber1 = createRandomRoomNumber();
        room1.setRoomNumber(temporaryRoomNumber1);
        checklist1.setRooms(List.of(room1));
        checklistRepository.save(checklist1);

        Checklist checklist2 = new Checklist();
        ChecklistRoom room2 = new ChecklistRoom();
        room2.setChecklist(checklist2);
        int temporaryRoomNumber2 = createRandomRoomNumber();
        room2.setRoomNumber(temporaryRoomNumber2);
        checklist2.setRooms(List.of(room2));
        checklistRepository.save(checklist2);

        Checklist checklist3 = new Checklist();
        ChecklistRoom room3 = new ChecklistRoom();
        room3.setChecklist(checklist3);
        int temporaryRoomNumber3 = createRandomRoomNumber();
        room3.setRoomNumber(temporaryRoomNumber3);
        checklist3.setRooms(List.of(room3));
        checklistRepository.save(checklist3);

        mockMvc.perform(get("/api/v1/checklists/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rooms[0].roomNumber").value(temporaryRoomNumber1))
                .andExpect(jsonPath("$[1].rooms[0].roomNumber").value(temporaryRoomNumber2));
    }

    @Test
    void getIndicatedChecklistsShouldReturnIndicatedChecklists() throws Exception {
        Checklist checklist1 = new Checklist();
        ChecklistRoom room1 = new ChecklistRoom();
        room1.setChecklist(checklist1);
        room1.setRoomNumber(1);
        checklist1.setRooms(List.of(room1));
        checklistRepository.save(checklist1);

        Checklist checklist2 = new Checklist();
        ChecklistRoom room2 = new ChecklistRoom();
        room2.setChecklist(checklist2);
        int roomNumber = createRandomRoomNumber();
        room2.setRoomNumber(roomNumber);
        checklist2.setRooms(List.of(room2));
        checklistRepository.save(checklist2);

        mockMvc.perform(get("/api/v1/checklists/info/" + checkLastChecklistId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rooms[0].roomNumber").value(roomNumber));
    }

    @Test
    void checkIsSummaryCorrect() throws Exception {
        Checklist checklist1 = new Checklist();
        ChecklistRoom room1 = new ChecklistRoom();
        room1.setChecklist(checklist1);
        room1.setRoomNumber(1);
        checklist1.setRooms(List.of(room1));
        checklistRepository.save(checklist1);

        mockMvc.perform(get("/api/v1/checklists/info/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].createdAt").exists())
                .andExpect(jsonPath("$[0].createdAt").isString())
                .andExpect(jsonPath("$[0].createdAt", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}")));
        // Expect the createdAt field to match the ISO 8601 date-time format with microsecond precision
    }

    @Test
    void checkIsSummaryDateCurrent() throws Exception {
        Checklist checklist1 = new Checklist();
        ChecklistRoom room1 = new ChecklistRoom();
        room1.setChecklist(checklist1);
        room1.setRoomNumber(1);
        checklist1.setRooms(List.of(room1));
        checklistRepository.save(checklist1);

        String response = mockMvc.perform(get("/api/v1/checklists/info/summary"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String createdAt = JsonPath.read(response, "$[0].createdAt");
        LocalDateTime createdAtDateTime = LocalDateTime.parse(createdAt);
        LocalDateTime now = LocalDateTime.now();

        assertTrue(Duration.between(createdAtDateTime, now).toMinutes() < 1, "The createdAt date is not current");
        // Check if the createdAt date is within 1 minute of the current time
    }

    private int checkLastChecklistId() throws Exception {
        String response = mockMvc.perform(get("/api/v1/checklists/info"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Integer> ids = JsonPath.read(response, "$[*].id");
        return ids.stream().max(Integer::compare).orElseThrow();
    }

    private int createRandomRoomNumber() {
        return (int) (Math.random() * 1000) + 1;
    }

}