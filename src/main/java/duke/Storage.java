package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    private boolean exit = false;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public boolean isExit() {
        return exit;
    }

    public void setExit() {
        exit = true;
    }
}
