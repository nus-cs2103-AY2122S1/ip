package bob;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    /** Pathname to directory where the data will be stored */
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws DirectoryNotFoundException, FileNotFoundException {
        File dataDirectory = new File(this.path);

        // Check for data directory
        boolean directoryExists = dataDirectory.exists();
        if (!directoryExists) {
            throw new DirectoryNotFoundException();
        }

        // Check for bob.txt
        File bobFile = new File(this.path + "/bob.txt");
        boolean fileExists = bobFile.exists();
        if (!fileExists) {
            throw new FileNotFoundException();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(bobFile);
            while (s.hasNext()) {
                String curr = s.nextLine();
                Task newTask;
                boolean isComplete = false;
                if (curr.matches("\\[T](.*)")) {
                    String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                    newTask = new Todo(splitCurr[1]);
                    isComplete = curr.contains("[X]");
                } else if (curr.matches("\\[D](.*)")) {
                    String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                    String[] splitRight = splitCurr[1].split(" \\(by: ", 2);
                    String deadline = splitRight[1].substring(0, splitRight[1].length() - 1);
                    newTask = new Deadline(splitRight[0], formatDate(deadline));
                    isComplete = curr.contains("[X]");
                } else {
                    String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                    String[] splitRight = splitCurr[1].split(" \\(at: ", 2);
                    String timing = splitRight[1].substring(0, splitRight[1].length() - 1);
                    newTask = new Event(splitRight[0], formatDate(timing));
                    isComplete = curr.contains("[X]");
                }
                tasks.add(newTask);
                if (isComplete) {
                    newTask.markCompleted();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    private String formatDate(String date) {
        String day = "";
        String month = "";
        String year = "";
        String[] splitDate = date.split(" ", 3);

        day = splitDate[1].length() == 1 ? "0" + splitDate[1] : splitDate[1];
        switch (splitDate[0]) {
        case "Jan":
            month = "01";
        case "Feb":
            month = "02";
        case "Mar":
            month = "03";
        case "Apr":
            month = "04";
        case "May":
            month = "05";
        case "Jun":
            month = "06";
        case "Jul":
            month = "07";
        case "Aug":
            month = "08";
        case "Sep":
            month = "09";
        case "Oct":
            month = "10";
        case "Nov":
            month = "11";
        default:
            month = "12";
        }
        year = splitDate[2];

        // Return year, month and day Strings in the required format.
        return year + "-" + month + "-" + day;
    }

    public void makeDataDirectory() {
        File dataDirectory = new File(this.path);
        dataDirectory.mkdir();
    }

    public void makeBobFile() {
        try {
            File dataDirectory = new File(this.path);
            new File(dataDirectory, "bob.txt").createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBobFile(TaskList taskList){
        try {
            FileWriter writer = new FileWriter(this.path + "/bob.txt");
            writer.write("");
            writer.close();
            for (int i = 0; i < Integer.parseInt(taskList.getNoOfTasks()); i++) {
                appendToFile(taskList.getTask(i).printTask());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(this.path + "/bob.txt", true); //FileWriter in append mode
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
