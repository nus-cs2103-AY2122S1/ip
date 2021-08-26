import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    public static void loadTasksFromFile(ArrayList<Task> tasks) throws IOException, DukeException {
        String directoryPath = "./data";
        String filePath = directoryPath.concat("/duke.txt");

        File directory = new File(directoryPath);
        File dataFile = new File(filePath);

        if (!directory.exists()) {
            throw new DukeException("OOPS!! Directory does not exist");
        } else if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        Scanner f = new Scanner(dataFile);

        while (f.hasNext()) {
            String[] task = f.nextLine().split("|");
            String taskType = task[0];
            boolean isDone = (Integer.parseInt(task[1]) == 1);
            String description = task[2];
            String time = task.length == 4 ? task[3] : null;

            if (taskType.equals("T")) {
                tasks.add(new TodoTask(description, isDone));
            } else if (taskType.equals("D")) {
                tasks.add(new DeadlineTask(description, isDone, time));
            } else {
                tasks.add(new EventTask(description, isDone, time));
            }
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String type = t.type;
            String description = t.description;
            String isDone = t.isDone ? "1" : "0";

            String line = type + "|" + isDone + "|" + description;
            if (t.type.equals("D")) {
                DeadlineTask dt = (DeadlineTask) t;
                line += "|" + dt.time;
            } else if (t.type.equals("E")) {
                EventTask et = (EventTask) t;
                line += "|" + et.time;
            }
            line += "\n";
            fw.write(line);
        }

        fw.close();
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________\n";
        System.out.println(line + "Hello I'm Duke\nWhat can I do for you?\n" + line);

        ArrayList<Task> tasks = new ArrayList<>(100);
        int ctr = 0;

        try {
            loadTasksFromFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong while trying to load tasks: " + e.getMessage());
            System.exit(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            try {
                if (command.contains("done")) {
                    if (!command.contains(" ")) {
                        throw new DukeException("OOPS!! done needs the index of the task.");
                    }
                    int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasks.get(idx).updateStatus();
                    System.out.printf("%sNice! I've marked this task as done:\n%s\n%s\n", line, tasks.get(idx), line);
                } else if (command.equals("list")) {
                    System.out.println(line + "Here are the tasks in your list:");
                    for (int i = 0; i < ctr; i++) {
                        System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                    }
                    System.out.println(line);
                }else if (command.contains("delete")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! delete needs the index of the task.");
                    }
                    int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task t = tasks.remove(idx);
                    System.out.printf("%sNoted. I've removed this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, t, ctr - 1, line);
                    ctr--;
                } else if (command.contains("todo")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! The description of a todo cannot be empty.");
                    }
                    int taskIdxStart = command.indexOf(" ") + 1;
                    String task = command.substring(taskIdxStart);
                    tasks.add(new TodoTask(task));
                    System.out.printf("%sGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, tasks.get(ctr), ctr + 1, line);
                    ctr++;
                } else if (command.contains("deadline")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
                    }
                    int taskIdxStart = command.indexOf(" ") + 1;
                    int timeIdxStart = command.indexOf("/");
                    String task = command.substring(taskIdxStart, timeIdxStart - 1);
                    String time = command.substring(timeIdxStart + 4);
                    tasks.add(new DeadlineTask(task, time));
                    System.out.printf("%sGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, tasks.get(ctr), ctr + 1, line);
                    ctr++;
                } else if (command.contains("event")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! The description of an event cannot be empty.");
                    }
                    int taskIdxStart = command.indexOf(" ") + 1;
                    int timeIdxStart = command.indexOf("/");
                    String task = command.substring(taskIdxStart, timeIdxStart - 1);
                    String time = command.substring(timeIdxStart + 4);
                    tasks.add(new EventTask(task, time));
                    System.out.printf("%sGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, tasks.get(ctr), ctr + 1, line);
                    ctr++;
                } else {
                    throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(line + e.getMessage() + "\n"+ line);
            }
            command = sc.nextLine();
        }

        try {
            saveTasksToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong while trying to save tasks: " + e.getMessage());
            System.exit(0);
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
