package duke;

import dukeException.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTaskList() throws DukeException{
        File f;
        Scanner sc;
        try {
            f = new File(filePath);
            /** If folder data does not exist, mkdir. **/
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
            /** If file taskList.txt does not exist, create it. **/
            if (!f.exists()) {
                f.createNewFile();
            }
            sc = new Scanner(f);

            int n = sc.nextInt();
            //        System.out.println("total number of tasks loaded from file = " + n);

            ArrayList<Task> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int type = sc.nextInt();
                int isDone = sc.nextInt();
                sc.nextLine();
                String taskName = sc.nextLine();
                String taskDate = sc.nextLine();
                String taskTime = sc.nextLine();

                //            System.out.println(String.format("[%d %d [%s] [%s]]", type, isDone, taskName, taskDate));
                Task newTask;
                if (type == 1) {
                    newTask = new Todo(taskName);
                } else if (type == 2) {
                    LocalDate date = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalTime time = null;
                    if (!taskTime.equals("null")) {
                        time = LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HHmm"));
                    }
                    newTask = new Deadline(taskName, date, time);
                } else {
                    LocalDate date = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalTime time = null;
                    if (!taskTime.equals("null")) {
                        time = LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HHmm"));
                    }
                    newTask = new Event(taskName, date, time);
                }

                if (isDone == 1) {
                    newTask.markAsDone();
                }

                result.add(newTask);
            }
            return result;
        } catch (IOException e) {
            throw new DukeException("Cannot load task list from file: " + filePath);
        } catch (NoSuchElementException e) {
            throw new DukeException("The format of taskList.txt is incorrect");
        }
    }

    public void saveTaskList(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            int n = tasks.size();
            fw.write(String.format("%d\n", n));

            for (int i = 0; i < n; i++) {
                Task currentTask = tasks.get(i);
                if (currentTask.getTypeIcon() == "T") {
                    fw.write(String.format("%d ", 1));
                } else if (currentTask.getTypeIcon() == "D") {
                    fw.write(String.format("%d ", 2));
                } else {
                    fw.write(String.format("%d ", 3));
                }

                fw.write(currentTask.isDone ? "1\n" : "0\n");

                fw.write(currentTask.description + "\n");

                if (currentTask instanceof Deadline) {
                    Deadline d = (Deadline) currentTask;
                    fw.write(d.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
                    if (d.time != null) {
                        fw.write(d.time.format(DateTimeFormatter.ofPattern("HHmm")) + "\n");
                    } else {
                        fw.write("null\n");
                    }
                } else if (currentTask instanceof Event) {
                    Event e = (Event) currentTask;
                    fw.write(e.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
                    if (e.time != null) {
                        fw.write(e.time.format(DateTimeFormatter.ofPattern("HHmm")) + "\n");
                    } else {
                        fw.write("null\n");
                    }
                } else {
                    fw.write("noSpecificTime\n");
                }

                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save task list to file: " + filePath);
        }
    }
}
