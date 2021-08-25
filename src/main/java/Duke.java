import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> myTasks = new ArrayList<>();

        // Tries to read from a save file to initialize a saved taskList
        try {

            // Ensure directory and file are both there
            File myFile = new File("data");
            myFile.mkdirs();
            myFile = new File("data/duke.txt");
            myFile.createNewFile();


            // Reads from file is there is anything in it
            Scanner fileReader = new Scanner(myFile);
            boolean checker = true;
            while (fileReader.hasNext() && checker) {
                checker = scanInputs(fileReader, myTasks, false);
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");
        }

        Scanner scan = new Scanner(System.in);

        boolean checker = true;
        while (checker) {
            checker = scanInputs(scan, myTasks, true);
        }
    }


    /**
     * Scans using a provided scanner and adds the tasks to a given taskList.
     *
     * @param scan the scanner to be used
     * @param taskList the taskList to be used
     * @param printMsg prints confirmation messages if true, does not if false
     * @return false if previous input was to terminate ("bye"), else returns true
     */
    public static boolean scanInputs(Scanner scan, ArrayList<Task> taskList, boolean printMsg) {
        String data = scan.next();
        switch (data.toLowerCase()) {
        case "bye":
            scan.close();
            saveToFile(taskList);
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        case "list":
            if (taskList.size() == 0) {
                System.out.println("There are currently no tasks!");
            }
            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(i + ". " + taskList.get(i - 1).toString());
            }
            return true;
        case "done":
            Task task = taskList.get(scan.nextInt() - 1);
            task.markAsDone();
            if (printMsg) {
                System.out.println(" Nice! I've marked this task as done:\n" + task);
            }
            return true;
        case "delete":
            task = taskList.remove(scan.nextInt() - 1);
            if (printMsg) {
                task.removeTaskMsg(taskList.size());
            }
            return true;
        case "todo":
            try {
                Task newTask = new Todo(scan.nextLine());
                taskList.add(newTask);
                if (printMsg) {
                    newTask.addTaskMsg(taskList.size()); // Print confirmation message
                }
            } catch (DukeException e) {
                e.printMsg();
            }
            return true;
        case "deadline":
            try {
                Task newTask = new Deadline(scan.nextLine());
                taskList.add(newTask);
                if (printMsg) {
                    newTask.addTaskMsg(taskList.size()); // Print confirmation message
                }
            } catch (DukeException e) {
                e.printMsg();
            }
            return true;

        case "event":
            try {
                Task newTask = new Event(scan.nextLine());
                taskList.add(newTask);
                if (printMsg) {
                    newTask.addTaskMsg(taskList.size()); // Print confirmation message
                }
            } catch (DukeException e) {
                e.printMsg();
            }
            return true;

        default:
            System.out.println("What are you even typing omg get it right...");
            scan.nextLine(); // Remove all other input to the scanner
            return true;
        }

    }

    /**
     * Saves a list of tasks to a file for future reading.
     *
     * @param taskList the list of tasks
     */
    public static void saveToFile(ArrayList<Task> taskList) {
        try {
            FileWriter myWriter = new FileWriter("data/duke.txt");
            for (int i = 0; i < taskList.size(); i++) {
                taskList.get(i).writeToFile(myWriter);
                myWriter.write("\n");
                if (taskList.get(i).isDone) {
                    myWriter.write("done " + (i + 1));
                    myWriter.write("\n");
                }
            }
            myWriter.close();
            System.out.println("Tasks successfully saved ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
