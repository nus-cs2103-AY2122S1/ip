import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws InvalidDateFormat{
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
            throw new InvalidDateFormat();
        }
        return taskList;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void write(ArrayList<Task> taskList) {

        String output = "";
        String separator = " | ";

        for (Task t : taskList) {
            output = output + t.getType() + separator + ((t.checkDone()) ? 1 : 0) + separator +
                    t.getDescription() + separator + t.getDeadline() + "\n";
        }
        try {
            writeToFile(this.filePath, output);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}