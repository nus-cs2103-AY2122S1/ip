package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Storage {

    private String filePath;
    static TaskList tasklist;
    String FILE_PATH = "Data/DukeData.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasklist = new TaskList();
    }

    /**
     * Gets called when user inputs bye.Saves tasks to hard disk in
     * DukeData.txt file
     *
     * @param tasks TaskList containing tasks
     */
    void saveTasks(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get("Data/"));
            File data_file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(FILE_PATH);//Overwriting entire file
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.toString());
                assert task.toString().length() != 0 : "Empty task being saved";
                writer.write("\n");
            }
            writer.close();
            if (data_file.createNewFile()) {
                System.out.println("File created: " + data_file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No files");
        }
    }

    /**
     * Called when chatbot starts to load up TaskList
     *
     * @param filePath FilePath where data is stored
     */
    static void readFile(String filePath) throws FileNotFoundException {
        try{
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            char taskType = currLine.charAt(1);
            switch (taskType) {
                case 'T':
                    tasklist.addTask(new ToDo(currLine.substring(7)));
                    System.out.println(new ToDo(currLine.substring(7)));
                    break;
                case 'D':
                    int l = currLine.indexOf("(");
                    int n = currLine.indexOf(")");
                    assert l < n : "Issue with bracketing order!";
                    tasklist.addTask(new Deadline(currLine.substring(7, l), currLine.substring(l + 1, n)));
                    System.out.println(new Deadline(currLine.substring(7, l), currLine.substring(l + 1, n)));
                    break;
                case 'E':
                    int i = currLine.indexOf("(");
                    int k = currLine.indexOf(")");
                    tasklist.addTask(new Event(currLine.substring(7, i), currLine.substring(i + 1, k)));
                    System.out.println(new Event(currLine.substring(7, i), currLine.substring(i + 1, k)));
                    break;
                 }
            }
        }
        catch (Exception e){

        }

    }
}
