import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskManager {
    private Scanner commandInput;
    private ArrayList<Task> tasks;

    public TaskManager(Scanner commandInput) {
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
                } else if (Pattern.compile("(t|T)(o|O)(d|D)(o|O).*").matcher(command).matches()) {
                    this.addTodo(command);
                } else if (Pattern.compile("(d|D)(e|E)(a|A)(d|D)(l|L)(i|I)(n|N)(e|E)\\s.+( /by ).+").matcher(command).matches()) {
                    this.addDeadline(command);
                } else if (Pattern.compile("(e|E)(v|V)(e|E)(n|N)(t|T)\\s.+( /at ).+").matcher(command).matches()) {
                    this.addEvent(command);
                } else {
                    this.commandFail();
                }
            } catch (DukeException ex) {
                Response.drawLine();
                System.out.println(ex.getMessage());
                Response.drawLine();
            }
        }
        commandInput.close();
    }

    private void addTodo(String description) throws DukeException{
        String test = description.substring(4);
        if (Pattern.compile("\\s.+").matcher(test).matches()) {
            Todo newTodo = new Todo(description.substring(5));
            tasks.add(newTodo);
            this.added();
        } else {
            throw new DukeException("There appears to be a typo in your Todo command.\nPlease try again.");
        }
    }

    private void addDeadline(String description) {
        String[] deadlineArr = description.substring(9).split(" /by ", 2);
        Deadline newDeadline = new Deadline(deadlineArr[0], deadlineArr[1]);
        tasks.add(newDeadline);
        this.added();
    }

    private void addEvent(String description) {
        String[] eventArr = description.substring(6).split(" /at ", 2);
        Event newEvent = new Event(eventArr[0], eventArr[1]);
        tasks.add(newEvent);
        this.added();
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
        Response.drawLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i).toString());
        }
        Response.drawLine();
    }

    private void commandFail() throws DukeException {
        throw new DukeException("I didn't get that. Please try again.");
    }
}
