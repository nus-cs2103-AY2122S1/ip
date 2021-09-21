import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lania.Log;
import lania.Storage;
import lania.Ui;
import lania.command.AddCommand;
import lania.command.Command;
import lania.command.CompleteCommand;
import lania.command.DeleteCommand;
import lania.command.ExitCommand;
import lania.command.FindCommand;
import lania.command.ListCommand;
import lania.command.UndoCommand;
import lania.task.TaskList;
import lania.task.Todo;

public class CommandTest {
    @Test
    public void executeAddCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();
        Command addCommand = new AddCommand(new Todo("test"));

        String result = addCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Lania has added: \n"
                + "[T][ ] test\n"
                + "Great! Now you have 1 task in your list.");
    }

    @Test
    public void executeCompleteCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();

        Command addCommand = new AddCommand(new Todo("test"));
        addCommand.execute(tasks, storage, ui, log);
        Command completeCommand = new CompleteCommand(1);

        String result = completeCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Good job! Lania has marked this task as done:\n"
                + "[T][X] test");
    }

    @Test
    public void executeDeleteCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();

        Command addCommand = new AddCommand(new Todo("test"));
        addCommand.execute(tasks, storage, ui, log);
        Command deleteCommand = new DeleteCommand(1);

        String result = deleteCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Ok, Lania has removed this task:\n"
                + "[T][ ] test\n"
                + "Great! Now you have 0 tasks in your list.");
    }

    @Test
    public void executeExitCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();
        Command exitCommand = new ExitCommand();

        String result = exitCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Bye. Lania looks forward to seeing you again!");
    }

    @Test
    public void executeFindCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();

        Command addCommand = new AddCommand(new Todo("test"));
        addCommand.execute(tasks, storage, ui, log);
        addCommand = new AddCommand(new Todo("testing"));
        addCommand.execute(tasks, storage, ui, log);
        addCommand = new AddCommand(new Todo("fail"));
        addCommand.execute(tasks, storage, ui, log);
        Command findComand = new FindCommand("test");

        String result = findComand.execute(tasks, storage, ui, log);
        assertEquals(result, "You have the following task(s):\n"
                + "1.[T][ ] test\n"
                + "2.[T][ ] testing");
    }

    @Test
    public void executeListCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();

        Command addCommand = new AddCommand(new Todo("test"));
        addCommand.execute(tasks, storage, ui, log);
        addCommand = new AddCommand(new Todo("testing"));
        addCommand.execute(tasks, storage, ui, log);
        addCommand = new AddCommand(new Todo("tested"));
        addCommand.execute(tasks, storage, ui, log);
        Command listCommand = new ListCommand();

        String result = listCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "You have the following task(s):\n"
                + "1.[T][ ] test\n"
                + "2.[T][ ] testing\n"
                + "3.[T][ ] tested");
    }

    @Test
    public void executeUndoCommand() {
        String result;
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/test.txt");
        Ui ui = new Ui();
        Log log = new Log();

        Command addCommand = new AddCommand(new Todo("test"));
        addCommand.execute(tasks, storage, ui, log);
        Command completeCommand = new CompleteCommand(1);
        completeCommand.execute(tasks, storage, ui, log);
        Command deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(tasks, storage, ui, log);
        UndoCommand undoCommand = new UndoCommand();

        result = undoCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Lania has added: \n"
                + "[T][X] test\n"
                + "Great! Now you have 1 task in your list.");
        result = undoCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Lania has marked this task as undone:\n"
                + "[T][ ] test");
        result = undoCommand.execute(tasks, storage, ui, log);
        assertEquals(result, "Ok, Lania has removed this task:\n"
                + "[T][ ] test\n"
                + "Great! Now you have 0 tasks in your list.");
    }
}
