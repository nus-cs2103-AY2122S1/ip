import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final static String DIR_PATH = "data";
    private final static String FILE_NAME = "tasks.txt";

    public static Scanner loadTasks() {
        File file = new File(DIR_PATH + "/" + FILE_NAME);
        try {
            return new Scanner(file);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void saveTasks(String tasks) {
        try {
            File base_dir = new File(DIR_PATH);
            if (!base_dir.exists()) {
                base_dir.mkdirs();
            }
            FileWriter fw = new FileWriter(DIR_PATH + "/" + FILE_NAME);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
