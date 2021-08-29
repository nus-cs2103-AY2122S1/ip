package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private static final Pattern STORE_FORMAT = Pattern.compile("\\[(?<type>\\S)] \\[(?<done> |X)] (?<arguments>.*)");
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public TaskList load() throws Exception {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(new FileReader(fileName));
        while (sc.hasNextLine() ) {
            String input = sc.nextLine();
            Matcher matcher = STORE_FORMAT.matcher(input);
            if (!matcher.matches()) {
                throw new DukeException("HAHAHHAHA");
            }
            String type = matcher.group("type");
            String done = matcher.group("done");
            String arguments = matcher.group("arguments");
            String[] parts;
            Task task;

            switch (type) {
            case "T":
                task = new Todo(arguments);
                if (done.equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            case "D":
                parts = arguments.split(" \\(by: |\\)");
                task = new Deadline(parts[0], parts[1]);
                if (done.equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            case "E":
                parts = arguments.split(" \\(at: |\\)");
                task = new Event(parts[0], parts[1]);
                if (done.equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            }

        }

        return taskList;
    }

    public void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void createNewFile() {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

//    public void readFromFile(duke.TaskList taskList) {
//        try {
//            Scanner sc = new Scanner(new FileReader(fileName));
//            while (sc.hasNextLine()) {
//                String input = sc.nextLine();
//                String[] parts = input.split(" ", 3);
//                duke.tasks.Task task;
//
//                switch (parts[0]) {
//                case "[T]":
//                    task = createTask("todo", parts[2]);
//                    taskList.add(task);
//                    if (parts[1].equals("[X]")) {
//                        task.markAsDone();
//                    }
//                    break;
//                case "[D]":
//                    task = createTask("deadline", parts[2]);
//                    taskList.add(task);
//                    if (parts[1].equals("[X]")) {
//                        task.markAsDone();
//                    }
//                    break;
//                case "[E]":
//                    task = createTask("event", parts[2]);
//                    taskList.add(task);
//                    if (parts[1].equals("[X]")) {
//                        task.markAsDone();
//                    }
//                    break;
//                }
//
//            }
//        } catch (Exception e) {
//            File file = new File(filename);
//            try {
//                file.createNewFile();
//            } catch (IOException ioException) {
//                System.out.println("Error: " + ioException);
//            }
//        }
//    }
//
//    public void writeToFile(duke.TaskList taskList) {
//        try {
//            FileWriter fw = new FileWriter(fileName);
//            for (int i = 0; i < taskList.size(); i++) {
//                fw.write(taskList.getTask(i) + "\n");
//            }
//            fw.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
}
