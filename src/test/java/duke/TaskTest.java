package duke;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    public void deadlineTaskTest(){
        Task task = new Deadline("Submit assignment", "2021-09-01");
        assertEquals("[D][ ] Submit assignment (by: Sep 1 2021)", task.toString());
        task.markAsDone();
        assertEquals("[D][X] Submit assignment (by: Sep 1 2021)", task.toString());
    }
}
