package CommandTest;

import duke.Duke;
import duke.DukeException;
import duke.TaskList;
import duke.command.DoneCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    private TaskList myTasks;
    private void start() {
        this.myTasks = new TaskList();
        try {
            myTasks.addTask("todo watch TV");
            myTasks.addTask("event watch TV /at 20/08/2021 16:00");
            myTasks.addTask("todo watch TV again");
            myTasks.addTask("deadline watch friend's TV /by 14/09/2021 15:00");
            myTasks.addTask("todo wash hair");
        } catch (DukeException d) {
            System.out.println(d);
        }

    }

    /**
     * Tests whether an event task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone() {
        start();
        assertEquals("[E][X] watch TV (at: AUGUST 20 2021 16:00)", printDone("done 2", 1));
    }

    /**
     * Tests whether a todo task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone2() {
        start();
        assertEquals("[T][X] watch TV", printDone("done 1", 0));
    }

    /**
     * Tests whether a todo task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone3() {
        start();
        assertEquals("[T][X] watch TV again", printDone("done 3", 2));
    }

    /**
     * Tests whether a deadline task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone4() {
        start();
        assertEquals("[D][X] watch friend's TV (by: SEPTEMBER 14 2021 15:00)", printDone("done 4", 3));
    }

    private String printDone(String s, int pos) {
        DoneCommand c = new DoneCommand(myTasks, s);
        c.execute();
        return myTasks.getTask(pos).toString();
    }

}
