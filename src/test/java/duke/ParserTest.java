package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkAsDoneCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class ParserTest {
    @Test
    void parser_deleteTask_deleteCommand() {
        try {
            assertEquals(new DeleteCommand(1), new Parser().parse("delete 2"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_list_listCommand() {
        try {
            assertEquals(new ListCommand(), new Parser().parse("list"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_bye_exitCommand() {
        try {
            assertEquals(new ExitCommand(), new Parser().parse("bye"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_find_findCommand() {
        try {
            assertEquals(new FindCommand("abc"), new Parser().parse("find abc"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_markTaskAsDone_markAsDoneCommand() {
        try {
            assertEquals(new MarkAsDoneCommand(0), new Parser().parse("done 1"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_addToDo_addCommand() {
        try {
            assertEquals(new AddCommand(new ToDo("abc")), new Parser().parse("todo abc"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_addDeadline_addCommand() {
        try {
            assertEquals(new AddCommand(new Deadline("abc", "2021/09/17 10:10")),
                    new Parser().parse("deadline abc /by 2021/09/17 10:10"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_addEvent_addCommand() {
        try {
            assertEquals(new AddCommand(new Event("abc", "2021/09/17 10:10", "2021/09/17 21:10")),
                    new Parser().parse("event abc /at 2021/09/17 10:10--2021/09/17 21:10"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
