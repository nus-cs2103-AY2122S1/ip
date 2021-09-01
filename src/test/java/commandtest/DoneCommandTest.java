package commandtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.command.DoneCommand;

public class DoneCommandTest {
    private TaskList myTasks;

    DoneCommandTest() {
        this.start();
    }

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
        String result = printExpect(1);
        assertEquals(result, printDone("done 2", 1));
    }

    /**
     * Tests whether a todo task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone2() {
        String result = printExpect(0);
        assertEquals(result, printDone("done 1", 0));
    }

    /**
     * Tests whether a todo task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone3() {
        String result = printExpect(2);
        assertEquals(result, printDone("done 3", 2));
    }

    /**
     * Tests whether a deadline task can be marked as done successfully
     */
    @Test
    public void markTask_differentType_asDone4() {
        String result = printExpect(3);
        assertEquals(result, printDone("done 4", 3));
    }

    private String printDone(String s, int pos) {
        DoneCommand c = new DoneCommand(myTasks, s);
        c.execute();
        return myTasks.getTask(pos).toString();
    }

    private String printExpect(int pos) {
        String[] s = this.myTasks.getTask(pos).toString().split(" ");
        String result = "";
        for (int i = 0; i < s.length; i++) {
            if (i == 1) {
                result += "X" + s[i];
            } else if (i != 0) {
                result += " " + s[i];
            } else {
                result += s[i];
            }
        }
        return result;
    }

}
