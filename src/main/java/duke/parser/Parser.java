package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>Parser</code> class contains main bot logic to parse user commands
 * Has a ui attribute to print out specific messages according to user command.
 * parseCommand(input,list) is the sole method of the class.
 */

public class Parser{

    Ui ui;
    public Parser(Ui ui){
        this.ui = ui;
    }

    private void sayBye(){
        ui.showMessage("Bye! *Poof!*");
        System.exit(0);
    }

    private void help(){
        ui.showHelp();
    }

    private void list(TaskList tasks){
        ui.showMessage("Tasks so far:");
        tasks.printAllTasks();
    }

    private void markTaskAsDone(TaskList tasks){
        while (true) {
            if (tasks.getSize() == 0) {
                ui.showError("noTasks");
                return;
            }
            try {
                int index = Integer.parseInt(ui.readInput("Can do! Which task should I set as done?\n"
                        + "From task number 1 to " + tasks.getSize()));
                tasks.markDone(index-1);
                ui.showMessage("Ok I've marked the above task as done");
                break;
            } catch (Exception e) {
                System.out.println(e);
                ui.showError("invalidIndex");
            }
        }
    }

    private void deleteTask(TaskList tasks){
        if (tasks.getSize() == 0) {
            ui.showError("noTasks");
            return;
        }
        while (true) {
            try {
                int index = Integer.parseInt(ui.readInput("Can Do! Which task should I delete?\n "
                        + "From task number 1 to " + tasks.getSize()));
                System.out.println(tasks.getTask(index-1));
                ui.showMessage("Ok, boom this task is gone!");
                tasks.delete(index-1);
                ui.showMessage("There are now " + tasks.getSize() + " tasks");
                break;
            } catch (Exception e){
                ui.showError("invalidIndex");
            }
        }
    }

    private void makeToDo(TaskList tasks){
        ui.showMessage("Ok! A to-do task");

        while (true) {
            String description;
            description = ui.readInput("What is the description of the task?");
            if (description.equals("")) {
                ui.showError("emptyInput");
            } else {
                Task t = new ToDo(description, false);
                ui.taskCreationSuccess(t);
                tasks.add(t);
                ui.showMessage("There are now " + tasks.getSize() + " tasks");
                break;
            }
        }
    }

    private void makeDeadline(TaskList tasks){
        ui.showMessage("Ok! A Deadline task");
        String description;
        LocalDate date;
        LocalTime time;

        while (true) {
            description = ui.readInput("What is the description of the task?");
            if (description.equals("")) {
                ui.showError("emptyInput");
            } else{
                break;
            }
        }

        while (true) {
            String input = ui.readInput("Enter the deadline for the task\n"
                    + "Enter date/time in this exact format: yyyy-mm-dd hh:mm");
            try {
                date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(input.substring(0,10)));
                time = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(input.substring(11)));
                break;
            } catch (Exception e) {
                ui.showError("invalidDate");
            }

        }

        Task t = new Deadline(description, date, time, false);
        ui.taskCreationSuccess(t);
        tasks.add(t);
        ui.showMessage("There are now " + tasks.getSize() + " tasks");
    }

    public void makeEvent(TaskList tasks){
        ui.showMessage("Ok! An Event task");
        String description;
        LocalDate date;
        LocalTime startTime, endTime;

        while (true) {
            description = ui.readInput("What is the description of the task?");
            if (description.equals("")) {
                ui.showError("emptyInput");
            } else {
                break;
            }
        }

        while (true) {
            String input = ui.readInput("Enter the event time range for the task\n"
                    + "Enter date/time in this exact format: yyyy-mm-dd hh:mm hh:mm");
            try {
                date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(input.substring(0,10)));
                startTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(input.substring(11,16)));
                endTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(input.substring(17)));
                break;
            } catch (Exception e) {
                ui.showError("invalidDate");
            }

        }

        Task t = new Event(description, date, startTime, endTime, false);
        ui.taskCreationSuccess(t);
        tasks.add(t);
        ui.showMessage("There are now " + tasks.getSize() + " tasks");
    }

    public void findTask(TaskList tasks){
        if (tasks.getSize() == 0) {
            ui.showMessage("Oops! There are no tasks to search for.");
            return;
        }

        ui.showMessage("Can Do! Please give me a string and "
                + "I'll return all tasks with descriptions containing that string!");

        String target;

        while (true) {
            target = ui.readInput("What should I search for?");
            if (target.equals("")){
                ui.showError("emptyInput");
                continue;
            }
            break;
        }

        ui.showMessage("Ok! Here are the tasks that match your search!");
        for (int i = 0; i < tasks.getSize(); i++){
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(target)){
                System.out.println(task);
            }
        }
    }

    /**
     * Takes a user input command string and processes it
     * Updates the referenced input TaskList where applicable
     * Prints invalidInput if input is not a recognised command
     *
     * @param input input String which corresponds to a user command
     * @param tasks ArrayList of classes to be updated
     */

    public void parseCommand(String input, TaskList tasks) {
        switch (input) {
            case "bye":
                sayBye();
                //fallthrough
            case "help":
                help();
                break;
            case "list":
                list(tasks);
                break;
            case "done":
                markTaskAsDone(tasks);
                break;
            case "delete":
                deleteTask(tasks);
                break;
            case "todo":
                makeToDo(tasks);
                break;
            case "deadline":
                makeDeadline(tasks);
                break;
            case "event":
                makeEvent(tasks);
                break;
            case "find":
                findTask(tasks);
                break;
            default:
                ui.showError("invalidCommand");
                break;
        }
    }
}
