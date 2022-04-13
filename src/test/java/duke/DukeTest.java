package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.data.TaskList;
import duke.io.Command;
import duke.io.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class DukeTest {
    @Test
    public void parserTest() {
        try {
            assertEquals(new Command(Command.CommandName.BYE), Parser.parse("bye"));
            assertEquals(new Command(Command.CommandName.LIST), Parser.parse("list"));
            assertEquals(new Command(Command.CommandName.DONE, new String[]{"2"}), Parser.parse("done 2"));
            assertEquals(new Command(Command.CommandName.DELETE, new String[]{"2"}), Parser.parse("delete 2"));
            assertEquals(new Command(Command.CommandName.TODO, new String[]{"description"}),
                    Parser.parse("todo description"));
            assertEquals(new Command(Command.CommandName.DEADLINE, new String[]{"description", "2021-08-28"}),
                    Parser.parse("deadline description /by 2021-08-28"));
            assertEquals(new Command(Command.CommandName.EVENT, new String[]{"description", "2021-08-28"}),
                    Parser.parse("event description /at 2021-08-28"));
            assertEquals(new Command(Command.CommandName.DATE, new String[]{"2021-08-28"}),
                    Parser.parse("date 2021-08-28"));
        } catch (DukeException e) {
            throw new AssertionError("Test case failed, unintended error in parsing");
        }
    }

    @Test
    public void parserInvalidInputTest() {
        assertThrows(DukeException.class, () -> Parser.parse("todo"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline"));
        assertThrows(DukeException.class, () -> Parser.parse("listtodo"));
        assertThrows(DukeException.class, () -> Parser.parse("list todo"));
        assertThrows(DukeException.class, () -> Parser.parse("event /at 2021-06-12"));
        assertThrows(DukeException.class, () -> Parser.parse("event desc /at"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline desc /by"));
    }

    @Test
    public void taskListFilterTest() {
        TaskList testList = new TaskList();
        testList.add(new Todo("description", true));
        testList.add(new Deadline("A deadline", LocalDate.parse("2021-08-28"), false));
        testList.add(new Event("An event", LocalDate.parse("2021-08-28"), true));
        testList.add(new Deadline("Wrong deadline", LocalDate.parse("2021-08-27"), false));
        testList.add(new Event("Wrong event", LocalDate.parse("2021-08-27"), true));

        TaskList expectedList = new TaskList();
        expectedList.add(new Deadline("A deadline", LocalDate.parse("2021-08-28"), false));
        expectedList.add(new Event("An event", LocalDate.parse("2021-08-28"), true));

        TaskList actualList = testList.filterByDate(LocalDate.parse("2021-08-28"));

        assertEquals(expectedList, actualList);
    }
}
