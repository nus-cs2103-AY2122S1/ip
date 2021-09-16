package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class that interact with the file used to store tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path to duke storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File dukeFile = new File(filePath);
            File directory = dukeFile.getParentFile();
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts all tasks derived from the storage file to TaskList.
     *
     * @return TaskList that includes all tasks in the file.
     */
    public TaskList convertFileToTaskList() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        File dukeFile = new File(filePath);
        try {
            Scanner scan = new Scanner(dukeFile);
            while (scan.hasNext()) {
                String taskString = scan.nextLine();
                Task task = convertTaskStringToTask(taskString);
                taskList.add(task);
            }
        } catch (Exception e) {
            System.out.println("Can't understand data in the duke file. "
                    + "Detail information: "
                    + e.getMessage());
        }
        return taskList;
    }


    /**
     * Converts the string representation of task to task object.
     *
     * @param taskString String representation of task read from file.
     * @return Task object.
     * @throws DukeException Exception that duke bot can throw.
     */
    public static Task convertTaskStringToTask(String taskString) throws Exception {
        String splitSign = "&&";
        String[] newTaskCommands = taskString.split(splitSign);
        String taskType = newTaskCommands[0];
        String status = newTaskCommands[1];
        String taskDescription = newTaskCommands[2];

        Task task;
        String reminderTime;
        switch(taskType) {
        case "T":
            reminderTime = newTaskCommands.length > 3 ? newTaskCommands[3] : "";
            task = reminderTime.length() > 0
                    ? new ToDo(taskDescription, reminderTime)
                    : new ToDo(taskDescription);
            break;
        case "D":
            String deadlineTime = newTaskCommands[3];
            reminderTime = newTaskCommands.length > 4 ? newTaskCommands[4] : "";
            task = reminderTime.length() > 0
                    ? new Deadline(taskDescription, deadlineTime, reminderTime)
                    : new Deadline(taskDescription, deadlineTime);
            break;
        case "E":
            String eventTime = newTaskCommands[3];
            String[] startingEndingTime = eventTime.split("--");
            String from = startingEndingTime[0];
            String to = startingEndingTime[1];

            reminderTime = newTaskCommands.length > 4 ? newTaskCommands[4] : "";
            task = reminderTime.length() > 0
                    ? new Event(taskDescription, from, to, reminderTime)
                    : new Event(taskDescription, from, to);
            break;
        default:
            throw new DukeException("Can't understand the task icon '" + taskType + "'");
        }
        if (status.equals("1")) {
            task.done();
        }
        return task;
    }

    /**
     * Updates the file with the new TaskList.
     *
     * @param taskList New TaskList.
     */
    public void convertTaskListToFile(TaskList taskList) {
        try {
            FileWriter clearFile = new FileWriter(filePath);
            clearFile.write("");
            clearFile.close();
            for (int i = 0; i < taskList.size(); i++) {
                addTaskToFile(taskList.get(i));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * adds new tasks to the file.
     *
     * @param task Task that is going to be added.
     */
    public void addTaskToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            assert (!task.getDescription().contains("&&"));
            String reminderTime = task.hasReminder()
                    ? task.getReminderTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                    : "";
            String output = task.getIcon() + "&&" + task.getStatus() + "&&" + task.getDescription()
                    + "&&" + task.getTaskTime() + "&&" + reminderTime + "\n";
            fileWriter.write(output);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
