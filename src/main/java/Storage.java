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

    private static void parseAndCreateTask(String input, ArrayList<Task> taskList) {
        String[] inputArr = input.split(",");
        Task currentTask;
        switch (inputArr[0]) {
        case "T" :
            currentTask = new Todo(inputArr[2]);
            if (inputArr[1].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        case "E" :
            currentTask = new Event(inputArr[3], inputArr[1]);
            if (inputArr[2].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        case "D" :
            currentTask = new Deadline(inputArr[3], inputArr[1]);
            if (inputArr[2].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        default:
            System.out.println("Database has invalid data!");
        }

    }

    public ArrayList<Task> loadData() {
        File dataFile = new File(this.filePath);
        System.out.println("Loading data from database..........");
        ArrayList<Task> taskList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(dataFile);){

            while (fileScanner.hasNext()) {
                parseAndCreateTask(fileScanner.nextLine(), taskList);
            }
            System.out.println("Database loaded!");
        } catch (FileNotFoundException fileException) {
            System.out.println("No database found, creating database");
            try {
                dataFile.createNewFile();
                System.out.println("Database created");
            } catch (IOException ioException) {
                System.out.println("Error creating database");
            }
        }
        return taskList;
    }

    public void saveData(ArrayList<Task> taskList) {
        try (FileWriter fw = new FileWriter(this.filePath)) {

            StringBuilder dataString = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                dataString.append(currentTask.getData() + "\n");
            }
            fw.write(dataString.toString());

        } catch (IOException e) {
            System.out.println("Error saving data!");
            e.printStackTrace();
        }
    }
}
