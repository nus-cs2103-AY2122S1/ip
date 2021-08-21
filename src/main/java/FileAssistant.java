import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileAssistant {
    private static final String DIRECTORY_PATH = "../../../data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    public static ArrayList<String> retrieveData() {
        try {
            File f = new File(FILE_PATH);
            Scanner sc = new Scanner(f);
            ArrayList<String> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                taskList.add(sc.nextLine());
            }

            return taskList;

        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static void writeToFile(String allTasks) {
        // Checks if directory exists, creates if it doesn't
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Write to file
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(allTasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }
}