import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // process the line
                String[] output = line.split("\\s\\|\\s");
                switch (output[0]) {
                    case "T":
                        Task newTodo = new Todo(output[2], output[1] == "1");
                        taskList.add(newTodo);
                        break;
                    case "D":
                        Task newDeadline = new Deadline(output[2], output[3], output[1] == "1");
                        taskList.add(newDeadline);
                        break;
                    case "E":
                        Task newEvent = new Event(output[2], output[3], output[1] == "1");
                        taskList.add(newEvent);
                        break;
                    default:
                        System.out.println("Detected invalid task type. Please check...");
                        break;
                }
            }
        } catch (IOException e1) {
            System.out.println("Something went wrong: " + e1.getMessage());
        } catch (InvalidDateFormat e2) {
            System.out.println(e2.getMessage());
        }
        return taskList;
    }
}