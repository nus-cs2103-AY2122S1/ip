import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    private static File src;
    private static Scanner fileScanner;

    private enum inputTypes {
        BYE("bye"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), LIST("list"), DONE("done"), DELETE("delete");

        private String value;

        inputTypes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    private static void addNewTodo(String task) throws Exception {
        if (task.length() <= 5) {
            throw new Exception("todoEmpty");
        }
        String taskName = task.substring(5);
        Task todoTask = new Todo(taskName);
        tasks.add(todoTask);
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        saveNewTask(todoTask);
    }

    private static void addNewDeadline(String task) throws Exception {
        int position = task.indexOf('/');
        String taskName = task.substring(9, position - 1);
        String deadlineTime = task.substring(position + 4);
        Task deadlineTask = new Deadline(taskName, deadlineTime);
        tasks.add(deadlineTask);
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        saveNewTask(deadlineTask);
    }

    private static void addNewEvent(String task) throws Exception {
        int position = task.indexOf('/');
        String taskName = task.substring(6, position - 1);
        String eventTime = task.substring(position + 4);
        Task eventTask = new Event(taskName, eventTime);
        tasks.add(eventTask);
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        saveNewTask(eventTask);
    }

    private static void setTaskDone(String task) throws Exception {
        int itemDone = Integer.parseInt(task.substring(5));
        tasks.get(itemDone - 1).finished();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + tasks.get(itemDone - 1).toString());
        modifyTasks();
    }

    private static void showList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i - 1).toString());
        }
    }

    private static void deleteEvent(String task) throws Exception {
        int itemDeleted = Integer.parseInt(task.substring(7));
        Task deletedTask = tasks.remove(itemDeleted - 1);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + deletedTask.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        modifyTasks();
    }

    private static void readEvents(Scanner sc) {
        while (sc.hasNextLine()) {
            String task = sc.nextLine();

            if (task.equals("\n")) {
                System.out.println(task);
                continue;
            }

            char taskType = task.charAt(0);
            char taskDone = task.charAt(4);
            String taskValue = task.substring(8);
            boolean finished = (taskDone == '1');
            Task curTask = new Task("");
            int separatorPosition;

            switch (taskType) {
            case 'T':
                curTask = new Todo(taskValue, finished);
                break;
            case 'E':
                separatorPosition = taskValue.indexOf('|');
                String eventName = taskValue.substring(0, separatorPosition - 1);
                String eventTime = taskValue.substring(separatorPosition + 2);
                curTask = new Event(eventName, finished, eventTime);
                break;
            case 'D':
                separatorPosition = taskValue.indexOf('|');
                String deadlineName = taskValue.substring(0, separatorPosition - 1);
                String deadlineTime = taskValue.substring(separatorPosition + 2);
                curTask = new Deadline(deadlineName, finished, deadlineTime);
            }
            tasks.add(curTask);
        }
    }

    private static void loadSavedTasks() {
        try {
            //Create a File called duke.txt
            src = new File("data/duke.txt");
            if (src.createNewFile()) {
                System.out.println("I have created a new file for you :)");
            }
            Scanner sc = new Scanner(src);
            readEvents(sc);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveNewTask(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(src, true);
            fileWriter.write("\n" + task.toStoredString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void modifyTasks() {
        try {
            FileWriter fileWriter = new FileWriter(src);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(src, true);
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toStoredString());
                if (i != tasks.size() - 1) {
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printSeparateLine() {
        System.out.println("________________________________________________________________");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Read events stored in the input List
        loadSavedTasks();

        //Greeting
        printSeparateLine();
        System.out.println("    Hello! I'm Irving.");
        System.out.println("    What can I do for you?");
        printSeparateLine();

        Scanner inputScanner = new Scanner(System.in);
        String task = inputScanner.nextLine();
        while (!task.equals(inputTypes.BYE.getValue())) {
            try {
                printSeparateLine();
                if (task.contains(inputTypes.TODO.getValue())) {
                    addNewTodo(task);
                } else if (task.contains(inputTypes.DEADLINE.getValue())) {
                    addNewDeadline(task);
                } else if (task.contains(inputTypes.EVENT.getValue())) {
                    addNewEvent(task);
                } else if (task.contains(inputTypes.DONE.getValue())) {
                    setTaskDone(task);
                } else if (task.equals(inputTypes.LIST.getValue())) {
                    showList();
                } else if (task.contains(inputTypes.DELETE.getValue())) {
                    deleteEvent(task);
                } else {
                    throw new Exception("Cannot Understand");
                }
            } catch (Exception e) {
                if (e.getMessage().equals("todoEmpty")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (e.getMessage().equals("Cannot Understand")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } finally {
                printSeparateLine();
                task = inputScanner.nextLine();
            }
        }
        printSeparateLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printSeparateLine();
    }
}
