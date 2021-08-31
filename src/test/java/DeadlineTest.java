import duke.task.Deadline;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void statusIconTest(){
        LocalDate date = LocalDate.parse("2021-01-01");
        Deadline dl = new Deadline("family celeb", date);
        dl.markAsDone();
        Assertions.assertEquals(dl.getStatusIcon(), "X");
    }
}
