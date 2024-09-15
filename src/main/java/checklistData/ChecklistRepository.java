package checklistData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    @Query("""
            SELECT c FROM Checklist c
            WHERE c.createdAt = (
                SELECT MAX(c2.createdAt) FROM Checklist c2
            )""")
    public Checklist getLastChecklist();

    @Query("""
            SELECT new checklistData.ChecklistSummaryDTO(c.id, c.createdAt)
            FROM Checklist c
            """)
    public List<ChecklistSummaryDTO> getIdDateFromAllChecklists();

}
