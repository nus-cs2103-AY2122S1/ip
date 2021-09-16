package bob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;
import bob.exception.InvalidDateException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Represents an object that deals with loading tasks from a specified file and saving tasks in the file.
 */
public class Storage {
    /** Pathname to directory where the data will be stored */
    private final String path;

    /**
     * Constructor for a new Storage instance.
     *
     * @param path Pathname to the data directory that will be used to store the list of tasks.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the data from the bob.txt file to instantiate the TaskList for the current session.
     *
     * @return ArrayList containing tasks to be added to the TaskList for this current session.
     * @throws DirectoryNotFoundException If the data directory does not exist yet.
     * @throws FileNotFoundException If the bob.txt file does not exist yet.
     */
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
                boolean isComplete;
                assert (curr.matches("\\[T](.*)") || curr.matches("\\[D](.*)")
                        || curr.matches("\\[E](.*)"));
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
                } else if (curr.matches("\\[E](.*)")) {
                    String[] splitCurr = curr.split(" \\Q[\\E.\\Q]\\E ", 2);
                    String[] splitRight = splitCurr[1].split(" \\(at: ", 2);
                    String timing = splitRight[1].substring(0, splitRight[1].length() - 1);
                    newTask = new Event(splitRight[0], formatDate(timing));
                    isComplete = curr.contains("[X]");
                } else { // Should never reach this branch.
                    newTask = new Task("");
                    isComplete = false;
                }
                tasks.add(newTask);
                if (isComplete) {
                    newTask.markCompleted();
                }
            }
        } catch (IOException | InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Formats the date from the printed format to the format required to make a LocalDate object.
     * (e.g. Dec 1 2021 to 2021-12-01)
     *
     * @param date Date of Event or Deadline task in the printed format.
     * @return Date in the String format required to make a LocalDate object.
     */
    private String formatDate(String date) {
        String day;
        String month;
        String year;
        String[] splitDate = date.split(" ", 3);

        assert (splitDate[1].length() == 1 || splitDate[1].length() == 2);
        day = splitDate[1].length() == 1 ? "0" + splitDate[1] : splitDate[1];

        assert (splitDate[0] == "Jan" || splitDate[0] == "Feb" || splitDate[0] == "Mar" || splitDate[0] == "Apr"
                || splitDate[0] == "May" || splitDate[0] == "Jun" || splitDate[0] == "Jul" || splitDate[0] == "Aug"
                || splitDate[0] == "Sep" || splitDate[0] == "Oct" || splitDate[0] == "Nov" || splitDate[0] == "Dec");

        switch (splitDate[0]) {
        case "Jan":
            month = "01";
            break;
        case "Feb":
            month = "02";
            break;
        case "Mar":
            month = "03";
            break;
        case "Apr":
            month = "04";
            break;
        case "May":
            month = "05";
            break;
        case "Jun":
            month = "06";
            break;
        case "Jul":
            month = "07";
            break;
        case "Aug":
            month = "08";
            break;
        case "Sep":
            month = "09";
            break;
        case "Oct":
            month = "10";
            break;
        case "Nov":
            month = "11";
            break;
        case "Dec":
            month = "12";
            break;
        default:
            month = "00"; // Should never reach this branch.
        }

        assert (splitDate[2].length() == 4);
        year = splitDate[2];

        // Return year, month and day Strings in the required format.
        return year + "-" + month + "-" + day;
    }

    /**
     * Makes the data directory that will later contain the bob.txt file if it does not yet exist.
     */
    public void makeDataDirectory() {
        File dataDirectory = new File(this.path);
        dataDirectory.mkdir();
    }

    /**
     * Makes the bob.txt file in the data directory if it does not yet exist.
     */
    public void makeBobFile() {
        try {
            File dataDirectory = new File(this.path);
            new File(dataDirectory, "bob.txt").createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates bob.txt with the newest list of tasks provided.
     *
     * @param taskList New list of tasks to be saved to the bob.txt file.
     */
    public void updateBobFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.path + "/bob.txt");
            writer.write("");
            writer.close();
            int noOfTasks = Integer.parseInt(taskList.getNoOfTasks());
            for (int i = 0; i < noOfTasks; i++) {
                String currTaskPrinted = taskList.getTask(i).printTask();
                appendToFile(currTaskPrinted);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds text to the existing text file without overriding it.
     *
     * @param textToAppend Text to be added to the existing text file.
     */
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
