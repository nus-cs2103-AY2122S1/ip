package main;
import task.deadline.Deadline;
import task.event.Event;
import task.Task;
import task.Todo.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handle all hard disk related operations
 * Writes and Loads file
 */
public class Storage {
    private File filepath;

    public Storage(File filepath) {
        this.filepath = filepath;
    }

    /**
     * Read the task list in Duke.txt
     * Input the task into the ArrayList list and update done_check;
     *
     * @param done_check ArrayList for checking whether task is completed.
     * @param list ArrayList for the list of tasks.
     * @param file File Duke.txt file.
     */
    public void load_text_file(ArrayList done_check, ArrayList list, File file) {
        String added = "";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                added = sc.nextLine();
                if (added.equals("")) {
                } else if (added.equals("Do these soon!!")) {
                } else if (added.substring(1, 2).equals("T")) {
                    Task todo = new Todo("todo " + added.substring(7), true);
                    list.add(todo);
                    done_check.add(added.substring(4, 5));
                } else if (added.substring(1, 2).equals("D")) {
                    Task deadline = new Deadline("deadline " + added.substring(7), true);
                    list.add(deadline);
                    done_check.add(added.substring(4, 5));
                } else {
                    Task event = new Event("event " + added.substring(7), true);
                    list.add(event);
                    done_check.add(added.substring(4, 5));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            try {
                Path path = Paths.get("./data");
                Files.createDirectories(path);
                System.out.println("Directory data created");
            } catch (IOException r) {
                System.err.println("Failed to create directory!" + r.getMessage());
            }
        }
    }

    /**
     * Check whether the relevant files for ./data/Duke.txt is created
     * If not, create the folders
     */
    public void check_file_creation() {
        try {
            this.check_file();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            try {
                Path path = Paths.get("./data");
                Files.createDirectories(path);
                System.out.println("Directory data created");
            } catch (IOException r) {
                System.err.println("Failed to create directory!" + r.getMessage());
            }
        }
    }

    private void check_file() throws FileNotFoundException {
        Path path = Paths.get("./data");
        if (!Files.exists(path)) {
            FileNotFoundException e = new FileNotFoundException();
            throw e;
        }
    }

    /**
     * Write the task list into Duke.txt
     *
     * @param list ArrayList<Task> list of tasks
     * @param done_check ArrayList<String> to keep track of completion of tasks
     */
    public void writing_to_file(ArrayList<Task> list, ArrayList<String> done_check) {
        try {
            String task = "Do these soon!!" + "\n";
            for (int i = 0; i < list.size(); i++) {
                task = task + "[" + list.get(i).get_type() + "][" + done_check.get(i) + "] " + list.get(i).get_task() + "\n";
            }
            this.writeToFile("./data/Duke.txt", task);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void writeToFile(String filePath, String textToWrite) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }




}
