import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                System.out.println("asdf");
                Task newTask;
                try {
                    String taskInfo = line.substring(7);
                    switch (line.charAt(1)) {
                    case 'D':
                        String[] deadlineInfo = taskInfo.split(" \\| by: ");
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy");
                        LocalDate by = LocalDate.parse(deadlineInfo[1], dateFormatter);
                        newTask = new Deadline(deadlineInfo[0], by);
                        break;
                    case 'E':
                        String[] eventInfo = taskInfo.split(" \\| at: ");
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mma");
                        LocalDateTime at = LocalDateTime.parse(eventInfo[1], dateTimeFormatter);
                        newTask = new Event(eventInfo[0], at);
                        break;
                    case 'T':
                        newTask = new ToDo(taskInfo);
                        break;
                    default:
                        throw new DukeException("Error: Data is formatted incorrectly in storage file.");
                    }
                } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                    throw new DukeException("Error: Data is formatted incorrectly in storage file.");
                }
                if (line.charAt(4) == 'X') {
                    newTask.isDone = true;
                }
                tasks.add(newTask);
                line = br.readLine();
            }
        } catch (java.io.IOException e) {
            throw new DukeException("Error: The specified file path for storage cannot be resolved.");
        }
        return tasks;
    }

    public void save(TaskList tasks) { // TODO: Tidy this
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            int taskCount = tasks.getSize();
            for (int i = 0; i < taskCount; i++) {
                fw.write( tasks.getTask(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
