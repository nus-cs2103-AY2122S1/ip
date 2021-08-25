<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
=======
import java.time.format.DateTimeParseException;
>>>>>>> branch-Level-8
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.EmptyDescriptionException;
import exceptions.InvalidInputException;
import exceptions.UserInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        String GREETING = "Hello! I'm Duck \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can I do for you?\n";
        String WELCOME_BACK = "Hello! Welcome back! \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can Duck do for you?\n";
        String BYE = "Bye. Hope to see you again soon!\n"
                + "   __(.)>   *quack*\n"
                + "~~ \\___)\n";

        try {
            Path path = Paths.get("data/");
            Files.createDirectories(path); //Create directory if it does not exist
            File taskRecord = new File("data/duke.txt");
            if (taskRecord.createNewFile()) {
                System.out.println(GREETING);
                System.out.println("Creating a new file to save your tasks... *quack*");
            } else {
                //File already exists, parse file and add tasks to ArrayList
                System.out.print(WELCOME_BACK);
                tasks = readSaved(taskRecord);
            }
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
        
        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);
        
        while (isActive) {
            try {
                String newUserInput = scanner.nextLine();
                String firstWord = newUserInput;
                if (newUserInput.contains(" ")) {
                    String[] splitString = newUserInput.split(" ", 2);
                    firstWord = splitString[0];
                    newUserInput = splitString[1];
                } else {
                    newUserInput = "";
                }
                switch (firstWord) {
                    case "bye":
                        isActive = false;
                        System.out.println(BYE);
                        break;
                    case "list":
                        if (tasks.isEmpty()) {
                            System.out.println("There are no tasks on your list. *quack*");
                        } else if (tasks.size() == 1) {
                            System.out.println("There is one task on your list:");
                            System.out.println("1. " + tasks.get(0).toString());
                            System.out.println("*quack*");
                        } else {
                            System.out.println("Here are the tasks on your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println(i + 1 + ". " + tasks.get(i).toString());
                            }
                            System.out.println("*quack*");
                        }
                        break;
                    case "done":
                        int taskNo = Integer.parseInt(newUserInput);
                        if (taskNo < 1 || taskNo > tasks.size()) {
                            System.out.printf("Oops! No such task exists\n" +
                                            "Please use a number from 1 to %s\n",
                                    tasks.size());
                        } else {
                            Task completedTask = tasks.get(taskNo - 1);
                            completedTask.taskDone();
                            tasks.set(taskNo - 1, completedTask);
                            System.out.println("Nice! I've marked this task as done: \n"
                                    + tasks.get(taskNo - 1).toString());
                            rewriteTaskRecord(tasks);
                        }
                        break;
                    case "todo":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        
                        Task newTask = new Todo(newUserInput);
                        tasks.add(newTask);
                        appendTask(newTask);

                        System.out.println("Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n",
                                tasks.size());
                        break;
                    case "deadline":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String description, dateTime;
                        if (newUserInput.contains("/")) {
<<<<<<< HEAD
                            String[] splitString = newUserInput.split(" /by ", 2);
=======
                            String[] splitString = newUserInput.split(" /by ");
>>>>>>> branch-Level-8
                            description = splitString[0];
                            dateTime = splitString[1];
                        } else {
                            description = newUserInput;
                            dateTime = "not specified";
                        }

                        newTask = new Deadline(description, dateTime);
                        tasks.add(newTask);
                        appendTask(newTask);

                        System.out.println("Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n",
                                tasks.size());
                        break;
                    case "event":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        if (newUserInput.contains("/")) {
                            String[] splitString = newUserInput.split(" /at ");
                            description = splitString[0];
                            dateTime = splitString[1];
                        } else {
                            description = newUserInput;
                            dateTime = "not specified";
                        }

                        newTask = new Event(description, dateTime);
                        tasks.add(newTask);
                        appendTask(newTask);
                        
                        System.out.println("Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n",
                                tasks.size());
                        break;
                    case "delete":
                        taskNo = Integer.parseInt(newUserInput);
                        if (taskNo < 1 || taskNo > tasks.size()) {
                            System.out.printf("Oops! No such task exists\n" +
                                            "Please use a number from 1 to %s\n",
                                    tasks.size());
                        } else {
                            System.out.println("Noted. I've removed this task: \n"
                                    + tasks.get(taskNo - 1).toString());
                            tasks.remove(taskNo - 1);
                            System.out.printf("Now you have %d tasks in the list.\n",
                                    tasks.size());
                            rewriteTaskRecord(tasks);
                        }
                        break;
                    default:
                        throw new InvalidInputException();

                }
            } catch (UserInputException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("OOPS! Please input date in this format: yyyy-mm-dd");
                System.out.println(e);
            }
        }
        scanner.close();
    }
    
    private static ArrayList<Task> readSaved (File taskRecord) {
        ArrayList<Task> savedTasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(taskRecord);
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] taskAttributes = taskLine.split(" / ");
                for (String word : taskAttributes){
                    System.out.println(word);
                }
                switch (taskAttributes[0]) {
                    case "T":
                        savedTasks.add(new Todo(taskAttributes[2], taskAttributes[1].equals("1")));
                        break;
                    case "D":
                        savedTasks.add(new Deadline(taskAttributes[2], taskAttributes[3], 
                                taskAttributes[1].equals("1")));
                        break;
                    case "E":
                        savedTasks.add(new Event(taskAttributes[2], taskAttributes[3], 
                                taskAttributes[1].equals("1")));
                        break;
                    default: 
                        System.out.println("OOPS! There is an unknown value in the data file.");
                        break;
                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
        return savedTasks;
    }
    
    private static void appendTask(Task task) {
        try {
            File taskRecord = new File("data/duke.txt");
            FileWriter writer = new FileWriter(taskRecord, true);
            writer.write(task.saveText() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
    }

    private static void rewriteTaskRecord(ArrayList<Task> tasks) {
        try {
            File taskRecord = new File("data/duke.txt");
            FileWriter writer = new FileWriter(taskRecord, false);
            writer.write(""); //Wipe the file
            writer = new FileWriter(taskRecord, true);
            for (Task task : tasks)  {          
                writer.write(task.saveText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
    }
}



