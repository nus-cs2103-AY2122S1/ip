import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_PATH = "../../../data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    private boolean isDirectoryExists;
    private boolean isFileExists;

    public Storage() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);

        this.isDirectoryExists = directory.exists();
        this.isFileExists = file.exists();
    }

    public ArrayList<String> retrieveData() {
        if (isFileExists) {
            try {
                File f = new File(FILE_PATH);
                Scanner sc = new Scanner(f);
                ArrayList<String> taskList = new ArrayList<>();

                while (sc.hasNextLine()) {
                    taskList.add(sc.nextLine());
                }

                return taskList;

            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong:  " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public void writeToFile(String allTasks) {
        // Checks if directory exists, creates if it doesn't
        if (!isDirectoryExists) {
            File directory = new File(DIRECTORY_PATH);
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