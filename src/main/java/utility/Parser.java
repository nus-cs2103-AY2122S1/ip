package utility;

import error.DukeException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.List;

public class Parser {
    protected byte flag = 1;
    private String input;
    private String[] words;
    private StringBuilder log;

    public void takeInput(String input) {
        this.input = input;
    }

    public void interactWith(Ui ui, Logger logger, TaskList list) {
        log = new StringBuilder();
        words = this.input.trim().split(" ", 2);
        List<Task> tasks = list.showTasks();
        try {
            checkInput(words);
            switch (words[0]) {
            case "bye":
                logger.write(tasks);
                flag = 0;
                log.append("\n\t Peace out!");
                break;
            case "list":
                log.append("\n\t Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    log.append("\n\t ").append(i + 1).append(". ").append(tasks.get(i));
                }
                break;
            case "done":
                int idx = Integer.parseInt(words[1]);
                Task task = tasks.get(idx - 1);
                if (task.getStatusIcon().equals("X")) {
                    log.append("\t This task has been marked as done");
                } else {
                    task.markAsDone();
                    log.append("\n\t Nice! I've marked this task as done:\n\t\t").append(task);
                }
                break;
            case "todo":
                ToDo newTask = new ToDo(words[1]);
                tasks.add(newTask);
                log.append("\n\t Got it. I've added this task:\n\t\t").append(newTask).append("\n\t Now you have ")
                        .append(tasks.size()).append(" tasks in the list.");
                break;
            case "deadline":
                String[] details = words[1].split(" /by ", 2);
                Deadline deadline = new Deadline(details[0], details[1]);
                tasks.add(deadline);
                log.append("\n\t Got it. I've added this task:\n\t\t").append(deadline)
                        .append("\n\t Now you have ").append(tasks.size()).append(" tasks in the list.");
                break;
            case "event":
                details = words[1].split(" /at ", 2);
                Event event = new Event(details[0], details[1]);
                tasks.add(event);
                log.append("\n\t Got it. I've added this task:\n\t\t").append(event)
                        .append("\n\t Now you have ").append(tasks.size()).append(" tasks in the list.");
                break;
            case "delete":
                idx = Integer.parseInt(words[1]);
                task = tasks.get(idx - 1);
                tasks.remove(idx - 1);
                log.append("\n\t Noted. I've removed this task:\n\t\t").append(task)
                        .append("\n\t Now you have ").append(tasks.size()).append(" tasks in the list.");
                break;
            default:
                throw new DukeException(
                        "\n\t ☹ My dictionary does not contain this sophisticated language.\n\t Maybe someday :)");
            }
        } catch (DukeException e) {
            log.append(e.getMessage());
        }
        ui.showToUser(log.toString());
    }

    /**
     * Handles DukeExceptions for the chatbot
     * @param log The separate words in a given line
     */
    private static void checkInput(String[] log) throws DukeException{
        if (log.length == 1) {
            switch (log[0]) {
            case "todo":
                throw new DukeException("\n\t ☹ Oh dearie!. The description of a todo cannot be empty!");
            case "deadline":
                throw new DukeException("\n\t ☹ Oh lord!. I need some description and a time limit!");
            case "event":
                throw new DukeException("\n\t ☹ By the heavens!. I need some description and a timing!");
            }
        } else if (log.length == 2) {
            switch (log[0]) {
            case "deadline":
                if (log[1].split(" /by ", 2).length != 2) {
                    throw new DukeException("\n\t ☹ Blimey! Did you forget to type \"/by\" or a time limit?");
                }
                break;
            case "event":
                if (log[1].split(" /at ", 2).length != 2) {
                    throw new DukeException("\n\t ☹ Wait! Did you forget to type \"/at\" or a timing?");
                }
            }
        }
    }
}
