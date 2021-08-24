import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that contains the initialization of a list to store the tasks
 *
 * @author: Wei Yangken
 */
public class Tasklist {
    private ArrayList<Task> taskList;
    private static int currCount = 0;
    private static String fileDirectory = Duke.fileDirectory;

    /**
     * Constructor to create a new taskList to store tasks
     */
    Tasklist(ArrayList<Task> taskList) {
       this.taskList = taskList;
    }

    /**
     * Adds task to list and notifies the user if task has not been added properly
     * @param task Task to be added
     */
    public void add(Task task) {
        try {
            if(task == null) {
                throw new DukeException.TaskNotAddedException("Task has not been added successfully.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(Duke.breakline);
            return;
        }

        this.taskList.add(task);
        currCount += 1;
        System.out.println("Got it. I've added this task:");
        String addMsg = String.format("%s", task.toString());
        String counterMsg = String.format("Now you have %d tasks in the list.", currCount);
        System.out.println(addMsg);
        System.out.println(counterMsg);
        System.out.println(Duke.breakline);
    }

    /**
     * Lists out the tasks present in tasklist.
     * Checks for the scenario where list is empty.
     */
    public void list() {
        try {
            if(this.taskList.size() == 0) {
                throw new DukeException.EmptyListException("List is empty :(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(Duke.breakline);
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for(int i=0;i < taskList.size(); i++) {
            if(!this.taskList.get(i).isDone()) {
                System.out.printf("%d. %s\n", i + 1, this.taskList.get(i).toString());
            } else {
                System.out.printf("%d. %s\n", i + 1, this.taskList.get(i).toString());
            }
        }
        System.out.println(Duke.breakline);
    }

    /**
     * Deletes the indicated task from tasklist.
     * Accounts for situations where task could not be found.
     * @param idx Ranking of task to be deleted
     */
    public void delete(int idx) {
        Task removedTask;
        try {
            removedTask = this.taskList.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Item %d cannot be found.", idx);
            System.out.println(Duke.breakline);
            return;
        }

        String removeMsg = String.format("Noted. I've removed this task:\n%s",removedTask.toString());
        System.out.println(removeMsg);
        currCount = currCount - 1;
        System.out.printf("Now you have %d task(s) in the list.\n", currCount);
        System.out.println(Duke.breakline);
    }

    /**
     * Returns the task of a particular index in arraylist
     * @param idx Index in the arraylist
     * @return Task of a particular index
     */
    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    public void updateFile() {
        File file = new File(fileDirectory);
        try {
            if (file.createNewFile()) {
                System.out.printf("Saved file at %s\n", fileDirectory);
            } else {
                System.out.printf("File already exists at %s\n", fileDirectory);
            }

        } catch (IOException e) {
            System.out.println("An error occurred during creation of file.");
        }

        try {
            FileWriter fileWriter = new FileWriter(fileDirectory);
             String headers = "S/n,Type,Status,Name,Remarks\n";
             fileWriter.write(headers);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String listEntry = String.format("%d,%s,%b,%s,%s\n",
                        i+1, task.getTaskCat(), task.isDone(), task.getName(), task.getDetail());
                fileWriter.append(listEntry);
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
        }
    }

    public Tasklist readFile() {
        currCount = 0;
        ArrayList<Task> taskList = this.taskList;
        try {
            File file = new File(fileDirectory);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] fields = data.split(",");
                String type = fields[1];
                String isDone = fields[2];
                String taskName = fields[3];
                String remarks;
                try {
                    remarks = fields[4];
                } catch (IndexOutOfBoundsException e) {
                    remarks = "";
                }

                String input = taskName + " " + remarks;
                Task task = Task.parseStringIntoTask(input, type, Boolean.parseBoolean(isDone));
                taskList.add(task);
                currCount++;
            }
            System.out.printf("Loaded old tasklist...\n");
            System.out.println(Duke.breakline);

        } catch (FileNotFoundException e) {
            System.out.printf("Initializing new tasklist...\n");
            System.out.println(Duke.breakline);
        }
        return new Tasklist(taskList);
    }
}


