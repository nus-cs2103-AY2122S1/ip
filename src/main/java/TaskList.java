import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The task list to save all the tasks
 */
public class TaskList {
    // Saved tasks
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        ArrayList<Task> temp;
        try {
            temp = loadTaskListFromHardDisk();
        } catch (IOException e) {
            temp = new ArrayList<>();
            PrintUtil.displayContent("Can't read the save file.");
        }
        this.tasks = temp;
    }
    
    /**
     * Add a task to the list
     *
     * @param task The added task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        
        StringBuilder response = new StringBuilder("Got it. I've added this task:");
        response.append("\t").append("  ").append(task)
                .append("\n\t Now you have ").append(tasks.size());
        if (tasks.size() == 1) {
            response.append(" task in the list.");
        } else {
            response.append(" tasks in the list.");
        }
        saveTaskListToHardDisk();
        return response.toString();
    }

    /**
     * Mark a task in the task list as done
     * 
     * @param index The user requested index.
     * @return The status of the operation.
     */
    public String markTaskAsDone(int index) {
        StringBuilder response = new StringBuilder();
        if (tasks.get(index - 1).markAsDone()) {
            response.append("Nice! I've marked this task as done:\n");
        } else {
            response.append("This task is already done!");
        }
        response.append("\t" + "  ").append(tasks.get(index - 1).toString());
        saveTaskListToHardDisk();
        return response.toString();
    }

    public String delete(int index) {
        Task temp = tasks.get(index - 1);
        tasks.remove(index - 1);
        
        StringBuilder response = new StringBuilder("Noted. I've removed this task:");
        response.append("\t").append("  ").append(temp)
                .append("\n\t Now you have ").append(tasks.size());
        if (tasks.size() == 1) {
            response.append(" task in the list.");
        } else {
            response.append(" tasks in the list.");
        }
        saveTaskListToHardDisk();
        return response.toString();
    }
    
    /**
     * Return the size of the task list
     * 
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    private void saveTaskListToHardDisk() {
        try {
            FileWriter fileWriter = new FileWriter("src/data/duke.txt");
            if (tasks.size() == 0) {
                fileWriter.write("");
            } else {
                for (Task t : tasks) {
                    fileWriter.write(t.toSaveInHardDisk() + System.getProperty("line.separator"));
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            PrintUtil.displayContent("Can't save the tasks");
        }
    }
    
    private ArrayList<Task> loadTaskListFromHardDisk() throws IOException {
        File dir = new File("src/data");
        File logs = new File("src/data/duke.txt");
        ArrayList<Task> result = new ArrayList<>();
        try {
            result = parseSavedTaskList(logs);
        } catch (FileNotFoundException e) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!logs.exists()) {
                logs.createNewFile();
            }
        } catch (DukeCorruptedSaveException e) {
            logs.delete();
            logs.createNewFile();
            PrintUtil.displayContent("There's an error with the save file, the saved task list is deleted");
        }
        return result;
    }
    
    private ArrayList<Task> parseSavedTaskList(File logs) throws FileNotFoundException, DukeCorruptedSaveException {
        Scanner sc = new Scanner(logs);
        ArrayList<Task> tasks = new ArrayList<>();
        
        while (sc.hasNext()) {
            String[] currentLine = sc.nextLine().split(" ; ");
            switch (currentLine[0].trim()) {
                case "T": {
                    Task temp = new ToDo(currentLine[2].trim());
                    if (currentLine[1].equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                    break;
                }
                case "D": {
                    Task temp = new Deadline(currentLine[2].trim(), LocalDate.parse(currentLine[3]));
                    if (currentLine[1].equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                    break;
                }
                case "E": {
                    Task temp = new Event(currentLine[2].trim(), LocalDate.parse(currentLine[3].trim()));
                    if (currentLine[1].equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                    break;
                }
                default:
                    throw new DukeCorruptedSaveException("The save file is corrupted!");
            }
        }
        return tasks;
    }
    
    @Override
    public String toString() {
        if (this.size() == 0) {
            return "There is no task in the list";
        } else {
            StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                String temp = "\t" + " " + (i + 1) + "." + tasks.get(i).toString();
                if (i < tasks.size() - 1) { // remove the last \n char, ugly but get the job done
                    temp += "\n";
                }
                res.append(temp);
            }
            return res.toString();
        }
    }
}
