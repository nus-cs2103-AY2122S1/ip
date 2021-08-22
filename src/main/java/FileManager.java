import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class FileManager {

    protected static ArrayList<Task> getPreviousTasks() {
        createDir();
        File taskList = new File("data/taskList.txt");
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        if (taskList.exists()) {
            try {
                Scanner s = new Scanner(taskList);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    char typeOfTask = line.charAt(2);
                    if (typeOfTask == 'E') {
                        String description = line.substring(10, 10 + line.substring(10).indexOf('|')).trim();
                        String date = line.substring(line.lastIndexOf('|') + 1).trim();
                        boolean isDone = line.charAt(6) == 'X';
                        taskArrayList.add(new Event(description, date, isDone));
                    } else if (typeOfTask == 'D') {
                        String description = line.substring(10, 10 + line.substring(10).indexOf('|')).trim();
                        String date = line.substring(line.lastIndexOf('|') + 1).trim();
                        boolean isDone = line.charAt(6) == 'X';
                        taskArrayList.add(new Deadline(description, date, isDone));
                    } else {
                        String description = line.substring(10);
                        boolean isDone = line.charAt(6) == 'X';
                        taskArrayList.add(new Todo(description, isDone));
                    }


                }
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } else {
            try {
                taskList.createNewFile();
                return new ArrayList<Task>();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        return taskArrayList;

    }

    protected static void createDir() {
        File dataDir = new File("data");
        dataDir.mkdirs();
    }

    protected static void saveTaskList(ArrayList<Task> taskArrayList) {
        createDir();
        File taskListFile = new File("data/taskList.txt");

        String text = "";

        for (int i = 0; i < taskArrayList.size(); i++) {

            Task currentTask = taskArrayList.get(i);
            if (currentTask instanceof Event) {
                Event event = (Event) currentTask;
                text += String.format("| E | %s | %s | %s\n", event.isDone ? "X" : " ", event.description, event.date);
            } else if (currentTask instanceof Deadline) {
                Deadline deadline = (Deadline) currentTask;
                text += String.format("| D | %s | %s | %s\n", deadline.isDone ? "X" : " ", deadline.description,
                        deadline.date);
            } else {
                Todo todo = (Todo) currentTask;
                text += String.format("| T | %s | %s\n", todo.isDone ? "X" : " ", todo.description);
            }
        }

        try {
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }
            FileWriter fw = new FileWriter("data/taskList.txt");
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
