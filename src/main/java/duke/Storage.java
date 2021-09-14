package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the reading and writing of file
 * which Duke accesses for the task list.
 * Keeps the file path of the task list.
 */

public class Storage {
    private String filePath;

    /**
     * A public constructor to create Storage.
     * @param filePath The string for the file name.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads and reads the content of the file to
     * return an Array List of tasks saved in the file.
     * @return An ArrayList of tasks.
     * @throws IOException Thrown when the file is not found.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();

        File f = new File(this.filePath); // create a File for the given file path
        if (!f.exists()) {
            System.out.println("Creating new file...");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Creating new file...1");
            }

        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String readLine = s.nextLine();
            if (readLine.contains("[T]")) {
                Todo task = new Todo(readLine.substring(7));
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                arr.add(task);
            } else if (readLine.contains("[D]")) {
                String[] spl = readLine.split("/");

                LocalDate date = LocalDate.parse(spl[1].substring(3, 13));
                LocalTime time = LocalTime.parse(spl[1].substring(14));
                Deadline task = new Deadline(spl[0].substring(7), date, time);
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                arr.add(task);
            } else {
                String[] spl = readLine.split("/");

                LocalDate startDate = LocalDate.parse(spl[1].substring(3 , 13));
                LocalTime startTime = LocalTime.parse(spl[1].substring(14, 19));
                LocalDate endDate = LocalDate.parse(spl[1].substring(20 , 30));
                LocalTime endTime = LocalTime.parse(spl[1].substring(31));

                Event task = new Event(spl[0].substring(7), startDate, startTime, endDate, endTime);
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                arr.add(task);
            }
        }
        return arr;
    }

    /**
     * Rewrites the content of the file with the content passed in to the method.
     * @param arr The arraylist of tasks.
     * @throws IOException Thrown when the file cannot be found.
     */
    public void writeToFile(ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task str: arr) {
            fw.write(str.toStore() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns the filePath string of the storage.
     * @return the filePath string of the storage.
     */
    public String getPath() {
        return this.filePath;
    }


}
