import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.Ui;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindByDateCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

public class ParserTest {

    private TaskList makeTasks() {
        Event event = new Event("Give tuition", LocalDateTime.parse("2021-11-23T00:00"),
            LocalDateTime.parse("2021-11-23T00:00"), false, true);
        Todo todo = new Todo("Feed Momo", true);
        Deadline deadline = new Deadline("Assignment", LocalDateTime.parse("2021-11-23T00:00"));

        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        taskArrayList.add(event);
        taskArrayList.add(todo);
        taskArrayList.add(deadline);

        TaskList tasks = new TaskList(taskArrayList);

        return tasks;
    }


    @Test
    public void testParseValidInput() {

        String inputList = "list";
        String inputDone = "done 1";
        String inputFind = "find/date 2021-11-23";
        String inputDelete = "delete 1";
        String inputTodo = "todo party";
        String inputDeadline = "deadline party /by 2021-11-23";
        String inputEvent = "event party /at 2021-11-23 to 2021-11-24";
        String inputExit = "bye";

        try {
            Assertions.assertEquals(true, Parser.parse(inputList, null, makeTasks(), null)
                instanceof ListCommand, "Wrongly parses list command");

            Assertions.assertEquals(true, Parser.parse(inputDone, null, makeTasks(), null)
                instanceof MarkDoneCommand, "Wrongly parses mark done command");

            Assertions.assertEquals(true, Parser.parse(inputFind, null, makeTasks(), null)
                instanceof FindByDateCommand, "Wrongly parses find command");

            Assertions.assertEquals(true, Parser.parse(inputDelete, null, makeTasks(), null)
                instanceof DeleteCommand, "Wrongly parses delete command");

            Assertions.assertEquals(true, Parser.parse(inputExit, null, makeTasks(), null)
                instanceof ExitCommand, "Wrongly parses exit command");

            AddCommand todo = (AddCommand) Parser.parse(inputTodo, null, makeTasks(), null);
            Assertions.assertEquals(true, todo.getTaskType() == TaskType.TODO,
                "Wrongly parses add todo command");

            AddCommand event = (AddCommand) Parser.parse(inputEvent, null, makeTasks(), null);
            Assertions.assertEquals(true, event.getTaskType() == TaskType.EVENT,
                "Wrongly parses add todo command");

            AddCommand deadline = (AddCommand) Parser.parse(inputDeadline, null, makeTasks(), null);
            Assertions.assertEquals(true, deadline.getTaskType() == TaskType.DEADLINE,
                "Wrongly parses add todo command");
        } catch (Exception e) {
            Assertions.fail();
        }


        String expectedUiList = "1. [E][ ] Give tuition (at: 23 November 2021 to 23 November 2021)\n"
            + "2. [T][X] Feed Momo\n"
            + "3. [D][ ] Assignment (by: 23 November 2021 00:00)";

        class StubUi extends Ui {
            @Override
            public String list(TaskList tasks) {
                return expectedUiList;
            }
        }

        String expectedExceptionMessageMarkTask = "Please specify a task you would like marked as done Sir/Mdm:\n"
            + expectedUiList;


        try {
            Parser.parse("done", new StubUi(), makeTasks(), null);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals(expectedExceptionMessageMarkTask, e.getMessage());
        }

        String expectedExceptionMessageNoTask = "You have no tasks to mark as done Sir/Mdm!";

        try {
            Parser.parse("done 1", null, new TaskList(), null);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals(expectedExceptionMessageNoTask, e.getMessage());
        }

        String expectedExceptionMessageNotANumber = "Please enter a proper number within this range Sir/Mdm:\n"
            + expectedUiList;

        try {
            Parser.parse("done I", new StubUi(), makeTasks(), null);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals(expectedExceptionMessageNotANumber, e.getMessage());
        }

        String expectedExceptionMessageNotInRange = "Please specify a task within this range Sir/Mdm:\n"
            + expectedUiList;

        try {
            Parser.parse("done 4", new StubUi(), makeTasks(), null);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals(expectedExceptionMessageNotInRange, e.getMessage());
        }

    }

    @Test
    public void testParseBadInput() {

        String inputUnknown = "blah";

        try {
            Parser.parse(inputUnknown, null, null, null);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Pardon me Sir/Mdm, but I do not understand.", e.getMessage(),
                "DukeException should have been thrown with \"Pardon me Sir/Mdm, but I do not "
                    + "understand.\" message");
        }
    }


    @Test
    public void testDateTimeToString() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-11-23T00:00");

        Assertions.assertEquals("23 November 2021", Parser.getDateTimeString(dateTime, true),
            Parser.getDateTimeString(dateTime, true));
        Assertions.assertEquals("23 November 2021 00:00", Parser.getDateTimeString(dateTime, false),
            Parser.getDateTimeString(dateTime, false));
    }


    @Test
    public void testParseDateAndTime() {

        String dateOne = "2021-11-23";
        String dateTwo = "23/11/2021";
        String dateWrong = "23112021";

        String timeOne = "00:00";
        String timeTwo = "0000";
        String timeWrong = "00.00";

        LocalDateTime expectedDateTime = LocalDateTime.parse("2021-11-23T00:00");

        try {
            Assertions.assertEquals(true, Parser.parseDateAndTime(dateOne, timeOne).equals(expectedDateTime),
                "parseDateTime failed");
            Assertions.assertEquals(true, Parser.parseDateAndTime(dateOne, timeTwo).equals(expectedDateTime),
                "parseDateTime failed");
            Assertions.assertEquals(true, Parser.parseDateAndTime(dateTwo, timeOne).equals(expectedDateTime),
                "parseDateTime failed");
            Assertions.assertEquals(true, Parser.parseDateAndTime(dateTwo, timeTwo).equals(expectedDateTime),
                "parseDateTime failed");
        } catch (Exception e) {
            Assertions.fail();
        }

        String expectedMessage = "Wrong format Sir/Mdm. Dates and times must be given as only a date: DATE\n"
            + "or as date and time: DATE TIME\n"
            + "Accepted formats for DATE: YYYY-MM-DD, DD/MM/YYYY\n"
            + "Accepted formats for TIME (24H format): TT:TT, TTTT\n"
            + "Examples for DATE TIME: 13/2/2019 1800, 13/2/2019 18:00, 2019-02-13 1800,\n"
            + "2019-02-13 18:00\n"
            + "Examples for DATE: 13/2/2019, 2019-02-13";

        try {
            Parser.parseDateAndTime(dateWrong, timeOne).equals(expectedDateTime);
            Assertions.fail("parseDateTime failed to detect correct exception");
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage(), e.getMessage());
        }

        try {
            Parser.parseDateAndTime(dateWrong, timeWrong).equals(expectedDateTime);
            Assertions.fail("parseDateTime failed to detect correct exception");
        } catch (Exception e) {
            Assertions.assertEquals(expectedMessage, e.getMessage(), e.getMessage());
        }


    }


}

