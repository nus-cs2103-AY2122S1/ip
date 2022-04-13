package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;

/**
 * Represents a Parser that receives inputs from the user and executes the correct commands.
 */
public class Parser {

    private TaskList tasks;
    private String userInput;
    private int index;
    private Duke duke;
    private Ui response;

    /**
     * Constructor for the Parser object
     *
     * @param tasks
     * @param userInput
     * @param index
     */
    public Parser(TaskList tasks, String userInput, int index, Duke duke) {
        this.tasks = tasks;
        this.userInput = userInput;
        this.index = index;
        this.duke = duke;
        this.response = new Ui();
    }

    /**
     * Executes when Duke receives a bye command
     * Sends a goodbye message to the user
     */
    public String bye_execute() {
        System.out.println(response.byeResponse());
        this.writeToFile(duke.getFilePath());
        Platform.exit();
        return response.byeResponse();
    }

    /**
     * Executes when Duke receives a list command
     * Outputs the list of current tasks to the user
     */
    public String list_execute() {
        int i = 0;
        String body = "";
        while (i < tasks.getNumberOfTasks()) {
            body += "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            i++;
        }
        String message = response.listResponse(tasks, body);
        this.writeToFile(duke.getFilePath());
        System.out.println(message);
        return message;
    }

    /**
     * Executes when Duke receives a done command
     * Marks the relevant task as done
     */
    public String done_execute() {
        String userIndex = userInput.substring(5);
        int i = Integer.valueOf(userIndex);
        tasks.get(i - 1).markAsDone();
        String message = response.doneResponse(i, tasks);
        this.writeToFile(duke.getFilePath());
        System.out.println(message);
        return message;
    }

    /**
     * Executes when Duke receives a toDo command
     * Adds a todo task to the list of tasks
     */
    public String todo_execute() {
        try {
            Task newTask = new ToDo(userInput.substring(5));
            tasks.addTask(newTask);
            duke.setIndex(duke.getIndex() + 1);
            String message = response.addTaskResponse(duke.getIndex(), newTask);
            this.writeToFile(duke.getFilePath());
            System.out.println(message);
            return message;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(response.errorMessage("todo"));
            return response.errorMessage("todo");
        }
    }

    /**
     * Executes when Duke receives an event command
     * Adds an event task to the list of tasks
     */
    public String event_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(6 , i - 1);
            String[] split = userInput.split("at");
            String date = split[1].substring(1);
            LocalDate d = LocalDate.parse(date);
            String formattedTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task newTask = new Event(description, formattedTime);
            tasks.addTask(newTask);
            duke.setIndex(duke.getIndex() + 1);
            String message = response.addTaskResponse(duke.getIndex(), newTask);
            this.writeToFile(duke.getFilePath());
            System.out.println(message);
            return message;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(response.errorMessage("event"));
            return response.errorMessage("event");
        }
    }

    /**
     * Executes when Duke receives a deadline command
     * Adds a deadline task to the list of tasks
     */
    public String deadline_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(9 , i - 1);
            String[] split = userInput.split("by");
            String date = split[1].substring(1);
            LocalDate d = LocalDate.parse(date);
            String formattedTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task newTask = new Deadlines(description, formattedTime);
            tasks.addTask(newTask);
            duke.setIndex(duke.getIndex() + 1);
            String message = response.addTaskResponse(duke.getIndex(), newTask);
            this.writeToFile(duke.getFilePath());
            System.out.println(message);
            return message;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(response.errorMessage("deadline"));
            return response.errorMessage("deadline");
        }
    }

    /**
     * Executes when Duke receives a delete command
     * Deletes the relevant task from the list of tasks
     */
    public String delete_execute() {
        String userIndex = userInput.substring(7);
        int i = Integer.valueOf(userIndex);
        Task task = tasks.get(i - 1);
        if (task == null) {
            System.out.println("no task found!");
            return "no task found!";
        } else {
            duke.setIndex(duke.getIndex() - 1);
            String message = response.deleteTaskResponse(duke.getIndex(), task);
            System.out.println(message);
            tasks.removeTask(i - 1);
            this.writeToFile(duke.getFilePath());
            return message;
        }
    }

    /**
     * Executes when Duke receives a find command
     * Finds tasks in the list that contain the keyword specified by the user
     */
    public String find_execute() {
        String keyword = userInput.split(" ")[1];
        String taskString = "";
        boolean hasTask = false;
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            if (description.contains(keyword)) {
                taskString += tasks.get(i).toString() + "\n";
                hasTask = true;
            }
        }
        String message = response.findResponse(taskString, hasTask);
        System.out.println(message);
        return message;
    }

    /**
     * Executes when Duke receives a StatTask command
     * Calls the Statistic#numOfTasks method
     */
    public String statTaskExecute() {
        Statistic stat = new Statistic(duke, tasks);
        return stat.numberOfTasks();
    }

    /**
     * Executes when Duke receives a StatDone command
     * Calls the Statistic#numberOfDoneTasks() method
     */
    public String statDoneExecute() {
        Statistic stat = new Statistic(duke, tasks);
        return stat.numberOfDoneTasks();
    }

    /**
     * Executes when Duke receives a statNotDoneExecute()
     * Calls the Statistic#numberOfNotDoneTasks() method
     */
    public String statNotDoneExecute() {
        Statistic stat = new Statistic(duke, tasks);
        return stat.numberOfNotDoneTasks();
    }

    /**
     * Generates a formatted string of tasks to be outputted to the user and to be saved into hard disk.
     * @return Formatted string of tasks
     */
    public String generateTasks() {
        String taskString = "";
        int i = 0;
        while (i < tasks.getNumberOfTasks()) {
            taskString += tasks.get(i).toString() + "\n";
            i++;
        }
        return taskString;
    }

    /**
     * asd
     * @param filePath
     */
    public void writeToFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(this.generateTasks());
            fw.close();
        } catch (IOException e) {
            System.out.println("unable to write to file :<");
        }
    }
}
