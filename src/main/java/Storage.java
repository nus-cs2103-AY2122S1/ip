import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                String[] splitLine = line.split(" [|] ");

                // Read each element of the line
                String taskType = splitLine[0];
                boolean isDone = splitLine[1].equals("1");
                String description = splitLine[2];

                // Store data from file into tasks arraylist
                Task t;
                switch (taskType) {
                case "T":
                    t = new Todo(description, isDone);
                    tasks.add(t);
                    break;
                case "D":
                    t = new Deadline(description, isDone, splitLine[3]);
                    tasks.add(t);
                    break;
                case "E":
                    t = new Event(description, isDone, splitLine[3]);
                    tasks.add(t);
                    break;
                default:
                    break;
                }
            }

            return tasks;
        } catch (FileNotFoundException e) {
            // Create the file
            File f = new File(filePath);
            try {
                f.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.toStringForFile());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void append(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
