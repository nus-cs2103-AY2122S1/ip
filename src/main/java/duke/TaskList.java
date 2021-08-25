package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    Utility utility;


    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        utility = new Utility();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
        utility = new Utility();
    }

    /**
     * Adds a task to the List of tasks and to the data file.
     *
     * @param userInput String of task to add.
     * @param dataPath Path to the data storage file.
     */
    public String addTask(String userInput, Path dataPath) {
        Task taskToAdd;
        String dataToStore;

        try{
            if (userInput.toLowerCase().startsWith("todo")) {
                taskToAdd = new ToDo(userInput.substring(5));
            } else {
                String[] input = userInput.split(" ");

                int taskIndex = input[0].length() + 1;
                int dateIndex = userInput.indexOf("/");
                String[] dateAndTask = utility.seperateDateFromTask(dateIndex,taskIndex, userInput);

                String str = dateAndTask[1];
                LocalDateTime dateTime = utility.stringToDate(str);

                if (userInput.toLowerCase().startsWith("deadline")){
                    taskToAdd = new Deadline(dateAndTask[0], dateTime);
                } else if (userInput.toLowerCase().startsWith("event")) {
                    taskToAdd = new Event(dateAndTask[0], dateTime);
                } else {
                    throw new IllegalArgumentException("Please specify type of task");
                }
            }
            // Add task to data file
            dataToStore = taskToAdd.getDataRep();
            Files.writeString(dataPath, dataToStore + System.lineSeparator(), StandardOpenOption.APPEND);

            // Add task To arrayList
            this.tasks.add(taskToAdd);

            return(String.format("Got it. I've added this task:\n" +
                    "%s\nNumber of tasks: %s", taskToAdd.toString(), tasks.size()));

        } catch (IllegalArgumentException e) {
            return("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch(IOException | StringIndexOutOfBoundsException | DateTimeParseException  e) {
            return("Invalid Input format -> <taskType> <task> </by or /at> <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Deletes a task from the array of tasks.
     *
     * @param userInput String of task to delete.
     */
    public String deleteTask(String userInput) {
        try {
            //Delete from ArrayList
            int taskToDel = Integer.parseInt(userInput.substring(7)) - 1;
            Task task = this.tasks.get(taskToDel);
            this.tasks.remove(taskToDel);
            return(String.format("Noted. I've removed this task:\n%s\nNow you have %s tasks in list"
                    , task, this.tasks.size()));
        } catch (StringIndexOutOfBoundsException e) {
            return("OOPS!!! You cannot delete nothing!");
        } catch (NumberFormatException e) {
            return("OOPS!!! Must be a number bodoh");
        } catch (IndexOutOfBoundsException e) {
            return("OOPS!!! Number doesnt exist");
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param userInput Text beginning with 'done' followed by a number.
     */
    public String markTaskDone(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
            Task task = this.tasks.get(taskIndex);
            if (task.checkStatus()) {
                return("Youve already marked this task as done!");
            } else {
                task.setDone();
                return("Nice! I've marked this task as done:\n" + task.toString());
            }
        } catch (StringIndexOutOfBoundsException e) {
            return("OOPS!!! You cannot mark nothing as done!");
        } catch (NumberFormatException e) {
            return("OOPS!!! Must be a number bodoh");
        } catch (IndexOutOfBoundsException e) {
            return("OOPS!!! NUmber doesnt exist");
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints the list of todos sequentially.
     *
     * @param tasks List of current tasks.
     */
    public String showTasks(List<Task> tasks) {
        StringBuilder str = new StringBuilder().append("\n");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return str.toString();
    }
}
