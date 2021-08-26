package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static String filePath;

    private boolean exit = false;

    /**
     * Public constructor of the Storage.
     *
     * @param filePath The filepath to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks in a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isDone ? "1" : "0";
            String date = "";
            String activity = "";
            String information = "";
            if (task instanceof Event) {
                activity = "E";
                information = task.getDescription();
                date = ((Event) task).getDate();
                String desc = String.format("%s | %s | %s | %s\n",activity, status, information,date);
                fw.write(desc);
            } else if (task instanceof Deadline) {
                activity = "D";
                information = task.getDescription();
                date = ((Deadline) task).getDate();
                String desc = String.format("%s | %s | %s | %s\n",activity, status, information, date);
                fw.write(desc);
            } else if (task instanceof ToDo) {
                activity = "T";
                information = task.getDescription();
                String desc = String.format("%s | %s | %s\n",activity, status, information);
                fw.write(desc);
            }
        }
        fw.close();
    }

    /**
     * Loads the given file of tasks.
     *
     * @return The list of tasks.
     * @throws FileNotFoundException If file cannot be located.
     */
     public TaskList load() throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        TaskList tasks = new TaskList();
        while (s.hasNext()) {
            String text = s.nextLine();
            String activity = text.substring(0, 1);
            String status = text.substring(4, 5);
            String desc, date;

            if (activity.equals("T")) {
                desc = text.substring(8);
                ToDo todo = new ToDo(desc);
                if (status.equals("1")) {
                    todo.setDone();
                }
                tasks.add(todo);
            } else {
                int thirdBarId = text.indexOf('|', 7);
                desc = text.substring(8, thirdBarId - 1);
                date = text.substring(thirdBarId + 2);

                if (activity.equals("D")) {
                    Deadline deadline = new Deadline(desc, date);
                    if (status.equals("1")) {
                        deadline.setDone();
                    }
                    tasks.add(deadline);
                } else {
                    Event event = new Event(desc, date);
                    if (status.equals("1")) {
                        event.setDone();
                    }
                    tasks.add(event);
                }
            }
        }
        System.out.println("These are your current activities:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
        return tasks;
    }

    /**
     * Checks if a program should end.
     *
     * @return True if program should end, false otherwise.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Sets the boolean deciding whether a program should end.
     */
    public void setExit() {
        exit = true;
    }
}
