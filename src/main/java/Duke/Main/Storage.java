package Duke.Main;

import Duke.Task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {

    public static String processInstructions() {
        StringBuilder builder = new StringBuilder();
        try {
            File file = new File("taskFile/instructions.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                builder.append(sc.nextLine()).append("\n");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void saveData(File file, TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getTaskList()) {
                writer.write(task.save() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(File file, TaskList taskList) {
        try {
            FileReader reader = new FileReader(file);
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                char type = (taskString.length() < 1) ? ' ' : taskString.trim().charAt(1);
                char status = (taskString.length() < 4) ? '?' : taskString.trim().charAt(4);
                String[] taskArray = taskString.split("] ", 2);
                String taskDescription = (taskArray.length < 2) ? "" : taskArray[1];
                Task curr;
                switch (type) {
                case 'T':
                    taskDescription = taskDescription.trim();
                    curr = new Todo(taskDescription);
                    if (status == 'X') {
                        curr.markAsCompleted();
                    }
                    taskList.addTask(curr);
                    break;
                case 'D':
                    taskDescription = taskDescription.trim().
                            replaceAll("\\(", "/").
                            replaceAll("\\)", "");
                    curr = new Deadline(taskDescription);
                    if (status == 'X') {
                        curr.markAsCompleted();
                    }
                    taskList.addTask(curr);
                    break;
                case 'E':
                    taskDescription = taskDescription.
                            replaceAll("\\(", "/").
                            replaceAll("\\)", "");
                    curr = new Event(taskDescription);
                    if (status == 'X') {
                        curr.markAsCompleted();
                    }
                    taskList.addTask(curr);
                    break;
                default:
                    break;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
