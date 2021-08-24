import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                } else if (Pattern.compile("done\\s+\\d+\\s*").matcher(command.toLowerCase()).matches()) {
                    this.markTaskDone(command.substring(4).trim());
                } else if (Pattern.compile("delete\\s+\\d+\\s*").matcher(command.toLowerCase()).matches()) {
                    this.deleteTask(command.substring(6).trim());
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
        if (Pattern.compile("(?i)(deadline ).*\\S+.*( /by )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}").matcher(description).matches()) {
            String[] strArr = description.substring(9).split(" /by ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HHmm");
            LocalDateTime deadlineDT = LocalDateTime.MIN;
            try {
                deadlineDT = LocalDateTime.parse(strArr[1], formatter);
            } catch (DateTimeException ex) {
                throw new DukeException(ex.getMessage() + "\n"
                        + "Please try again.");
            }
            Deadline newDeadline = new Deadline(strArr[0].trim(), deadlineDT);
            tasks.add(newDeadline);
            this.added();
        } else {
            throw new DukeException("There appears to be a typo in your DEADLINE command.\n" 
                    + "The command should be of the form:\n"
                    + "  deadline 'description' /by 'yyyy mm dd hhmm'\n"
                    + "Please try again.");
        }
    }

    private void addEvent(String description) {
        if (Pattern.compile("(?i)(event ).*\\S+.*( /from )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}"
                        + "( to )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}").matcher(description).matches()) {
            String[] strArr = description.substring(6).split(" /from ", 2);
            String[] dateTimeArr = strArr[1].split(" to ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HHmm");
            LocalDateTime eventDT1 = LocalDateTime.MIN;
            LocalDateTime eventDT2 = LocalDateTime.MIN;
            try {
                eventDT1 = LocalDateTime.parse(dateTimeArr[0], formatter);
                eventDT2 = LocalDateTime.parse(dateTimeArr[1], formatter);
            } catch (DateTimeException ex) {
                throw new DukeException(ex.getMessage() + "\n"
                        + "Please try again.");
            }
            
            Event newEvent = new Event(strArr[0].trim(), eventDT1, eventDT2);
            tasks.add(newEvent);
            this.added();
        } else {
            throw new DukeException("There appears to be a typo in your EVENT command.\n"
                    + "The command should be of the form:\n"
                    + "  event 'description' /from 'yyyy mm dd hhmm' to 'yyyy mm dd hhmm'\n"
                    + "Please try again.");
        }
    }

    private void added() {
        Response.respond("Got it. I've added this task: \n"
        + "  " + tasks.get(tasks.size() - 1).toString() + "\n"
        + this.numOfTasks());
    }

    private void markTaskDone(String index) {
        int indexInt = Integer.parseInt(index) - 1;
        if (indexInt < tasks.size() && indexInt > -1) {
            Task currTask = tasks.get(indexInt);
            currTask.markDone();
            Response.respond("Nice! I've marked this task as done:\n"
                    + "  " + currTask.toString());
        } else {
            Response.respond("That task doesn't exist.\nPlease Try again.");
        }
    }

    private void deleteTask(String index) {
        int indexInt = Integer.parseInt(index) - 1;
        if (indexInt < tasks.size() && indexInt > -1) {
            Task currTask = tasks.get(indexInt);
            tasks.remove(indexInt);
            Response.respond("Noted. I've removed this task:\n"
                    + "  " + currTask.toString() + "\n"
                    + this.numOfTasks());
            
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
            Response.respond(this.numOfTasks());
        }
    }

    private void commandFail() {
        throw new DukeException("I didn't get that. Please try again.");
    }
    
    private String numOfTasks() {
        if (tasks.size() > 0) {
            return "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            return "There are no tasks in your list.";
        }
        
    }
}
