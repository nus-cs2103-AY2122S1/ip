package abyss;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Abyss {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String TODO_REGEX = "^todo[ ]+\\S[ -~]*$";
    private static final String DEADLINE_REGEX = "^deadline[ ]+\\S[ -~]*\\/by[ ]+\\S[ -~]*$";
    private static final String EVENT_REGEX = "^event[ ]+\\S[ -~]*\\/at[ ]+\\S[ -~]*$";
    private static final String FILE_PATH = "./data/abyss.txt";

    public static void main(String[] args) {
        printLogo();
        reply("Hello beautiful. Welcome to the Abyss.", "What can we do for you today?");

        File file = new File(FILE_PATH);
        if (!file.isFile()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                reply(e.getMessage());
            }
        }

        try {
            loadTasks();
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
                    saveTasks();
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
                    saveTasks();
                } else if (cmd.matches("^todo.*")) {
                    if (!cmd.matches(TODO_REGEX)) {
                        throw new InvalidTodoException("Description of a 'todo' task piece cannot be empty.");
                    }

                    String description = cmd.split("todo[ ]+", 2)[1];
                    addToDo(description);
                    saveTasks();
                } else if (cmd.matches("^deadline.*")) {
                    if (!cmd.matches(DEADLINE_REGEX)) {
                        throw new InvalidDeadlineException("Description and date of a 'deadline' task piece " +
                                "cannot be empty.");
                    }

                    String content = cmd.split("deadline[ ]+", 2)[1];
                    String[] parts = content.split("\\/by[ ]+", 2);
                    addDeadline(parts[0], parts[1]);
                    saveTasks();
                } else if (cmd.matches("^event.*")) {
                    if (!cmd.matches(EVENT_REGEX)) {
                        throw new InvalidEventException("Description and date of an 'event' task piece " +
                                "cannot be empty.");
                    }

                    String content = cmd.split("event[ ]+", 2)[1];
                    String[] parts = content.split("\\/at[ ]+", 2);
                    addEvent(parts[0], parts[1]);
                    saveTasks();
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
        Task newTask = new Deadline(description, by);
        addTask(newTask);
    }

    private static void addEvent(String description, String at) {
        Task newTask = new Event(description, at);
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

    private static void loadTasks() throws IOException, LoadTaskException {
        FileReader fileReader = new FileReader(FILE_PATH);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] entry = line.split(" \\| ", 4);
            String taskType = entry[0];
            String isDone = entry[1];
            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(entry[2]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = new Deadline(entry[2], entry[3]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = new Event(entry[2], entry[3]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            default:
                throw new LoadTaskException("Invalid task in file.");
            }
            tasks.add(task);
        }
        reader.close();
    }

    private static void saveTasks() throws IOException {
        StringBuffer input = new StringBuffer();
        for (int i = 0; i < getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            input.append(task.toFileEntry());
            input.append("\n");
        }
        FileWriter writer = new FileWriter(FILE_PATH);
        writer.write(input.toString());
        writer.close();
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
