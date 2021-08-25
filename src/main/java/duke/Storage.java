package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 * 
 */
public class Storage {

    private String filePath;
    public ArrayList<Task> taskArr;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskArr = new ArrayList<Task>();
    }

    /**
     * 
     * Loads existing save file if it exists.
     * Otherwise, it will create a new file.
     * @return ArrayList of Tasks containing existing tasks from previous saves. 
     */
    public ArrayList<Task> loadFile() {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                convertToTask(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found. Creating new file.");
            createFile(f);
            return new ArrayList<Task>();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Task>();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Task>();
        }
        return this.taskArr;
    }

    /**
     * 
     * Carries tasks from previous save file to current application run.
     * @param line Line of previous save file.
     * @throws ParseException If date format is incorrect.
     * @throws DukeException If line from previous save file was invalid.
     */
    public void convertToTask(String line) throws ParseException, DukeException {
        Task task;
        boolean isDone = false;
        String dateStr;
        DateFormat oldFormat = new SimpleDateFormat("MMM dd yyyy");
        DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String[] bracketSplit = line.split("\\|");
        String taskType = bracketSplit[0].trim();
        String description = bracketSplit[2].trim();
        if (bracketSplit[1].equals("X")) {
            isDone = true;
        }
        switch (taskType) {
        case "D":
            dateStr = bracketSplit[3].trim();
            date = oldFormat.parse(dateStr);
            dateStr = destDf.format(date);
            task = new Deadline(description, isDone, dateStr);
            taskArr.add(task);
            break;
        case "E":
            dateStr = bracketSplit[3].trim();
            date = oldFormat.parse(dateStr);
            dateStr = destDf.format(date);
            task = new Event(description, isDone, dateStr);
            taskArr.add(task);
            break;
        case "T":
            task = new Todo(description, isDone);
            taskArr.add(task);
            break;
        default:
            throw new DukeException("Error adding an existing Task");
        }

    }

    /**
     * Create a new file if there is no existing file.
     * @param f file variable.
     */
    public void createFile(File f) {
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update save file so that it remains up to date upon closure.
     * @param taskArr List of tasks in a TaskList. 
     * @throws IOException If IO operation fails.
     */
    public void writeToFile(TaskList taskArr) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        for (int i = 0; i < taskArr.size(); i++) {
            fw.write(taskArr.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }
}
