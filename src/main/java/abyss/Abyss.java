package abyss;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Abyss {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String TODO_REGEX = "^todo[ ]+\\S[ -~]*$";
    private static final String DEADLINE_REGEX = "^deadline[ ]+\\S[ -~]*\\/by[ ]+\\S[ -~]*$";
    private static final String EVENT_REGEX = "^event[ ]+\\S[ -~]*\\/at[ ]+\\S[ -~]*$";
    private static final String FILE_PATH = "./data/abyss.txt";

    public static void main(String[] args) {
        printLogo();
        reply("Hello beautiful. Welcome to the Abyss.", "What can we do for you today?");


        Storage storage;
        try {
            storage = new Storage(FILE_PATH);
            tasks = storage.loadTasks();
        } catch (IOException | LoadTaskException e) {
            reply(e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equalsIgnoreCase("exit")) {
            try {
                if (cmd.equalsIgnoreCase("list")) {
                    list();
                } else if (cmd.matches("^done.*")) {
                    if (!cmd.matches("^done[ ]+\\d*$")) {
                        throw new InvalidCommandException("Command 'done' should be followed by " +
                                "the index of the task piece.");
                    }
                    if (tasks.isEmpty()) {
                        throw new InvalidCommandException("The Abyss is empty");
                    }

                    String index = cmd.split("done[ ]+", 2)[1];
                    int i = Integer.parseInt(index);
                    if (i < 1 || i > getNumberOfTasks()) {
                        throw new InvalidCommandException("Index should be within 1 and " + getNumberOfTasks());
                    }
                    markAsDone(i);
                    storage.saveTasks(tasks);
                } else if (cmd.matches("^delete.*")) {
                    if (!cmd.matches("^delete[ ]+\\d*$")) {
                        throw new InvalidCommandException("Command 'delete' should be followed by " +
                                "the index of the task piece.");
                    }
                    if (tasks.isEmpty()) {
                        throw new InvalidCommandException("The Abyss is empty");
                    }

                    String index = cmd.split("delete[ ]+", 2)[1];
                    int i = Integer.parseInt(index);
                    if (i < 1 || i > getNumberOfTasks()) {
                        throw new InvalidCommandException("Index should be within 1 and " + getNumberOfTasks());
                    }
                    deleteTask(i);
                    storage.saveTasks(tasks);
                } else if (cmd.matches("^todo.*")) {
                    if (!cmd.matches(TODO_REGEX)) {
                        throw new InvalidTodoException("Description of a 'todo' task piece cannot be empty.");
                    }

                    String description = cmd.split("todo[ ]+", 2)[1];
                    addToDo(description);
                    storage.saveTasks(tasks);
                } else if (cmd.matches("^deadline.*")) {
                    if (!cmd.matches(DEADLINE_REGEX)) {
                        throw new InvalidDeadlineException("Description and date of a 'deadline' task piece " +
                                "cannot be empty.");
                    }

                    String content = cmd.split("deadline[ ]+", 2)[1];
                    String[] parts = content.split("\\/by[ ]+", 2);
                    addDeadline(parts[0], parts[1]);
                    storage.saveTasks(tasks);
                } else if (cmd.matches("^event.*")) {
                    if (!cmd.matches(EVENT_REGEX)) {
                        throw new InvalidEventException("Description and date of an 'event' task piece " +
                                "cannot be empty.");
                    }

                    String content = cmd.split("event[ ]+", 2)[1];
                    String[] parts = content.split("\\/at[ ]+", 2);
                    addEvent(parts[0], parts[1]);
                    storage.saveTasks(tasks);
                } else {
                    throw new InvalidCommandException("The Abyss does not understand your command.");
                }
            } catch (AbyssException | IOException e) {
                reply(e.getMessage());
            }
            cmd = sc.nextLine();
        }
        sc.close();

        reply("Exiting the Abyss. We look forward to your return.");
    }

    private static void reply(String... messages) {
        System.out.println(formatReply(messages));
    }

    private static void list() {
        System.out.println(formatListReply());
    }

    private static void markAsDone(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        String markedTask = "  " + task.toString();
        reply("Task piece is lit up in the Abyss.", markedTask);
    }

    private static void addToDo(String description) {
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    private static void addDeadline(String description, String by) {
        LocalDate date = LocalDate.parse(by);
        Task newTask = new Deadline(description, date);
        addTask(newTask);
    }

    private static void addEvent(String description, String at) {
        LocalDate date = LocalDate.parse(at);
        Task newTask = new Event(description, date);
        addTask(newTask);
    }

    private static void addTask(Task task) {
        tasks.add(task);
        String addedMsg = "Task piece is added to the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + getNumberOfTasks() + " task piece(s).";
        reply(addedMsg, task.toString(), tasksLeftMsg);
    }

    private static void deleteTask(int i) {
        String removedMsg = "Task piece is swallowed by the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + (getNumberOfTasks() - 1) + " task piece(s).";
        String task = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        reply(removedMsg, task, tasksLeftMsg);
    }

    private static int getNumberOfTasks() {
        return tasks.size();
    }

    private static String formatReply(String[] messages) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < messages.length; i++) {
            reply += "\t " + messages[i] + "\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    private static String formatListReply() {
        String reply = "\t......................................................\n";
        for (int i = 0; i < getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            reply += "\t " + (i + 1) + "." + task.toString() + "\n";
        }
        if (tasks.isEmpty()) {
            reply += "\t The Abyss is empty.\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    private static void printLogo() {
        String logo =
                "  ('-.    .-. .-')                .-')     .-')   \n"
                        + "  ( OO ).-.\\  ( OO )              ( OO ).  ( OO ). \n"
                        + "  / . --. / ;-----.\\  ,--.   ,--.(_)---\\_)(_)---\\_)\n"
                        + "  | \\-.  \\  | .-.  |   \\  `.'  / /    _ | /    _ | \n"
                        + ".-'-'  |  | | '-' /_).-')     /  \\  :` `. \\  :` `. \n"
                        + " \\| |_.'  | | .-. `.(OO  \\   /    '..`''.) '..`''.)\n"
                        + "  |  .-.  | | |  \\  ||   /  /\\_  .-._)   \\.-._)   \\\n"
                        + "  |  | |  | | '--'  /`-./  /.__) \\       /\\       /\n"
                        + "  `--' `--' `------'   `--'       `-----'  `-----' \n";
        System.out.println(logo);
    }
}
