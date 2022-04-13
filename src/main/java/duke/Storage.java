package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    /**
     * initialises the storage, which creates the necessary files
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            int lastSlash = filePath.lastIndexOf("/");
            File myDirectory = new File(filePath.substring(0, lastSlash) + "/");
            if (!myDirectory.exists()) {
                myDirectory.mkdir();
            }

            File myFile = new File(filePath);
            myFile.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * saves the output param into the file initialised by storage
     * @return ArrayList of Task
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> myList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String myTask = sc.nextLine();
                String taskType = myTask.substring(1, 2);
                String taskDetail = myTask.substring(7);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                if (taskType.equals("T")) {
                    myList.add(new ToDo(taskDetail));
                } else if (taskType.equals("D")) {
                    int index = taskDetail.lastIndexOf("(by: ");
                    LocalDate myDate = LocalDate.parse(taskDetail.substring(index + 5, taskDetail.length() - 1), formatter);
                    myList.add(new Deadline(taskDetail.substring(0, index - 1), myDate));
                } else if (taskType.equals("E")) {
                    int index = taskDetail.lastIndexOf("(at: ");
                    LocalDate myDate = LocalDate.parse(taskDetail.substring(index + 5, taskDetail.length() - 1), formatter);
                    myList.add(new Event(taskDetail.substring(0, index - 1), myDate));
                }
                boolean done = myTask.substring(4, 5).equals("X");
                if (done) {
                    myList.get(myList.size() - 1).markAsDone();
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return myList;
    }

    /**
     * saves the output param into the file initialised by storage
     * @param output
     */
    public void saveTasks(String output) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(output);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
