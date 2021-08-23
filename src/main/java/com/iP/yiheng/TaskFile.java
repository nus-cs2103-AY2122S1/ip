package com.iP.yiheng;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFile {

    private File file;
    private final static String filePath = "data/tasks.txt";

    public TaskFile() {
        file = new File(filePath);
    }

    protected void printTaskFile()  {
        try {
            Scanner sc = new Scanner(file);
            boolean isEmpty = !sc.hasNext();
            int index = 1;

            while (sc.hasNext()) {
                System.out.println(index + ". " + sc.nextLine());
                index++;
            }

            if (isEmpty) System.out.println("Your list is empty!");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please add an item to automatically create your tasks.txt!" +
                    " If no file is created,\nyou need to create a data folder in your root directory before " +
                    "proceeding.");
        }
    }

    protected void saveTask(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task.toString() + "\n");
        fileWriter.close();
    }

    protected void overwriteList(ArrayList<Task> taskArrayList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : taskArrayList) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    protected void loadFile() {
        try {
            Scanner sc = new Scanner(file);
            Task task = new Task();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                char taskChar = line.charAt(1);
                char taskStatus = line.charAt(4);
                String description = line.substring(7);
                if (taskChar == 'T') {
                    task.addExisting(taskChar, taskStatus, description, null);
                } else {
                    int lastIndex = line.length() - 2;
                    int startIndex = 0;
                    StringBuilder stringBuilder = new StringBuilder();
                    if (line.charAt(lastIndex + 1) == ')') {
                        for (int i = lastIndex; i > 0; i--) {
                            if (line.charAt(i) == '(') {
                                startIndex = i - 7; // Offset characteristics
                                break;
                            }
                            stringBuilder.append(line.charAt(i));
                        }
                        stringBuilder.reverse();
                        task.addExisting(taskChar, taskStatus, description.substring(0, startIndex), stringBuilder.toString().trim());
                    } else {
                        for (int i = lastIndex + 1; i > 0; i--) {
                            if (line.charAt(i) == '(') {
                                startIndex = i - 7;
                                break;
                            }
                            stringBuilder.append(line.charAt(i));
                        }
                        stringBuilder.reverse();
                        task.addExisting(taskChar, taskStatus, description.substring(0, startIndex), stringBuilder.toString().trim());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please add an item to automatically create your tasks.txt!" +
                    " If no file is created,\nyou need to create a data folder in your root directory before " +
                    "proceeding.");
        }
    }

}
