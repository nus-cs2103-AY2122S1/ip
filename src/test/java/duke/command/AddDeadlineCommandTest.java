package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.data.TaskStorageStub;
import duke.exceptions.DukeException;
import duke.io.UserOutputHandlerStub;
import duke.tasks.Deadline;
import duke.tasks.TaskList;


class AddDeadlineCommandTest {
    @Test
    void executingCommandWorks_userInput_addsDeadlineToTaskList() throws DukeException {
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand("x /by 2021-12-12");
        Deadline expectedDeadline = new Deadline("x", false, LocalDate.parse("2021-12-12"));

        UserOutputHandlerStub userOutputHandlerStub = new UserOutputHandlerStub();
        TaskList taskList = new TaskList(new TaskStorageStub());
        addDeadlineCommand.execute(userOutputHandlerStub, taskList);

        assertEquals("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + "\t[D][ ] x (by: DECEMBER 12 2021)\n"
                        + "Now you have 1 task in the list.\n"
                        + "____________________________________________________________",
                userOutputHandlerStub.getWrittenMessage());

        assertEquals(1, taskList.getNumOfTasks());
        assertEquals(expectedDeadline.getTaskRepresentation(), taskList.getTask(0).getTaskRepresentation());
    }
}
