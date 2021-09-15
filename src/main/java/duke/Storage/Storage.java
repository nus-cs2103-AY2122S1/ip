package duke.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.Task;

public class Storage {

    /**
     * Creates storage file if file does not exist
     */
    public static void createFile() {
        File directory = new File("data/");
        File tasks = new File("data/duke.txt");
        try {
            directory.mkdir();
            if (tasks.createNewFile()) {
                System.out.println(tasks.getName() + " created");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Write to file with processed data.
     * @param tasks Tasks to write to file.
     * @throws IOException
     */
    public static void writeToFile(List<Task> tasks) throws IOException {
        int i = 1;
        String lines = "";
        for (Task task: tasks) {
            lines += task.fileWriteString();
            if (i != tasks.size()) {
                lines += "\n";
            }
            i++;
        }
        FileWriter fileWriter = new FileWriter("data/duke.txt");
        fileWriter.write(lines);
        fileWriter.close();
    }

    /**
     * Read file content raw.
     * @return List of strings.
     */
    public static List<String> readFile() {
        File file = new File("data/duke.txt");
        List<String> tasks = new ArrayList();
        try {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                String task = readFile.nextLine();
                tasks.add(task);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
        return tasks;
    }

}
