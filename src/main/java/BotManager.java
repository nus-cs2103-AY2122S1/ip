import tasks.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BotManager {
    private Scanner commandInput;
    private ArrayList<Task> tasks;

    public BotManager(Scanner commandInput) {
        this.commandInput = commandInput;
        this.tasks = new ArrayList<>();
        this.listen();
    }

    private void listen() {
        while (commandInput.hasNextLine()) {
            String command = commandInput.nextLine();
            try {
                if (command.equalsIgnoreCase("bye")) {
                    Response.exitResponse();
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    this.list();
                } else if (Pattern.compile("done\\s\\d+").matcher(command.toLowerCase()).matches()) {
                    this.markTaskDone(command.substring(5));
                } else if (Pattern.compile("(?i)todo.*").matcher(command).matches()) {
                    this.addTodo(command);
                } else if (Pattern.compile("(?i)deadline.*").matcher(command).matches()) {
                    this.addDeadline(command);
                } else if (Pattern.compile("(?i)event.*").matcher(command).matches()) {
                    this.addEvent(command);
                } else {
                    this.commandFail();
                }
            } catch (DukeException ex) {
                Response.respond(ex.getMessage());
            }
        }
        commandInput.close();
    }

    private void addTodo(String description) {
        String[] strArr = Pattern.compile("(?i)todo\\s+").split(description, 2);
        if (strArr.length == 2 && strArr[1].length() > 0) {
            Todo newTodo = new Todo(strArr[1]);
            tasks.add(newTodo);
            this.added();
        } else if (strArr.length == 2 || strArr[0].length() == 4) {
            throw new DukeException("The description of a TODO task cannot be empty.\nPlease try again.");
        } else {
            throw new DukeException("There appears to be a typo in your TODO command.\n"
                    + "The command should be of the form:\n"
                    + "  todo 'description'\n"
                    + "Please try again.");
        }
    }

    private void addDeadline(String description) {
        if (Pattern.compile("(?i)(deadline ).*\\S+.*( /by ).*\\S+.*").matcher(description).matches()) {
            String[] strArr = description.substring(9).split(" /by ", 2);
            Deadline newDeadline = new Deadline(strArr[0].trim(), strArr[1].trim());
            tasks.add(newDeadline);
            this.added();
        } else {
            throw new DukeException("There appears to be a typo in your DEADLINE command.\n" 
                    + "The command should be of the form:\n"
                    + "  deadline 'description' /by 'time'\n"
                    + "Please try again.");
        }
    }

    private void addEvent(String description) {
        if (Pattern.compile("(?i)(event ).*\\S+.*( /at ).*\\S+.*").matcher(description).matches()) {
            String[] strArr = description.substring(6).split(" /at ", 2);
            Event newEvent = new Event(strArr[0].trim(), strArr[1].trim());
            tasks.add(newEvent);
            this.added();
        } else {
            throw new DukeException("There appears to be a typo in your EVENT command.\n"
                    + "The command should be of the form:\n"
                    + "  event 'description' /at 'time'\n"
                    + "Please try again.");
        }
    }

    private void added() {
        Response.respond("Got it. I've added this task: \n"
        + "  " + tasks.get(tasks.size() - 1).toString() + "\n"
        + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private void markTaskDone(String index) {
        int indexInt = Integer.parseInt(index) - 1;
        if (indexInt < tasks.size() && indexInt > -1) {
            Task currTask = tasks.get(indexInt);
            currTask.markDone();
            Response.respond("Nice! I've marked this task as done:\n"
                    + currTask.toString());
        } else {
            Response.respond("That task doesn't exist.\nPlease Try again.");
        }
    }

    private void list() {
        if (tasks.size() > 0) {
            Response.drawLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("  " + (i + 1) + "." + tasks.get(i).toString());
            }
            Response.drawLine();
        } else {
            Response.respond("There are no tasks in your list.");
        }
    }

    private void commandFail() {
        throw new DukeException("I didn't get that. Please try again.");
    }
}
