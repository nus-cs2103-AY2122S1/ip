package utils;

import tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

public class Util {
    public static boolean isLowerCase(String input) {
        return input == input.toLowerCase();
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
