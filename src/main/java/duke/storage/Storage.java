package duke.storage;

import duke.tasks.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Represents the file containing the list of tasks.
 */
public class Storage {
    private static BufferedWriter writer;
    private static String dataStoragePath;

    static {
        try {
            writer = new BufferedWriter(new FileWriter("data/duke.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for a Storage object.
     * @param dataStoragePath path
     */
    public Storage(String dataStoragePath) {
        // https://stackoverflow.com/questions/26239151/
        File file = new File("data/duke.txt");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (FileNotFoundException e) {
            System.out.println("No such file found. Creating the file now!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Storage.dataStoragePath = dataStoragePath;
    }

    /**
     * Loads the task list from the duke.txt file and returns void.
     * @throws IOException if an error while reading has occurred.
     */
    public void readFromFile() throws IOException {
        try {
            //create a BufferedReader which loads the data when duke.Duke starts up
            String line;
            FileReader fReader = new FileReader(dataStoragePath);
            BufferedReader reader = new BufferedReader(fReader);

            //reading data
            while ((line = reader.readLine()) != null) {
                String[] curr = new String[4];
                curr[0] = line.substring(1, 2);
                curr[1] = line.substring(4, 5);
                curr[2] = line.substring(7);
                if (line.contains("[T]")) {
                    TaskList.addSpecificTask("todo " + curr[2]);
                } else if (line.contains("[E]")) {
                    TaskList.addSpecificTask("event " + curr[2]);
                } else if (line.contains("[D]")) {
                    TaskList.addSpecificTask("deadline " + curr[2]);
                }
                if (line.contains("[X]")) {
                    TaskList.getLast().markAsDone();
                }
            }
            reader.close();
        } catch (IOException e) {
            FileWriter fWriter = new FileWriter("data/duke.txt", true);
            writer = new BufferedWriter(fWriter);
        }
    }

    /**
     * Takes in a task and adds it to the tasklist, while writing it to the file
     * @param task Task ot be added.
     * @throws IOException if an error while reading has occurred.
     */
    public static String writeToFile(String task) throws IOException {
        boolean isDeadline = task.startsWith("deadline") && task.contains("/by ");
        boolean isEvent = task.startsWith("event") && task.contains("/at ");
        boolean isTodo = task.startsWith("todo");

        String str = "";
        try {
            if (isDeadline || isEvent || isTodo) {
                writer = new BufferedWriter(new FileWriter(dataStoragePath, true));
                str += TaskList.addSpecificTask(task);
                assert TaskList.getSize() > 0;
                System.out.println(TaskList.getLast().toString());
                writer.write(TaskList.getLast().toString() + "\n");
                writer.close();
            }
            FileWriter fWriter = new FileWriter("data/duke.txt", true);
            writer = new BufferedWriter(fWriter);
            return str;
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return str;
    }

    /**
     * Rewrites the duke.txt file with the new list of tasks.
     */
    public static void overwrite() throws IOException {
        FileWriter fWriter = new FileWriter("data/duke.txt", false);
        writer = new BufferedWriter(fWriter);
        writer.write(TaskList.stringList());
        writer.close();
        FileWriter newWriter = new FileWriter("data/duke.txt", true);
        writer = new BufferedWriter(newWriter);
    }
}
