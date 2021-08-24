import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

// todo
// 1. Check existence of file and create it
// 2. Read from file
// 3. Save taskList to file

public class Storage {
    File toDoFile;

    public Storage(String filePath) {
        // Check if Kermit data exists, else create it
        this.toDoFile = new File(filePath);
    }

    private ToDo readToDoData(File file) throws KermitException {
        ToDo todo = new ToDo();
        String line;
        Task task;
        try {
            Scanner sc = new Scanner(file);
            // Read file line by line
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] commands = line.split(" \\| ");
                String taskShortForm = commands[0];
                boolean isCompleted = commands[1].equals("1");
                String description = commands[2];
                LocalDate date = LocalDate.parse(commands[3]);

                // Create task based on line data
                switch (taskShortForm) {
                    case "T":
                        task = new ToDos(description, isCompleted);
                        todo.add(task);
                        break;
                    case "D":
                        task = new Deadline(description, date, isCompleted);
                        todo.add(task);
                        break;
                    case "E":
                        task = new Event(description, date, isCompleted);
                        todo.add(task);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new KermitException("Could not find the file!");
        }
        return todo;
    }

    public ToDo getToDoList() throws KermitException {
        return this.readToDoData(toDoFile);
    }
}