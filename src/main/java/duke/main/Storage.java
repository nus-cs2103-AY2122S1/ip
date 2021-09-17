package duke.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.DucSyntaxErrorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {

    /**
     * Update current task list content to file
     * @param file a file that saved the task entered and date
     * @param taskList task list
     */
    public static void saveData(File file, TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getTaskList()) {
                writer.write(task.save() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot find file specified");
        }
    }

    /**
     * Load and preprocess data to task list from file
     * @param file a file that contains previously entered tasks and dates
     * @param taskList task list
     */
    public static void loadData(File file, TaskList taskList) {
        try {
            FileReader reader = new FileReader(file);
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                char type = (taskString.length() < 1) ? ' ' : taskString.trim().charAt(1);
                char status = (taskString.length() < 4) ? '?' : taskString.trim().charAt(4);
                String[] taskArray = taskString.split("] ", 2);
                String taskDescription = (taskArray.length < 2) ? "" : taskArray[1].trim();
                Task curr = processData(type, taskDescription);
                if (status == 'X') {
                    curr.markAsCompleted();
                }
                taskList.addTask(curr);
            }
        } catch (IOException e) {
            System.out.println("Cannot find file specified");
        }
    }

    private static Task processData(char taskType, String taskDescription) {
        Task processedTask;
        switch (taskType) {
        case 'T':
            processedTask = new Todo(taskDescription);
            break;
        case 'D':
            taskDescription = taskDescription.replaceAll("\\(", "/")
                    .replaceAll("\\)", "");
            processedTask = new Deadline(taskDescription);
            break;
        case 'E':
            taskDescription = taskDescription.replaceAll("\\(", "/")
                    .replaceAll("\\)", "");
            processedTask = new Event(taskDescription);
            break;
        default:
            throw new DucSyntaxErrorException(String.valueOf(taskType));
        }
        return processedTask;
    }
}
