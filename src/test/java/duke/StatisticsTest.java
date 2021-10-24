package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class StatisticsTest {
    @Test
    public void test() {
        Statistics statistics = new Statistics();
        statistics.incrementTaskAdded();
        statistics.incrementTaskDeleted();
        statistics.incrementTaskDone();
        assertEquals("1", statistics.getTasksDone());
        assertEquals("1", statistics.getTasksAdded());
        assertEquals("1", statistics.getTasksDeleted());

        Statistics existingStatistics = new Statistics(3,4,10);
        assertEquals("3", existingStatistics.getTasksDone());
        assertEquals("4", existingStatistics.getTasksAdded());
        assertEquals("10", existingStatistics.getTasksDeleted());


    }
}
