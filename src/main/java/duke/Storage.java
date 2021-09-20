package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    /**
     * Loads List of tasks from the file in [project_root]/data/duke.txt
     * @return List of Tasks to be processed by the TaskList class.
     */
    public List<Task> getTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            File taskFile = new File("../../../data", "duke.txt");
            Scanner myReader = new Scanner(taskFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] args = data.split(Pattern.quote("|"));
                boolean isDone = args[1].trim().equals("1");
                switch (args[0].trim()) {
                case "T":
                    tasks.add(new ToDo(args[2], isDone));
                    break;
                case "E":
                    tasks.add(new Event(args[2], isDone, args[3]));
                    break;
                case "D":
                    tasks.add(new Deadline(args[2], isDone, args[3]));
                    break;
                default:
                    myReader.close();
                    throw new IllegalArgumentException(
                            "COULDN'T PARSE YOUR STUPID FILE. FORMAT THE INPUT PROPERLY.");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // Initialise file and directory if they do not exist
            saveToFile(tasks);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("FORMAT YOUR FILE DATETIMES PROPERLY YOU FOOL.");
        } catch (Exception e) {
            System.out.println("COULDN'T GET YOUR MISERABLE FILE. TRY AGAIN.");
        }
        return tasks;
    }

    /**
     * Saves List of tasks from the file in [project_root]/data/duke.txt
     * @param tasks List of Tasks to be saved.
     */
    public void saveToFile(List<Task> tasks) {
        try {
            // create directory if it does not exist
            File directory = new File("../../../data");
            directory.mkdirs();

            File myFile = new File("../../../data", "duke.txt");
            FileWriter dukeWriter = new FileWriter(myFile);
            for (Task task : tasks) {
                dukeWriter.write(String.format("%s\n", task.getFileString()));
            }
            dukeWriter.close();
        } catch (IOException e) {
            System.out.println("SAVING THE FILE FAILED YOU IDIOT. JUST GIVE UP.");
        }

    }
}
