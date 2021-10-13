package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Ui;



class DeadlineCommandTest {
    @Test
    public void deadlineCommandAddedSuccessTest() {
        String input = "deadline return book /by 2021-05-05";
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Command deadlineCommand = new DeadlineCommand(input);
        try {
            deadlineCommand.execute(task, ui, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, task.getTotalNumberOfTask());
    }
}
