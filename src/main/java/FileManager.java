import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileManager {

    private static final String FILE_PATH = "data/gnosis.csv";
    private static File file = new File(FILE_PATH);

    public static void readFile() {
        String delimiter = ",";

        try {
            List<Task> tasks = Files.newBufferedReader(Paths.get(FILE_PATH))
                    .lines()
                    .skip(1)
                    .map(s -> {
                String[] tokens = s.split(",");
                Task t = new Task(tokens[1], TaskType.TODO);
                t.setDone(Boolean.getBoolean(tokens[0]));
                return t;

            }).collect(Collectors.toList());

            for (Task t: tasks) {
                System.out.println(t);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeFile() {
        //Data
        //T | 1 | read book
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Study for CS2103"));
        tasks.add(new Event("cs2103 lecture", "2-4pm"));
        tasks.add(new Deadline("cs2103 iP due", "2359 Thursday"));

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));

            writer.write("is Task Completed,Task Name");
            writer.newLine();

            for (Task record: tasks) {
                String oneLine = "false" + "," + record;
                writer.write(oneLine);
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static boolean CreateDataFolder() throws IOException {
        // create folder and file only if file doesnt exist
        if (!file.exists() && file.getParentFile().mkdir()) {
            System.out.println("File and data folder created");
            return file.createNewFile();
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            FileManager.CreateDataFolder();

            //writeFile();

            readFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
