import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int index = 0;
    private static void addTask(Task task) {
        taskList.add(task);
        index++;
    }
    private static void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
        index--;
    }
    private static String convertTaskToText(Task task) {
        String result = task.type + "|";
        if(task.isCompleted()) {
            result += "1|";
        }else {
            result += "0|";
        }
        result += task.getTaskContent();
        if(task.type.equals("D") || task.type.equals("E")) {
            result += "|" + task.getTiming();
        }
        return result;
    }
    private static Task convertTextToTask(String text) {
        String[] str = text.split("\\|");
        Task newTask;
        if(str[0].equals("T")) {
            newTask = new ToDo(str[2]);
        }else if(str[0].equals("D")) {
            newTask = new Deadline(str[2], str[3]);
        }else {
            newTask = new Event(str[2], str[3]);
        }
        if(str[1].equals("1")) {
            newTask.markCompleted();
        }
        return newTask;
    }
    private static void writeToFile(Path path) {
        StringBuilder combinedTask = new StringBuilder();
        for(int i = 0; i < index; i++) {
            combinedTask.append(convertTaskToText(taskList.get(i))).append("\n");
        }
        try {
            FileWriter writer = new FileWriter(String.valueOf(path), false);
            writer.write(combinedTask.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred 2");
        }
    }
    private static void createFile(Path directoryPath, Path filePath) {
        File newDirectory = new File(String.valueOf(directoryPath));
        File newFile = new File(String.valueOf(filePath));
        try {
            newDirectory.mkdir();
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error occurred 1");
        }
    }
    private static void saveTask() {
        String home = System.getProperty("user.home");
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "iP", "data", "duke.txt");
        java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "iP", "data");
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if(!directoryExists) {
            createFile(directoryPath, filePath);
        }
        writeToFile(filePath);
    }
    private static void loadTask() {
        String home = System.getProperty("user.home");
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "iP", "data", "duke.txt");
        try {
            File myObj = new File(String.valueOf(filePath));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                taskList.add(convertTextToTask(data));
                index++;
            }
            myReader.close();
            taskList.forEach(task -> {
                System.out.println(task.toString());
            });
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("Here are your tasks");
        loadTask();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(input.split(" ")[0].equals("delete")) {
                Task taskToBeDeleted = taskList.get(Integer.parseInt(input.split(" ")[1]) - 1);
                deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
                System.out.println("Noted. I've removed this task: \n" +
                         "  " + taskToBeDeleted.toString() +"\n" +
                        "Now you have " + index + " tasks in the list.");
                saveTask();
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < index; i++){
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
            }else if(input.split(" ")[0].equals("done")){
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                taskList.get(taskIndex - 1).markCompleted();
                System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString());
                saveTask();
            } else if(input.split("todo").length == 0) {
                try {
                    throw new DukeException.emptyToDoDescriptionException();
                } catch (DukeException.emptyToDoDescriptionException e) {
                    e.exceptionMessage();
                }
            }else if(input.split("deadline").length == 0) {
                try {
                    throw new DukeException.emptyDeadlineDescriptionException();
                } catch (DukeException.emptyDeadlineDescriptionException e) {
                    e.exceptionMessage();
                }
            }else if(input.split("event").length == 0) {
                try {
                    throw new DukeException.emptyEventDescriptionException();
                } catch (DukeException.emptyEventDescriptionException e) {
                    e.exceptionMessage();
                }
            } else if(input.split("event")[0].equals("")) {
                String taskContent = input.split("event ")[1];
                Task newEvent = new Event(taskContent);
                addTask(newEvent);
                System.out.println("Got it. I've added this task: \n" +
                        "  " + newEvent.toString() + "\n" +
                        "Now you have " + index + " tasks in the list.");
                saveTask();
            } else if(input.split("deadline")[0].equals("")) {
                String taskContent = input.split("deadline ")[1];
                Task newEvent = new Deadline(taskContent);
                addTask(newEvent);
                System.out.println(" Got it. I've added this task: \n" +
                        "  " + newEvent.toString() + "\n" +
                        "Now you have " + index + " tasks in the list.");
                saveTask();
            } else if(input.split("todo")[0].equals("")) {

                String taskContent = input.split("todo ")[1];
                Task newEvent = new ToDo(taskContent);
                addTask(newEvent);
                System.out.println("Got it. I've added this task: \n" +
                        "  " + newEvent.toString() + "\n" +
                        "Now you have " + index + " tasks in the list.");
                saveTask();
            }
            else {
                try{
                    throw new DukeException.invalidInputException();
                } catch (DukeException.invalidInputException e) {
                    e.exceptionMessage();
                }
            }
        }
    }

}
