package duke;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructor() {
        assertEquals("[D][ ] homework (by: 2021-08-07)",
                new Deadline("homework", "2021-08-07").toString());
        assertEquals("[D][ ] project (by: 2022-09-16)",
                new Deadline("project", "2022-09-16").toString());
        assertEquals("[D][ ] submit portfolio (by: 2021-11-10)",
                new Deadline("submit portfolio", "2021-11-10").toString());
    }

    @Test
    public void done() {
        Deadline homework = new Deadline("homework", "2021-08-07");
        homework.markDone();
        assertEquals("[D][X] homework (by: 2021-08-07)", homework.toString());
        Deadline project = new Deadline("project", "2022-09-16");
        project.markDone();
        assertEquals("[D][X] project (by: 2022-09-16)", project.toString());
    }
}
