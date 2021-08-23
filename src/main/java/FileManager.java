import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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
                        String[] storedValues = line.substring(5).split("[|]", 5);
                        boolean isDone = storedValues[0].trim().equals("X");
                        boolean isDateOnly = storedValues[1].trim().equals("X");
                        LocalDateTime startDate = LocalDateTime.parse(storedValues[2].trim());
                        LocalDateTime endDate = LocalDateTime.parse(storedValues[3].trim());
                        String description = storedValues[3].trim();
                        taskArrayList.add(new Event(description, startDate, endDate, isDone, isDateOnly));
                    } else if (typeOfTask == 'D') {
                        String[] storedValues = line.substring(5).split("[|]", 4);
                        boolean isDone = storedValues[0].trim().equals("X");
                        boolean isDateOnly = storedValues[1].trim().equals("X");
                        LocalDateTime date = LocalDateTime.parse(storedValues[2].trim());
                        String description = storedValues[3].trim();
                        taskArrayList.add(new Deadline(description, date, isDone, isDateOnly));
                    } else {
                        String[] storedValues = line.substring(5).split("[|]", 2);
                        boolean isDone = storedValues[0].trim().equals("X");
                        String description = storedValues[1].trim();
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
                text += String.format("| E | %s | %s | %s | %s | %s\n", event.isDone ? "X" : " ",
                        event.isDateOnly ? "X" : " ", event.startDateTime, event.endDateTime, event.description);
            } else if (currentTask instanceof Deadline) {
                Deadline deadline = (Deadline) currentTask;
                text += String.format("| D | %s | %s | %s | %s\n", deadline.isDone ? "X" : " ",
                        deadline.isDateOnly ? "X" : " ", deadline.date, deadline.description);
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
