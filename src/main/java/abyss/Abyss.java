package abyss;

import abyss.command.*;
import abyss.exception.AbyssException;
import abyss.exception.LoadTaskException;
import abyss.task.Task;
import abyss.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Abyss is an application which keeps track of tasks, allowing users to
 * add and delete tasks of multiple types, including deadlines, events and to-dos.
 * The Abyss can also list existing tasks and mark tasks as done.
 */
public class Abyss {
    private static TaskList tasks = new TaskList();
    private static Storage storage;
    private static final String FILE_PATH = "./data/abyss.txt";
    private static final String[] START_MESSAGES = {"Hello beautiful. Welcome to the Abyss.",
            "What can we do for you today?"};
    private static final String EXIT_MESSAGE = "Exiting the Abyss. We look forward to your return.";

    /**
     * Main function that handles the flow of the application.
     *
     * @param args Input arguments.
     */
    public static void main(String[] args) {
        Ui.printLogo();
        Ui.reply(START_MESSAGES);

        try {
            storage = new Storage(FILE_PATH);
            tasks = storage.loadTasks();
        } catch (IOException | LoadTaskException e) {
            Ui.reply(e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine().trim();
                Command cmd = Parser.parseCommand(input);
                Task task;

                if (cmd.getClass() == DoneCommand.class) {
                    DoneCommand done = (DoneCommand) cmd;
                    tasks.markAsDone(done.getIndex());
                    storage.saveTasks(tasks);
                } else if (cmd.getClass() == DeleteCommand.class) {
                    DeleteCommand delete = (DeleteCommand) cmd;
                    tasks.delete(delete.getIndex());
                    storage.saveTasks(tasks);
                } else if (cmd.getClass() == TodoCommand.class) {
                    TodoCommand todo = (TodoCommand) cmd;
                    task = tasks.addToDo(todo.getDescription());
                    Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                    storage.saveTasks(tasks);
                } else if (cmd.getClass() == DeadlineCommand.class) {
                    DeadlineCommand deadline = (DeadlineCommand) cmd;
                    task = tasks.addDeadline(deadline.getDescription(), deadline.getDate());
                    Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                    storage.saveTasks(tasks);
                } else if (cmd.getClass() == EventCommand.class) {
                    EventCommand event = (EventCommand) cmd;
                    task = tasks.addEvent(event.getDescription(), event.getDate());
                    Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                    storage.saveTasks(tasks);
                } else if (cmd.getClass() == FindCommand.class) {
                    FindCommand find = (FindCommand) cmd;
                    tasks.find(find.getKeyword());
                }  else if (cmd.getClass() == ListCommand.class) {
                    tasks.list();
                } else if (cmd.getClass() == ExitCommand.class) {
                    Ui.reply(EXIT_MESSAGE);
                    sc.close();
                    return;
                }
            } catch (AbyssException | IOException | DateTimeParseException e) {
                Ui.reply(e.getMessage());
            }
        }
    }

    public static int getNumberOfTasks() {
        return tasks.getNumberOfTasks();
    }


}
