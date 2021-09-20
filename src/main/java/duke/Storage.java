package duke;

import duke.tasks.TaskList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the file containing the list of tasks.
 */
public class Storage {
    private static final String text = "./data/duke.txt";
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(text, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Storage() {
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
                str += TaskList.addSpecificTask(task);
                assert TaskList.getSize() > 0;
                System.out.println(TaskList.getLast().toString());
                writer.write(TaskList.getLast().toString() + "\n");
            }
            writer.close();
            FileWriter fWriter = new FileWriter(text, true);
            writer = new BufferedWriter(fWriter);
            return str;
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return str;
    }

    /**
     * Loads the task list from the duke.txt file and returns void.
     * @throws IOException if an error while reading has occurred.
     */
    public static void readFromFile() throws IOException {
        try {
            //create a BufferedReader which loads the data when duke.Duke starts up
            String line;
            FileReader fReader = new FileReader(text);
            BufferedReader reader = new BufferedReader(fReader);

            //reading data
            while ((line = reader.readLine()) != null) {
                String[] curr = new String[4];
                curr[0] = line.substring(1, 2);
                curr[1] = line.substring(4, 5);
                curr[2] = line.substring(7);
                switch (curr[1]) {
                case "T":
                    TaskList.addSpecificTask("todo " + curr[2]);
                case "E":
                    TaskList.addSpecificTask("event " + curr[2]);
                case "D":
                    TaskList.addSpecificTask("deadline " + curr[2]);
                default: break;
                }
                if (curr[1].equals("X")) {
                    TaskList.getLast().markAsDone();
                }
                reader.close();
            }
        } catch (IOException e) {
            FileWriter fWriter = new FileWriter(text, true);
            writer = new BufferedWriter(fWriter);
        }
    }

    /**
     * Rewrites the duke.txt file with the new list of tasks.
     */
    public static void overwrite() throws IOException {
        FileWriter fWriter = new FileWriter(text, false);
        writer = new BufferedWriter(fWriter);
        writer.write(TaskList.overwrite());
        writer.close();
        FileWriter newWriter = new FileWriter(text, true);
        writer = new BufferedWriter(newWriter);
    }
}
