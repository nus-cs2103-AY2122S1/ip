package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        String[] input = userInput.split(" ");
        String taskType = input[0].toLowerCase();
        int taskIndex = input[0].length() + 1;

        try{
            if (taskType.equals("todo")) {
                taskToAdd = new ToDo(userInput.substring(5));
            } else {
                taskToAdd = addDatedTask(userInput, taskType, taskIndex);
            }
            return saveTask(taskToAdd, dataPath);

        } catch(IllegalArgumentException | IOException | StringIndexOutOfBoundsException | DateTimeParseException  e) {
            return("<taskType> <task> </by or /at> <yyyy-MM-dd HHmm>");
        }
    }

    public Task addDatedTask(String userInput, String taskType, int taskIndex) {
        Task taskToAdd;
        int dateIndex = taskType.equals("event") ? userInput.indexOf("/at") : userInput.indexOf("/by");
        String[] dateAndTask = utility.seperateDateFromTask(dateIndex,taskIndex, userInput);
        String task = dateAndTask[0];
        String dateString = dateAndTask[1];
        LocalDateTime dateTime = utility.stringToDate(dateString);

        if (taskType.equals("deadline")){
            taskToAdd = new Deadline(task, dateTime);
        } else if (taskType.equals("event")) {
            taskToAdd = new Event(task, dateTime);
        } else {
            throw new IllegalArgumentException("Please specify type of task");
        }
        return taskToAdd;
    }

    /**
     * Saves task to datacfile and arraylist.
     *
     * @param task Task to be added.
     * @param dataPath Path to data file.
     * @return String message to be printed on screen.
     * @throws IOException In case of invalid input.
     */
    public String saveTask(Task task, Path dataPath) throws IOException {
        // Add task to data file
        String dataToStore = task.getDataRep();
        Files.writeString(dataPath, dataToStore + System.lineSeparator(), StandardOpenOption.APPEND);

        // Add task To arrayList
        this.tasks.add(task);

        return(String.format("Got it. I've added this task:\n" +
                "%s\nNumber of tasks: %s", task.toString(), tasks.size()));
    }

    /**
     * Deletes a task from the array of tasks.
     *
     * @param userInput String of task to delete.
     */
    public String deleteTask(String userInput) {
        int taskIndex = 7;
        try {
            //Delete from ArrayList
            int taskToDel = Integer.parseInt(userInput.substring(taskIndex)) - 1;
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
        StringBuilder str = new StringBuilder().append("");
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return str.toString();
    }

    public String findTask(List<Task> tasks, String userInput) {
        String keyWord = userInput.substring(5);
        List<Task> tempList = new ArrayList<>();

        tasks.forEach(task -> {
            if(task.toString().toLowerCase(Locale.ROOT).contains(keyWord.toLowerCase(Locale.ROOT))) {
                tempList.add(task);
            };
        });
        return showTasks(tempList);
    }
}
