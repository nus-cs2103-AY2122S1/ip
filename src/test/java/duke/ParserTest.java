package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import org.junit.jupiter.api.Test;

import duke.commands.AddEventCommand;

public class ParserTest {
    @Test
    public void addTodoTest() throws IOException {
        Parser parser = new Parser();
        try {
            TaskList tasks = new TaskList();
            Command c = parser.getCommand("todo read book", tasks);
            AddTodoCommand command =  (AddTodoCommand) c;
            assertEquals(command.getDesc().trim(), "read book");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void addEventTest() throws IOException {
        Parser parser = new Parser();
        try {
            TaskList tasks = new TaskList();
            Command c = parser.getCommand("event project meeting /at 6/8/21 16:00", tasks);
            AddEventCommand command =  (AddEventCommand) c;
            assertEquals(command.getDesc().trim(), "project meeting");
            assertEquals(command.getAt(), "2021-08-06T16:00");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void addDeadlineTest() throws IOException {
        Parser parser = new Parser();
        try {
            TaskList tasks = new TaskList();
            Command c = parser.getCommand("deadline return book /by 6/8/21 16:00", tasks);
            AddDeadlineCommand command =  (AddDeadlineCommand) c;
            assertEquals(command.getDesc().trim(), "return book");
            assertEquals(command.getBy(), "2021-08-06T16:00");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }
}
