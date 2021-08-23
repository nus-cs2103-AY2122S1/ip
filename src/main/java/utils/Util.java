package utils;

import tasks.Task;
import tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Util {
    public static boolean isLowerCase(String input) {
        return input == input.toLowerCase();
    }

    public static TaskList loadDataBase(String filePath) {
        TaskList taskList = new TaskList();
        File f = new File(filePath); // create a File for the given file path
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                StorageParser storageParser = new StorageParser(s.nextLine());
                Task task = Task.of(storageParser);
                taskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            //Data file doesn't exist
            //Create directory
            Path path = Path.of(filePath);
            File directory = new File(path.getParent().toString());
            if (!directory.exists()) {
                directory.mkdirs();
            }
        } finally {
            return taskList;
        }
    }

    public static void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    //Print confirmation after the task is added
    public static String taskAddConfirmation(Task task, int numTasks) {
        String log = "";
        log += "Got it. I've added this task:\n";
        log += task + "\n";
        log += Util.informNumTask(numTasks);
        return log;
    }

    public static String informNumTask(int numTasks) {
        if (numTasks == 1 || numTasks == 0) {
            return "Now you have " + numTasks + " task in the list.";
        } else {
            return "Now you have " + numTasks + " tasks in the list.";
        }
    }
}
