package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException1;

/**
 * Represents the hard disk/file that stores all the tasks in the application.
 */
public class Storage {
    private String path;

    /**
     * Creates an object of the Storage class to store all tasks on the hard disk.
     *
     * @param filePath Path of the file that stores all the tasks in the application.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Loads the tasks on the hard drive into an ArrayList when the application restarts.
     *
     * @return Arraylist containing all the tasks in the hard disk.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            String path = "data" + File.separator + "duke.txt";
            boolean hasDirectory = Files.exists(Paths.get("data"));
            boolean hasFile = Files.exists(Paths.get("data/duke.txt"));
            if(hasDirectory) {
                if(hasFile) {
                    getContent("data/duke.txt", list);
                    if(list.size() == 0) {
                        System.out.println("There are no tasks");
                    } else {
                        //System.out.println("im here!!");
                        printFile("data/duke.txt");
                    }
                } else {
                    File file = new File(path);
                }
            } else {
                File file = new File(path);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("error!");
        } finally {
            return list;
        }
    }

    /**
     * Gets all the tasks in the hard disk
     *
     * @param filePath Path of the file that contains all the tasks in the application.
     * @param list Arraylist that stores all the tasks.
     * @throws FileNotFoundException If file is not found.
     * @throws DukeException1 If there is an error in creating a todo, event, deadline task.
     */
    public void getContent(String filePath, ArrayList<Task> list) throws FileNotFoundException, DukeException1 {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            /*System.out.println(s);
            String[] st = s.split(" ");
            for (int i = 0; i < st.length; i++) {
                System.out.println(st[i]);
            }*/
            try {
                if(s.split("/")[0].equals("T")) {
                    ToDo todo = new ToDo(s.split("/")[2]);
                    list.add(todo);
                    if(s.split("/")[1].equals("1")) {
                        todo.setDone();
                    }
                } else if(s.split("/")[0].equals("D")) {
                    Deadline deadline = new Deadline(s.split("/", 4)[2], s.split("/", 4)[3]);
                    list.add(deadline);
                    if(s.split("/")[1].equals("1")) {
                        deadline.setDone();
                    }
                } else {
                    Event event = new Event(s.split("/", 4)[2], s.split("/", 4)[3]);
                    list.add(event);
                    if(s.split("/")[1].equals("1")) {
                        event.setDone();
                    }
                }
            } catch (DukeException1 e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints all tasks in the file when the application restarts.
     *
     * @param path Path of the file containing all the tasks.
     * @throws FileNotFoundException If file is not found.
     */
    public void printFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    /**
     * Appends a task to the end of the file storing all the tasks.
     *
     * @param task Task to be appended to the end of the file.
     */
    public void appendToFile(Task task) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("data/duke.txt", true));
            write.append(task.storeTask());
            write.newLine();
            write.close();
        }  catch(IOException e) {
            System.out.println("error occurred when appending task to file!");
        }
    }

    /**
     * Rewrites the entire file from the start.
     *
     * @param tasks The Arraylist storing all the tasks.
     */
    public void rewriteFile(ArrayList<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter("data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                //if (i != number) {
                writer.println(task.storeTask());
                //writeToFile("data/duke.txt", task.getType() + " | " + task.getDone() + " | " + task.getInfo());
                //}
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred in rewriting entire file!");
        }
    }

}
