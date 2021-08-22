package abyss;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Abyss {
    private static TaskList tasks = new TaskList();
    private static final String FILE_PATH = "./data/abyss.txt";
    private static final String[] START_MESSAGES = {"Hello beautiful. Welcome to the Abyss.",
            "What can we do for you today?"};
    private static final String EXIT_MESSAGE = "Exiting the Abyss. We look forward to your return.";

    public static void main(String[] args) {
        Ui.printLogo();
        Ui.reply(START_MESSAGES);

        Storage storage;
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
                switch (cmd.getType()) {
                case DONE:
                    DoneCommand done = (DoneCommand) cmd;
                    tasks.markAsDone(done.getIndex());
                    storage.saveTasks(tasks);
                    break;
                case DELETE:
                    DeleteCommand delete = (DeleteCommand) cmd;
                    tasks.delete(delete.getIndex());
                    storage.saveTasks(tasks);
                    break;
                case TODO:
                    TodoCommand todo = (TodoCommand) cmd;
                    task = tasks.addToDo(todo.getDescription());
                    Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                    storage.saveTasks(tasks);
                    break;
                case DEADLINE:
                    DeadlineCommand deadline = (DeadlineCommand) cmd;
                    task = tasks.addDeadline(deadline.getDescription(), deadline.getDate());
                    Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                    storage.saveTasks(tasks);
                    break;
                case EVENT:
                    EventCommand event = (EventCommand) cmd;
                    task = tasks.addEvent(event.getDescription(), event.getDate());
                    Ui.replyTaskAdded(task, tasks.getNumberOfTasks());
                    storage.saveTasks(tasks);
                    break;
                case LIST:
                    tasks.list();
                    break;
                case EXIT:
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
