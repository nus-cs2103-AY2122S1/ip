package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.EventTask;
import duke.task.ToDoTask;

public class CommandTest {
    @Test
    public void textTest_commandBye_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.BYE, "").execute(new TaskList(),
                    new Storage("data/duke.txt")), "Bye. Hope to see you again soon! \\_(\"v\")_/");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandDeadline_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.DEADLINE, "Return The Da Vinci Code /by 25/08/2000"
                    + " 2359").execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added "
                    + "this task:\n  [D][ ] Return The Da Vinci Code (by: Aug 25 2000, 23:59)\n"
                    + "Now you have 1 tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandDelete_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.DELETE, "1").execute(tasks, new Storage(
                    "data/duke.txt")), "Noted. I've removed this task:\n"
                    + "  [T][ ] Go to the Bookstore");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandDone_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.DONE, "1").execute(tasks, new Storage(
                    "data/duke.txt")), "Nice! I've marked this task "
                    + "as done:\n  [T][X] Go to the Bookstore");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandEvent_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.EVENT, "Abhishek's Wedding /at 01/09/2000 1800")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added "
                    + "this task:\n  [E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\nNow you have 1 tasks in the"
                    + " list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandFind_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new EventTask("Abhishek's Wedding", "01/09/2000 1800"));
        try {
            assertEquals(new Command(Command.CommandTypes.FIND, "Abhishek's Wedding")
                    .execute(tasks, new Storage("data/duke.txt")), "These are the tasks matching the "
                    + "Search String:\n1.[E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandList_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Mall"));
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.LIST, "").execute(tasks, new Storage(
                    "data/duke.txt")), "Here are the tasks in your"
                    + " list:\n1.[T][ ] Go to the Mall\n2.[T][ ] Go to the Bookstore\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandTodo_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.TODO, "Go to the Bookstore").execute(new TaskList(),
                    new Storage("data/duke.txt")), "Got it. I've added this task:\n  [T][ ] Go to the "
                    + "Bookstore\nNow you have 1 tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void textTest_commandByeIncorrectInput_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.BYE, "IncorrectInput").execute(new TaskList(),
                    new Storage("data/duke.txt")), "Bye. Hope to see you again soon! \\_(\"v\")_/");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "OOPS! I'm sorry, but I don't know that command");
        }
    }

    @Test
    public void textTest_commandDeadlineIncorrectInput_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.DEADLINE, "").execute(new TaskList(),
                    new Storage("data/duke.txt")), "Got it. I've added this task:\n"
                    + "  [D][ ] Return The Da Vinci Code (by: Aug 25 2000, 23:59)\nNow you have 1 tasks in the list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Deadline Command!!, \nCorrect Format --> "
                    + "deadline <Description> /by <dd/MM/yyyy HHmm>");
        }
    }

    @Test
    public void textTest_commandDeadlineIncorrectInputNoDescription_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.DEADLINE, "/by 25/08/2000 2359")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added this task:"
                    + "\n  [D][ ] Return The Da Vinci Code (by: Aug 25 2000, 23:59)\n"
                    + "Now you have 1 tasks in the list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Deadline Command!!, \nCorrect Format --> "
                    + "deadline <Description> /by <dd/MM/yyyy HHmm>");
        }
    }

    @Test
    public void textTest_commandDeadlineIncorrectInputNoDeadline_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.DEADLINE, "Return The Da Vinci Code")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added this task:"
                    + "\n  [D][ ] Return The Da Vinci Code (by: Aug 25 2000, 23:59)\n"
                    + "Now you have 1 tasks in the list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Deadline Command!!, \nCorrect Format --> "
                    + "deadline <Description> /by <dd/MM/yyyy HHmm>");
        }
    }

    @Test
    public void textTest_commandDeleteIncorrectInput_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.DELETE, "IncorrectInput").execute(tasks, new Storage(
                    "data/duke.txt")), "Noted. I've removed this task:\n"
                    + "  [T][ ] Go to the Bookstore");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Delete Command!!, "
                    + "\nCorrect Format --> delete <index>");
        }
    }

    @Test
    public void textTest_commandDeleteIncorrectInputNoTasks_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.DELETE, "1").execute(new TaskList(), new Storage(
                    "data/duke.txt")), "Noted. I've removed this task:\n"
                    + "  [T][ ] Go to the Bookstore");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Index!! There are no Tasks in the Task List");
        }
    }

    @Test
    public void textTest_commandDeleteIncorrectInputIndex_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.DELETE, "123").execute(tasks, new Storage(
                    "data/duke.txt")), "Noted. I've removed this task:\n"
                    + "  [T][ ] Go to the Bookstore");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Index!! There are 1 tasks in the Task List");
        }
    }

    @Test
    public void textTest_commandDoneIncorrectInput_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.DONE, "IncorrectInput").execute(tasks, new Storage(
                    "data/duke.txt")), "Nice! I've marked this task "
                    + "as done:\n  [T][X] Go to the Bookstore");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Done Command!!, "
                    + "\nCorrect Format --> done <index>");
        }
    }

    @Test
    public void textTest_commandDoneIncorrectInputNoTasks_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.DONE, "1").execute(new TaskList(), new Storage(
                    "data/duke.txt")), "Nice! I've marked this task as done:\n"
                    + "  [T][X] Go to the Bookstore");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Index!! There are no Tasks in the Task List");
        }
    }

    @Test
    public void textTest_commandDoneIncorrectInputIndex_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.DONE, "123").execute(tasks, new Storage(
                    "data/duke.txt")), "Nice! I've marked this task "
                    + "as done:\n  [T][X] Go to the Bookstore");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Index!! There are 1 tasks in the Task List");
        }
    }

    @Test
    public void textTest_commandEventIncorrectInput_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.EVENT, "")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added "
                    + "this task:\n  [E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\nNow you have 1 tasks in the"
                    + " list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Event Command!!, \n"
                    + "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
        }
    }

    @Test
    public void textTest_commandEventIncorrectInputNoDescription_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.EVENT, "/at 01/09/2000 1800")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added "
                    + "this task:\n  [E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\nNow you have 1 tasks in the"
                    + " list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Event Command!!, \n"
                    + "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
        }
    }

    @Test
    public void textTest_commandEventIncorrectInputNoDetails_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.EVENT, "Abhishek's Wedding")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Got it. I've added "
                    + "this task:\n  [E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\nNow you have 1 tasks in the"
                    + " list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Event Command!!, \n"
                    + "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
        }
    }

    @Test
    public void textTest_commandFindIncorrectInput_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new EventTask("Abhishek's Wedding", "01/09/2000 1800"));
        try {
            assertEquals(new Command(Command.CommandTypes.FIND, "")
                    .execute(tasks, new Storage("data/duke.txt")), "Here are the tasks in your list:\n"
                    + "1.[E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\n");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the Find Command!!,\n"
                    + "Correct Format --> find <search_string>");
        }
    }

    @Test
    public void textTest_commandFindIncorrectInputNoTasks_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.FIND, "IncorrectInput")
                    .execute(new TaskList(), new Storage("data/duke.txt")), "Here are the tasks in your "
                    + "list:\n" + "1.[E][ ] Abhishek's Wedding (at: Sep 01 2000, 18:00)\n");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The Task List is Empty");
        }
    }

    @Test
    public void textTest_commandListIncorrectInput_correctOutput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDoTask("Go to the Mall"));
        tasks.addTask(new ToDoTask("Go to the Bookstore"));
        try {
            assertEquals(new Command(Command.CommandTypes.LIST, "IncorrectInput").execute(tasks, new Storage(
                    "data/duke.txt")), "Here are the tasks in your"
                    + " list:\n1.[T][ ] Go to the Mall\n2.[T][ ] Go to the Bookstore\n");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "OOPS! I'm sorry, but I don't know that command");
        }
    }

    @Test
    public void textTest_commandTodoIncorrectInputNoDescription_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.TODO, "").execute(new TaskList(),
                    new Storage("data/duke.txt")), "Got it. I've added this task:\n  [T][ ] Go to the "
                    + "Bookstore\nNow you have 1 tasks in the list.");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Incorrect Format of the ToDo Command!!, "
                    + "\nCorrect Format --> todo <Description>");
        }
    }

    @Test
    public void textTest_commandUnknown_correctOutput() {
        try {
            assertEquals(new Command(Command.CommandTypes.UNKNOWN, "").execute(new TaskList(),
                    new Storage("data/duke.txt")), "");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "OOPS! I'm sorry, but I don't know that command");
        }
    }
}
