import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Lifeline {
    private Scanner sc;
    private ArrayList<Task> taskList;
    private String command;
    private Gson gson;

    Lifeline() {
        this.taskList = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.gson = new Gson();
        try {
            this.taskList = this.load("./save/tasks.json");
        } catch (LifelineException e) {
            System.out.println(e.getMessage());
            this.taskList = new ArrayList<>();
        }
    }

    private void greet() {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        System.out.println("Hello! I am\n" + lifeline);
        printList();
        System.out.println("What can I help you with today?\n");
    }


    private void getInput() {
        boolean exit = false;
        while (!exit) {
            try {
                this.command = sc.nextLine().trim();
                String[] inputs = command.split("\\s", 2);
                System.out.println();
                switch (getInputType(inputs[0])) {
                case LIST:
                    printList();
                    break;
                case BYE:
                    exit = true;
                    break;
                case DONE:
                    if (inputs.length != 2) {
                        throw new LifelineException("You did not specify an integer! Please use done <number>");
                    }
                    markAsDone(inputs[1]);
                    break;
                case DELETE:
                    if (inputs.length != 2) {
                        throw new LifelineException("You did not specify an integer! Please use delete <number>");
                    }
                    deleteTask(inputs[1]);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    if (inputs.length != 2) {
                        throw new LifelineException("Details of task cannot be blank!");
                    }
                    createTask(inputs[0].trim(), inputs[1].trim());
                    break;
                default:
                    echo(inputs[0]);
                    break;
                }
            } catch (LifelineException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        exit();
    }

    private void save(ArrayList<Task> tasks) throws LifelineException {
        try {
            FileWriter fileWriter = new FileWriter("./save/tasks.json");
            fileWriter.write(gson.toJson(tasks, ArrayList.class));
            fileWriter.close();
        } catch (IOException e) {
            throw new LifelineException("Unable to save tasks at the moment");
        }
    }

    private ArrayList<Task> load(String filepath) throws LifelineException {
        try {
            JsonArray arr = JsonParser.parseReader(new FileReader(filepath)).getAsJsonArray();
            ArrayList<Task> savedTasks = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                JsonObject currTask = arr.get(i).getAsJsonObject();
                if (currTask.has("by")) {
                    savedTasks.add(new Deadline(currTask.get("name").getAsString(),
                            currTask.get("by").getAsString(),
                            currTask.get("isDone").getAsBoolean()));

                } else if (currTask.has("dateTime")) {
                    savedTasks.add(new Event(gson.fromJson(currTask.get("name"), String.class),
                            gson.fromJson(currTask.get("dateTime"), String.class),
                            gson.fromJson(currTask.get("isDone"), boolean.class)));
                } else {
                    savedTasks.add(new ToDo(gson.fromJson(currTask.get("name"), String.class),
                            gson.fromJson(currTask.get("isDone"), boolean.class)));
                }
            }
            return savedTasks;
        } catch (IOException e) {
            throw new LifelineException("Cannot read file or file does not exist");
        }
    }

    private InputType getInputType(String input) throws LifelineException {
        try {
            return InputType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LifelineException("I am sorry! I don't know what that means! ☹");
        }
    }

    private void createTask(String task, String details) throws LifelineException {
        switch (task) {
        case "todo":
            Task newTask = new ToDo(details);
            addToList(newTask);
            break;
        case "deadline":
            String[] description = details.split("/by", 2);
            if (description.length != 2) {
                throw new LifelineException("Deadline cannot be blank! Use /by <deadline>");
            }
            newTask = new Deadline(description[0].trim(), description[1].trim());
            addToList(newTask);
            break;
        case "event":
            description = details.split("/at", 2);
            if (description.length != 2) {
                throw new LifelineException("Event date/time cannot be blank! Use /at <Day> <Time>");
            }
            newTask = new Event(description[0].trim(), description[1].trim());
            addToList(newTask);
            break;
        default:
            echo(command);
            break;
        }
        save(taskList);
    }

    private void printList() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks.\n");
        } else {
            int uncompletedTask = 0;
            System.out.println("Here " + (taskList.size() > 1 ? "are" : "is")
                    + " your " + (taskList.size() > 1 ? "tasks:" : "task:"));
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println((i + 1) + ". " + currTask);
                if (!currTask.isDone()) {
                    uncompletedTask++;
                }
            }
            System.out.println("You have " + uncompletedTask + " uncompleted " + (uncompletedTask > 1 ? "tasks"
                    : "task") + ".\n");
        }
    }

    private void addToList(Task task) {
        taskList.add(task);
        System.out.println("I have added this task for you:");
        System.out.println(task + "\n");
    }

    private void markAsDone(String index) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            Task taskToBeCompleted = taskList.get(taskIndex);
            if (taskToBeCompleted.isDone()) {
                System.out.println("The task is already done!");
            } else {
                taskToBeCompleted.setDone(true);
                System.out.println("You have completed the " + taskToBeCompleted.getClass().getName() + ":\n"
                        + taskToBeCompleted.getName() + "\n");
            }
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer! Please use done <number>");
        }
    }

    private void deleteTask(String index) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            Task taskToDelete = taskList.get(taskIndex);
            taskList.remove(taskToDelete);
            System.out.println("I have removed the task:\n" + taskToDelete + "\n");
            printList();
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer!");
        }
    }

    private void echo(String input) {
        System.out.println("You have said \"" + input + "\"\n");
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public void start() {
        this.greet();
        this.getInput();
    }
}
