import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
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

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(this.filePath);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Task task = stringToTask(s);
            taskList.add(task);
        }
        scanner.close();
        return taskList;
    }

    /**
     * Writes a task to the text file
     * @param taskString content of the task
     * @throws IOException
     */
    public void saveTaskToFile(String taskString) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
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
        //System.out.println("before: \n" + fileString +"\n");
        String newFile = fileString.replace(task.toString() + "\n", "");
        FileWriter writer = new FileWriter(this.filePath);
        writer.write(newFile);
        writer.flush();
        writer.close();
    }
    
    public void markAsDoneInFile(int num, Scanner scanner, TaskList tasks) throws IOException {
        Task task = tasks.taskList.get(num - 1);
        String fileString = "";
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString += input + "\n";
        }
        //System.out.println("before: \n" + fileString +"\n");
        String newFile = fileString.replace(task.toString(), task.toString().replace("[ ]", "[X]"));
        FileWriter writer = new FileWriter(this.filePath);
        writer.write(newFile);
        writer.flush();
        writer.close();
    }
}
