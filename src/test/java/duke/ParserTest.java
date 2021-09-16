package duke;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.task.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseAddCommand() throws DukeException {
        Parser p = new Parser(new Duke("data/tasks.txt"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        assertEquals(p.parse("deadline return book /by 2021-08-25 23:59"),
                new AddCommand(new Deadline("return book", false,
                        LocalDateTime.parse("2021-08-25 23:59", formatter))));
        assertEquals(p.parse("event group meeting /at 2021-09-01 15:00"),
                new AddCommand(new Event("group meeting", false,
                        LocalDateTime.parse("2021-09-01 15:00", formatter))));
        assertEquals(p.parse("todo read book "),
                new AddCommand(new ToDo("read book", false)));
    }

    @Test
    public void testParseDeleteCommand() throws DukeException {
        Duke d = new Duke("data/tasks.txt");
        Parser p = new Parser(d);
        assertEquals(p.parse("delete 2"), new DeleteCommand(1, d.getTaskList()));
    }

    @Test
    public void testDoneCommand() throws DukeException {
        Parser p = new Parser(new Duke("data/tasks.txt"));
        assertEquals(p.parse("done 5"), new DoneCommand(4));
    }
}
