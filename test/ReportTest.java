import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.logic.entities.Report;

public class ReportTest {

    @Test
    public void ShouldIncrementCount() {
        Report report = new Report("Ah", 0, "Ah");
        report.incrementCount();
        Assertions.assertEquals(1, report.getCount());

        report.incrementCount();
        report.incrementCount();
        Assertions.assertEquals(3, report.getCount());
    }
}
