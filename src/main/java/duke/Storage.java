package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the file used to load and save data.
 */
public class Storage {

    private String filepath;

    /**
     * A constructor to create a Storage object.
     *
     * @param filepath The location of the file to load from and save to.
     */
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the list of tasks from a file.
     *
     * @param ls A list to keep track of all tasks.
     */
    public void readFile(TaskList ls) {
        try {
            String arr[];
            File myObj = new File(this.filepath);
            Scanner myScanner = new Scanner(myObj);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                arr = data.split(" ");
                String task = arr[0];
                String description = arr[1] + " ";
                if (task.charAt(2) == 'D') {
                    String deadline = "";
                    for (int i = 2; i < arr.length; i++) {
                        if (i == 2) {
                            deadline += arr[i].substring(1);
                        } else if (i == arr.length - 1) {
                            deadline += " " + arr[i].substring(0, arr[i].length() - 1);
                        } else {
                            deadline += " " + arr[i];
                        }
                    }
                    Deadline item = new Deadline(description, deadline);
                    ls.addTask(item);
                    if (task.substring(4).equals("Done")) {
                        ls.markAsDone(ls.getSize()-1);
                    }
                } else if (task.charAt(2) == 'E') {
                    String deadline = "";
                    for (int i = 2; i < arr.length; i++) {
                        if (i == 2) {
                            deadline += arr[i].substring(1);
                        } else if (i == arr.length - 1) {
                            deadline += " " + arr[i].substring(0, arr[i].length() - 1);
                        } else {
                            deadline += " " + arr[i];
                        }
                    }
                    Event item = new Event(description, deadline);
                    ls.addTask(item);
                    if (task.substring(4).equals("Done")) {
                        ls.markAsDone(ls.getSize() - 1);
                    }
                }
                else {
                    Todo item = new Todo(description);
                    ls.addTask(item);
                    if (task.substring(4).equals("Done")) {
                        ls.markAsDone(ls.getSize() - 1);
                    }
                }
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Read File Error.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes to a file.
     *
     * @param ls A list to keep track of all files.
     */
    public static void saveFile(TaskList ls) {
        try {
            String str = "";
            for (int i = 0; i < ls.getSize(); i++) {
                str += (i + 1) + "."
                        + ls.getTask(i).toString().charAt(1)
                        + "|" + ls.getTask(i).getStatus() + " " + ls.getTask(i).getDescription();
                if (ls.getTask(i) instanceof Deadline || ls.getTask(i) instanceof Event) {
                    str += "(" + ls.getTask(i).getDeadline() + ")";
                }
                if (i != ls.getSize() - 1) {
                    str += "\n";
                }
            }
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Save File Error.");
            System.out.println(e.getMessage());
        }
    }


}
