package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Converts a line from text file to a task
     * @param s a line from text file
     * @return a task from the string
     */
    private Task stringToTask(String s) {
        boolean isDone = false;
        if (s.substring(4,7).equals("[X]")) isDone = true;
        Task task;
        if (s.startsWith("[T]")) {
            task = new Todo(s.substring(8));
        } else if (s.startsWith("[D]")) {
            String name = s.split(":")[0].substring(8);
            String date = s.split(":")[1];
            task = new Deadline(name, date);
        } else {
            String name = s.split(":")[0].substring(8);
            String time = s.split(":")[1];
            task = new Event(name, time);
        }
        if (isDone) task.markAsDone();
        return task;
    }

    /**
     * Loads stored tasks from text file
     * 
     * @return the list of tasks stored in file
     * @throws DukeException
     * @throws IOException
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File nekoData = new File(this.filePath);
        if (!nekoData.exists()) {
            nekoData.getParentFile().mkdir();
            nekoData.createNewFile();
        } else {
            Scanner scanner = new Scanner(nekoData);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                // if (s.startsWith(" ")) break;
                Task task = stringToTask(s);
                taskList.add(task);
            }
            scanner.close();
        }
        
        return taskList;
    }

    /**
     * Writes a task to the text file
     * @param taskString content of the task
     * @throws IOException
     */
    public void saveTaskToFile(String taskString) throws IOException {
        FileWriter writer = new FileWriter(new File(this.filePath), true);
        writer.append(taskString);
        writer.flush();
        writer.close();
    }

    /**
     * Deletes a task from the text file
     * @param num the index of the task in the list
     * @param scanner scanner for the text file
     * @return the updated text file string
     * @throws IOException
     */
    public void deleteTaskFromFile(int num, Scanner scanner, TaskList tasks) throws IOException {

        Task task = tasks.taskList.get(num - 1);
        String fileString = "";
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString += input + "\n";
        }
        
        System.out.println(task.toString());
        String newFile = fileString.replace(task.toString() + "\n", "");
        FileWriter writer = new FileWriter(new File(this.filePath));
        writer.write(newFile);
        writer.flush();
        writer.close();
    }
    
    /**
     * Marks a task as done in the list in the text file.
     * 
     * @param num the index of the task in the list
     * @param scanner scanner for the file
     * @param tasks the given tasks list
     * @throws IOException
     */
    public void markAsDoneInFile(int num, Scanner scanner, TaskList tasks) throws IOException {
        Task task = tasks.taskList.get(num - 1);
        String fileString = "";
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString += input + "\n";
        }
        System.out.println(task.toString());
        String newFile = fileString.replace(task.toString(), task.toString().replace("[ ]", "[X]"));
        FileWriter writer = new FileWriter(new File(this.filePath));
        writer.write(newFile);
        writer.flush();
        writer.close();
    }
}
