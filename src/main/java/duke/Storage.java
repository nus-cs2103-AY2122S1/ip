package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads tasks to files and saves tasks to files.
 */
public class Storage {
    String filePath;

    public Storage(String filePath) throws IOException {
        try {
            new File(filePath).createNewFile();
            this.filePath = filePath;
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> result = new ArrayList<>(100);
        int counter = 0;

        try {
            Scanner dataScanner = new Scanner(new File(this.filePath));

            while(dataScanner.hasNext()) {
                convertToTask(dataScanner.nextLine(), result, counter);
                counter++;
            }
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return result;
    }

    public void addTodo(Todo todo) {
        try {
            String textToSave = "T/~/0/~/" + todo.description + "\n";

            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public void addDeadline(Deadline deadline) {
        try {
            String textToSave = "D/~/0/~/" + deadline.savedFormat() + "\n";

            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public void addEvent(Event event) {
        try {
            String textToSave = "E/~/0/~/" + event.savedFormat() + "\n";

            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public void markAsDone(TaskList taskList, int taskToMark) {
        try {
            Task t = taskList.getTasks().get(taskToMark - 1);
            String taskType = t.taskType() == 0 ? "T" : (t.taskType() == 1 ? "D" : "E");
            String done = t.isDone ? "1" : "0";
            String description = t.savedFormat();
            String textToSave = taskType + "/~/" + done + "/~/" + description;

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path
                    .of("src/data/duke.txt")));
            fileContent.set(taskToMark - 1, textToSave);

            Files.write(Path.of("src/data/duke.txt"), fileContent);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public void delete(int taskToDelete) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path
                    .of("src/data/duke.txt")));
            fileContent.remove(taskToDelete - 1);

            Files.write(Path.of("src/data/duke.txt"), fileContent);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    private static void convertToTask(String s, ArrayList<Task> savedInputs, int counter) {
        String[] savedTasks = s.split("/~/");

        String description = savedTasks[2];
        if (savedTasks.length == 3) {
            savedInputs.add(new Todo(description));
        } else if (savedTasks[0].equals("D")) {
            String by = savedTasks[3];
            LocalDate date = LocalDate.parse(by);
            savedInputs.add(new Deadline(description, date));
        } else {
            String at = savedTasks[3];
            LocalDate date = LocalDate.parse(at);
            savedInputs.add(new Event(description, date));
        }

        boolean isDone = savedTasks[1].equals("1");
        if (isDone) {
            savedInputs.get(counter).markAsDone();
        }
    }

}
