import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;

import java.time.LocalDate;

import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * This class is responsible for reading and writing to the file for a Duke program.
 *
 */
public class Storage {

    private File taskFile;

    Storage(String filePath) {
        String localDir = System.getProperty("user.dir");
        this.taskFile = new File(localDir + File.separator + filePath);
        if (!taskFile.exists()) {
            taskFile.getParentFile().mkdirs();
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create new file");
            }
        }
    }

    /**
     * Takes in a line from the task file saved in disk and process it
     * @param taskLine A line from the file that is being read from.
     *
     */
    private void process(String taskLine, ArrayList<Task> taskList) throws DukeException {
        String[] parsedLine = taskLine.split(" \\| ", 3);
        String command = parsedLine[0];
        Boolean isDone = parsedLine[1].equals("1");
        String next = parsedLine[2];
        if (command.equals("T")) {
            Todo todo = new Todo(next, isDone);
            taskList.add(todo);
        } else if (command.equals("D")) {
            String[] details = next.split(" \\| ", 2);
            String desc = details[0];
            LocalDate dueDate = LocalDate.parse(details[1]);
            Deadline dl = new Deadline(desc, isDone, dueDate);
            taskList.add(dl);
        } else if (command.equals("E")) {
            String[] details = next.split(" \\| ", 2);
            String desc = details[0];
            String time = details[1];
            Event e = new Event(desc, isDone, time);
            taskList.add(e);
        } else {
            throw new DukeException();
        }
    }

    public ArrayList<Task> readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.taskFile))) {
            ArrayList<Task> taskList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null && !line.equals("")) {
                this.process(line, taskList);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("IO Exception occurred");
            return new ArrayList<>();
        } catch (DukeException e) {
            System.out.println("Invalid command found in file");
            return new ArrayList<>();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid file format");
            return new ArrayList<>();
        }
    }

    public void writeTasksToFile(TaskList taskList, File taskFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.taskFile));
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            String line = taskList.getTask(i).saveText();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    public File getTaskFile() {
        return this.taskFile;
    }
}
