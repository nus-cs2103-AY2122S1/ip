package abyss;

import java.util.Scanner;

public class Abyss {
    private static Task[] tasks = new Task[100];
    private static int count = 0;
    private static final String TODO_REGEX = "^todo[ ]+\\S[ -~]*$";
    private static final String DEADLINE_REGEX ="^deadline[ ]+\\S[ -~]*\\/by[ ]+\\S[ -~]*$";
    private static final String EVENT_REGEX = "^event[ ]+\\S[ -~]*\\/at[ ]+\\S[ -~]*$";

    public static void main(String[] args) {
        printLogo();
        reply("Hello beautiful. Welcome to the Abyss.", "What can we do for you today?");

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equalsIgnoreCase("exit")) {
            try {
                if (cmd.equalsIgnoreCase("list")) {
                    list();
                } else if (cmd.matches("^done.*")) {
                    if (!cmd.matches("^done \\d*$")) {
                        throw new InvalidInputException("Command 'done' should be followed by " +
                                "the index of the task piece.");
                    }
                    
                    String index = cmd.substring(5);
                    int i = Integer.parseInt(index);
                    if (i < 1 || i > count) {
                        throw new InvalidInputException("Index should be within 1 to " + count);
                    }
                    markAsDone(i);
                } else if (cmd.matches("^todo.*")) {
                    if (!cmd.matches(TODO_REGEX)) {
                        throw new InvalidInputException("Description of a 'todo' task piece cannot be empty.");
                    }
                    String description = cmd.split("todo[ ]+", 2)[1];
                    addToDo(description);
                } else if (cmd.matches("^deadline.*")) {
                    if (!cmd.matches(DEADLINE_REGEX)) {
                        throw new InvalidInputException("Description and date of a 'deadline' task piece " +
                                "cannot be empty.");
                    }
                    String content = cmd.split("deadline[ ]+", 2)[1];
                    String[] parts = content.split("\\/by[ ]+", 2);
                    addDeadline(parts[0], parts[1]);
                } else if (cmd.matches("^event.*")) {
                    if (!cmd.matches(EVENT_REGEX)) {
                        throw new InvalidInputException("Description and date of an 'event' task piece " +
                                "cannot be empty.");
                    }
                    String content = cmd.split("event[ ]+", 2)[1];
                    String[] parts = content.split("\\/at[ ]+", 2);
                    addEvent(parts[0], parts[1]);
                } else {
                    throw new InvalidInputException("The Abyss does not understand your command.");
                }
            } catch (InvalidInputException e) {
                reply(e.getMessage());
            }
            cmd = sc.nextLine();
        }
        reply("Exiting the Abyss. We look forward to your return.");
    }

    private static void reply(String... messages) {
        System.out.println(formatReply(messages));
    }

    private static void list() {
        System.out.println(formatListReply(tasks));
    }

    private static void markAsDone(int i) {
        Task task = tasks[i - 1];
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
        tasks[count] = task;
        count++;
        String addedMsg = "Task piece is added to the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + count + " task piece(s).";
        reply(addedMsg, task.toString(), tasksLeftMsg);
    }

    private static String formatReply(String[] messages) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < messages.length; i++) {
            reply += "\t " + messages[i] + "\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    private static String formatListReply(Task[] tasks) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            if (task == null) {
                break;
            }
            reply += "\t " + (i + 1) + "." + task.toString() + "\n";
        }
        if (tasks[0] == null) {
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
