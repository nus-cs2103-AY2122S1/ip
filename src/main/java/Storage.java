import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File localFile;

    public Storage(String filePath) {
        this.localFile = new File(filePath);
    }

    public ArrayList<Task> getFile() throws DukeException {
        try {
            if (localFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                ArrayList<Task> taskList = new ArrayList<>();
                Scanner fileScanner = new Scanner(localFile);
                while (fileScanner.hasNextLine()) {
                    String data = fileScanner.nextLine();
                    String[] parameters = data.split(" / ");
                    switch (parameters[0]) {
                        case "T": {
                            ToDo task = new ToDo(parameters[2]);
                            task.setDone(Integer.parseInt(parameters[1]));
                            taskList.add(task);
                            break;
                        }
                        case "D": {
                            Deadline task = new Deadline(parameters[2], LocalDate.parse(parameters[3]));
                            task.setDone(Integer.parseInt(parameters[1]));
                            taskList.add(task);
                            break;
                        }
                        case "E": {
                            Event task = new Event(parameters[2], LocalDate.parse(parameters[3]));
                            task.setDone(Integer.parseInt(parameters[1]));
                            taskList.add(task);
                            break;
                        }
                    }
                }
                fileScanner.close();
                return taskList;
            }
        } catch (IOException e) {
            throw new DukeException("An error occurred. :-(\n");
        }
    }

    public void saveList(ArrayList<Task> taskList) {
        StringBuilder content = new StringBuilder();
        for (Task task : taskList) {
            content.append(task.toFileFormat()).append("\n");
        }
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            myWriter.write(content.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
