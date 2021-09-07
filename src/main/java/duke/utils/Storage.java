package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Storage {

    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Returns ArrayList of Tasks loaded from filePath location
     *
     * @return the ArrayList
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner dataReader = new Scanner(this.file);

            while (dataReader.hasNextLine()) {
                String[] data = dataReader.nextLine().split("\\|");
                String type = data[0];
                boolean isDone = data[1].equals("1");
                String description = data[2];

                switch (type) {
                case "T":
                    tasks.add(new ToDo(description, isDone));
                    break;
                case "D":
                    LocalDate time = LocalDate.parse(data[3].trim());
                    tasks.add(new Deadline(description, time, isDone));
                    break;
                case "E":
                    LocalDate startTime = LocalDate.parse(data[3].trim());
                    LocalDate endTime = LocalDate.parse(data[3].trim());
                    tasks.add(new Event(description, startTime, endTime, isDone));
                    break;
                }

            }

            dataReader.close();
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                return tasks;
            }
        }
        return tasks;
    }

    /**
     * Writes formatted data into filePath location
     */
    public void save(String formattedTaskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(formattedTaskList);
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
