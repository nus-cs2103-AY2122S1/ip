package abyss;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import abyss.command.Command;
import abyss.command.DeadlineCommand;
import abyss.command.DeleteCommand;
import abyss.command.DoneCommand;
import abyss.command.EventCommand;
import abyss.command.ExitCommand;
import abyss.command.FindCommand;
import abyss.command.ListCommand;
import abyss.command.Parser;
import abyss.command.TodoCommand;
import abyss.exception.AbyssException;
import abyss.exception.LoadTaskException;
import abyss.task.Task;
import abyss.task.TaskList;

/**
 * The Abyss is an application which keeps track of tasks, allowing users to
 * add and delete tasks of multiple types, including deadlines, events and to-dos.
 * The Abyss can also list existing tasks and mark tasks as done.
 */
public class Abyss {
    private static TaskList tasks = new TaskList();
    private static Storage storage;
    private static final String FILE_PATH = "./data/abyss.txt";
    private static final String EXIT_MESSAGE = "Exiting the Abyss. We look forward to your return.";

    /**
     * Returns a response given a user input.
     *
     * @param input Input arguments.
     */
    public String getResponse(String input) {
        String response = "";

        try {
            storage = new Storage(FILE_PATH);
            tasks = storage.loadTasks();
        } catch (IOException | LoadTaskException e) {
            Ui.reply(e.getMessage());
        }

        try {
            Command cmd = Parser.parseCommand(input);
            Task task;

            if (cmd.getClass() == DoneCommand.class) {
                DoneCommand done = (DoneCommand) cmd;
                response = tasks.markAsDone(done.getIndex());
                storage.saveTasks(tasks);
            } else if (cmd.getClass() == DeleteCommand.class) {
                DeleteCommand delete = (DeleteCommand) cmd;
                response = tasks.delete(delete.getIndex());
                storage.saveTasks(tasks);
            } else if (cmd.getClass() == TodoCommand.class) {
                TodoCommand todo = (TodoCommand) cmd;
                task = tasks.addToDo(todo.getDescription());
                response = Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                storage.saveTasks(tasks);
            } else if (cmd.getClass() == DeadlineCommand.class) {
                DeadlineCommand deadline = (DeadlineCommand) cmd;
                task = tasks.addDeadline(deadline.getDescription(), deadline.getDate());
                response = Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                storage.saveTasks(tasks);
            } else if (cmd.getClass() == EventCommand.class) {
                EventCommand event = (EventCommand) cmd;
                task = tasks.addEvent(event.getDescription(), event.getDate());
                response = Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                storage.saveTasks(tasks);
            } else if (cmd.getClass() == FindCommand.class) {
                FindCommand find = (FindCommand) cmd;
                tasks.find(find.getKeyword());
            } else if (cmd.getClass() == ListCommand.class) {
                response = tasks.list();
            } else if (cmd.getClass() == ExitCommand.class) {
                response = Ui.reply(EXIT_MESSAGE);
            }
        } catch (AbyssException | IOException | DateTimeParseException e) {
            response = Ui.reply(e.getMessage());
        }
        return response;
    }

    public static int getNumberOfTasks() {
        return tasks.getNumberOfTasks();
    }
}
