package duke;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;

import java.time.LocalDate;

import java.io.IOException;
import java.io.FileNotFoundException;

import commands.*;
import tasks.*;
import exceptions.*;

/**
 * This class is responsible for reading and writing to the file for a Duke program.
 *
 * On initialization, it will contain a File object to which it will read and write to.
 * If the file path specified does not exist, it will instead create a new clean file
 * for writing to.
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
     * Takes in a line from the task file saved in disk and processes it
     * by adding it to the provided ArrayList of Tasks.
     *
     * @param taskLine A line from the file that is being read from.
     * @param taskList The ArrayList which will contain the Tasks in the file.
     * @throws DukeException If the letter in each line does not match any of the task types.
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

    /**
     * Reads the file of the current Storage object and stores the Tasks
     * saved in the file into a ArrayList of Tasks.
     *
     * @return The ArrayList of Tasks read from the file.
     */
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

    /**
     * Writes each Task in the taskList into the file line by line in the format
     * specified in each task object.
     *
     * @param taskList The TaskList which contains the Tasks to be written into the file.
     * @param taskFile The file which will be written into.
     * @throws IOException
     */
    public void writeTasksToFile(TaskList taskList, File taskFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.taskFile));
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            String line = taskList.getTask(i).saveText();
            writer.write(line);
        }
        writer.close();
    }

    /**
     * Gets the File that belongs to this instance of Storage.
     * @return The File object belonging to this Storage.
     */
    public File getTaskFile() {
        return this.taskFile;
    }
}
