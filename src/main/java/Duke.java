import java.util.*;

public class Duke {
    private ArrayList<Task> taskList;
    private int numTask;

    public Duke() {
        taskList = new ArrayList<Task>();
        numTask = 0;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc= new Scanner(System.in);
        while(true) {
            String userInput = sc.nextLine();
            duke.messageHandle(userInput);
        }
    }

    private void sendMessage(String text) {
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + text + end;
        System.out.println(output);
    }

    /**
     * Handle userInput base on the command given.
     * Split userInput to specific component to allow input into methods.
     * @param userInput the String entered by the user
     */
    public void messageHandle(String userInput) {
        String command = userInput.split(" ")[0];
        switch(command) {
            case "exit":
                this.exit();
                break;

            case "list":
                this.list();
                break;

            case "done":
                try {
                    this.done(userInput);
                } catch (IndexOutOfBoundsException e) {
                    String invalidNumber = "OOPS!!! Please enter a valid task number.";
                    this.sendMessage(invalidNumber);
                }
                break;

            case "todo":
                try {
                    String taskDescription = userInput.split("todo ")[1];
                    Todo newTodo = new Todo(taskDescription);
                    this.addTask(newTodo);
                } catch (IndexOutOfBoundsException e) {
                    String emptyDescription = "OOPS!!! The description of a todo cannot be empty.";
                    this.sendMessage(emptyDescription);
                }
                break;

            case "deadline":
                try {
                    String[] splitString = userInput.split("deadline |/by");
                    String taskDescription = splitString[1];
                    String by = splitString[2];
                    Deadline newDeadline = new Deadline(taskDescription, by);
                    this.addTask(newDeadline);
                } catch (IndexOutOfBoundsException e) {
                    String emptyDescription = "OOPS!!! The description/by of a deadline cannot be empty.";
                    this.sendMessage(emptyDescription);
                }
                break;

            case "event":
                try {
                    String[] splitString = userInput.split("event |/at");
                    String taskDescription = splitString[1];
                    String at = splitString[2];
                    Event newEvent = new Event(taskDescription, at);
                    this.addTask(newEvent);
                } catch (IndexOutOfBoundsException e) {
                    String emptyDescription = "OOPS!!! The description/at of an event cannot be empty.";
                    this.sendMessage(emptyDescription);
                }
                break;

            case "delete":
                try {
                    this.delete(userInput);
                } catch (IndexOutOfBoundsException e) {
                    String invalidNumber = "OOPS!!! Please enter a valid task number.";
                    this.sendMessage(invalidNumber);
                }
                break;

            default:
                String lost = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                this.sendMessage(lost);
                break;
        }
    }

    private void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private void echo(String args) {
        this.sendMessage(args);
    }

    private void exit() {
        String bye = "Bye. Hope to see you again soon!";
        this.sendMessage(bye);
        System.exit(0);
    }

    private void add(String taskDescription) {
        Task newTask = new Task(taskDescription);
        this.taskList.add(newTask);
        numTask = numTask + 1;
        String output = "added: " + newTask.getDescription();
        this.sendMessage(output);
    }

    private void addTask(Task task) {
        this.taskList.add(task);
        numTask = numTask + 1;
        String addTask = String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(), numTask);
        this.sendMessage(addTask);
    }

    private void list() {
        String task;
        if (numTask == 0) {
            task = "";
        } else {
            task = "1. " + taskList.get(0).toString();
            for(int taskNumber = 2; taskNumber <= numTask; taskNumber++) {
                task = task + "\n" + taskNumber + ". " + taskList.get(taskNumber - 1).toString();
            }
        }
        String listStatement = "Here are the tasks in your list:\n";
        String output = listStatement + task;
        this.sendMessage(output);
    }

    private void done(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.markDone();
        String markDone = "Nice! I've marked this task as done:\n";
        String taskStatus = currentTask.toString();
        String output = markDone + taskStatus;
        this.sendMessage(output);
    }

    private void delete(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        Task deleteTask = taskList.get(taskNumber - 1);
        String taskStatus = deleteTask.toString();
        taskList.remove(taskNumber - 1);
        numTask = numTask - 1;
        String delete = String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.",
                taskStatus, numTask) ;
       this.sendMessage(delete);
    }
}
