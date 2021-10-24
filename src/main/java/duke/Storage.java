package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String filePath;
    private ArrayList<Task> taskArr;

    /**
     * Constructor of a Storage object
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskArr = new ArrayList<Task>();
    }

    /**
     * Loads existing save file if it exists.
     * Otherwise, it will create a new file.
     *
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
    
    public Statistics loadStats() {
        File f = new File("./data/stats.txt");
        Statistics statistics;
        try {
            Scanner s = new Scanner(f);
            ArrayList<Integer> statisticsArr = new ArrayList<>();
            while (s.hasNext()) {
                String statisticString = s.nextLine();
                int statisticInt = Integer.parseInt(statisticString);
                statisticsArr.add(statisticInt);
                }
            assert statisticsArr.size() == 3: "Incorrect Statistic Length";
            statistics = convertToStats(statisticsArr);
            return statistics;
            
        } catch (FileNotFoundException e) {
            System.out.println("No saved statistics found. Creating new file.");
            createFile(f);
            statistics = new Statistics();
            return statistics;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            statistics = new Statistics();
            return statistics;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            statistics = new Statistics();
            return statistics;
        }
    }

    public Statistics convertToStats(ArrayList<Integer> statisticArr) throws ParseException {
        int done = statisticArr.get(0);
        int added = statisticArr.get(1);
        int deleted = statisticArr.get(2);
        
        return new Statistics(done, added, deleted);
    }
    
    /**
     * Carries tasks from previous save file to current application run.
     *
     * @param line Line of previous save file.
     * @throws ParseException If date format is incorrect.
     * @throws DukeException  If line from previous save file was invalid.
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
        assert taskType.length() > 0: "Empty Task";
        assert description.length() > 0: "Empty Description";
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
     *
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
     *
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
    
    public void writeStatistics(Statistics statistics) throws IOException {
        FileWriter fw = new FileWriter("./data/stats.txt");
        fw.write(statistics.getTasksDone() + System.lineSeparator());
        fw.write(statistics.getTasksAdded() + System.lineSeparator());
        fw.write(statistics.getTasksDeleted() + System.lineSeparator());
        fw.close();
    }

    /**
     * For testing.
     */
    String returnFirstTask() {
        return taskArr.get(0).toString();
    }
}
