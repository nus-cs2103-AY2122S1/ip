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
    private final List<Task> tasks;
    private final Utility utility;

    /**
     * Constructor for TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
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

        try {
            if (taskType.equals("todo")) {
                taskToAdd = new ToDo(userInput.substring(5));
            } else {
                taskToAdd = addDatedTask(userInput, taskType, taskIndex);
            }
            return saveTask(taskToAdd, dataPath);

        } catch (IllegalArgumentException | IOException | StringIndexOutOfBoundsException | DateTimeParseException e) {
            return ("Error : Invalid instruction\ntaskType task /by or /at yyyy-MM-dd HHmm");
        }
    }

    /**
     * Add an evemt od deadline where there is a date tagged to the task.
     *
     * @param userInput Task to add.
     * @param taskType Type of task being added.
     * @param taskIndex Position in the userInput string where the task begins.
     * @return Task to be added.
     */
    public Task addDatedTask(String userInput, String taskType, int taskIndex) {
        Task taskToAdd;
        int dateIndex = taskType.equals("event") ? userInput.indexOf("/at") : userInput.indexOf("/by");
        String[] dateAndTask = utility.seperateDateFromTask(dateIndex, taskIndex, userInput);
        String task = dateAndTask[0];
        String dateString = dateAndTask[1];
        LocalDateTime dateTime = utility.stringToDate(dateString);

        if (taskType.equals("deadline")) {
            taskToAdd = new Deadline(task, dateTime);
        } else if (taskType.equals("event")) {
            taskToAdd = new Event(task, dateTime);
        } else {
            throw new IllegalArgumentException("Error : Please specify type of task");
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

        return (String.format("Got it. I've added this task:\n"
                + "%s\nNumber of tasks: %s", task.toString(), tasks.size()));
    }

    /**
     * Deletes tasks from the array of tasks.
     *
     * @param userInput String of task to delete.
     */
    public String deleteTask(String userInput) {
        try {
            int commandLen = 7;
            String taskIndexes = userInput.substring(commandLen);
            String[] tasksString = taskIndexes.split(" ");
            int len = tasksString.length;
            List<Task> tasksToDel = new ArrayList<>(len);

            String returnMessage = "Noted. I've removed these tasks:\n";

            for (int i = 0; i < len; i++) {
                int taskToDel = Integer.parseInt(tasksString[i]) - 1;
                assert taskToDel >= 0;
                Task task = this.tasks.get(taskToDel);
                tasksToDel.add(task);
            }

            for (int i = 0; i < len; i++) {
                returnMessage += tasksToDel.get(i).toString() + "\n";
                this.tasks.remove(tasksToDel.get(i));
            }

            return String.format("%s\nYou now have %s tasks left", returnMessage, this.tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            return ("Error : You cannot delete nothing!");
        } catch (NumberFormatException e) {
            return ("Error : Must be a number bodoh");
        } catch (IndexOutOfBoundsException e) {
            return ("Error : Number doesnt exist");
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param userInput Text beginning with 'done' followed by a number.
     */
    public String markTaskDone(String userInput) {
        try {
            int taskIndex = 5;
            String tasksToMark = userInput.substring(taskIndex);
            String[] arrayOfTasks = tasksToMark.split(" ");

            String returnMessage = "Noted. I've marked these tasks:\n";

            for (int i = 0; i < arrayOfTasks.length; i++) {
                int taskToMark = Integer.parseInt(arrayOfTasks[i]) - 1;
                assert taskToMark >= 0;
                Task task = this.tasks.get(taskToMark);
                task.setDone();
                returnMessage += task + "\n";
            }

            return returnMessage;

        } catch (StringIndexOutOfBoundsException e) {
            return ("Error : You cannot mark nothing as done!");
        } catch (NumberFormatException e) {
            return ("Error : Must be a number bodoh");
        } catch (IndexOutOfBoundsException e) {
            return ("Error : Number doesnt exist");
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

    /**
     * Finds task form stored tasks.
     *
     * @param tasks Current list of tasks.
     * @param userInput Search keyWord.
     * @return Tasks related to keyWord.
     */
    public String findTask(List<Task> tasks, String userInput) {
        String keyWord = userInput.substring(5);
        List<Task> tempList = new ArrayList<>();

        tasks.forEach(task -> {
            if (task.toString().toLowerCase(Locale.ROOT).contains(keyWord.toLowerCase())) {
                tempList.add(task);
            }
        });
        return showTasks(tempList);
    }
}
