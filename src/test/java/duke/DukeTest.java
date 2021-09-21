package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void createTask_success(){
        LocalDate date1 = LocalDate.of(2021, 4, 20);
        LocalDate date2 = LocalDate.of(2021, 6,9);

        LocalTime time = LocalTime.of(8,30);
        LocalTime startTime = LocalTime.of(13, 30);
        LocalTime endTime = LocalTime.of(16,30);

        assertEquals("[T] [ ] Sleep",
                new duke.task.ToDo("Sleep",false).toString());
        assertEquals("[D] [ ] Finish Project (by: 2021-04-20 08:30)",
                new duke.task.Deadline("Finish Project", date1, time,false ).toString());
        assertEquals("[E] [X] CS2103T TP (at: 2021-06-09 13:30 to 16:30)",
                new duke.task.Event("CS2103T TP", date2, startTime, endTime, true).toString());
    }

    @Test
    public void load_invalidPath(){
        ArrayList<duke.task.Task> emptyList = new ArrayList<>();
        String WRONGPATH = "./blah.txt";
        duke.storage.Storage storage = new duke.storage.Storage(WRONGPATH);

        assertEquals(emptyList, storage.load());
    }

    @Test
    public void getTask_success(){
        duke.tasklist.TaskList tasklist = new duke.tasklist.TaskList();

        tasklist.add(new duke.task.ToDo("task1", false));
        tasklist.add(new duke.task.ToDo("task2", false));
        tasklist.add(new duke.task.ToDo("task3", false));
        tasklist.delete(0);
        tasklist.delete(0);
        assertEquals("[T] [ ] task3", tasklist.getTask(0).toString());

    }

}