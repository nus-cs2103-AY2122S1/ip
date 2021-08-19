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

    private void messageHandle(String userInput) {
        String command = userInput.split(" ")[0];
        if (command.equals("exit")) {
            this.exit();
        } else if (command.equals("list")) {
            this.list();
        } else if (command.equals("done")) {
            try {
                this.done(userInput);
            } catch (IndexOutOfBoundsException e) {
                String start = "_____________________________________\n";
                String end = "\n_____________________________________";
                String invalidNumber = "☹ OOPS!!! Please enter a valid task number.";
                String output = start + invalidNumber + end;
                System.out.println(output);
            }

        } else if (command.equals("todo")) {
            try {
                String taskDescription = userInput.split("todo ")[1];
                Todo newTodo = new Todo(taskDescription);
                this.addTask(newTodo);
            } catch (IndexOutOfBoundsException e) {
                String start = "_____________________________________\n";
                String end = "\n_____________________________________";
                String emptyDescription = "☹ OOPS!!! The description of a todo cannot be empty.";
                String output = start + emptyDescription + end;
                System.out.println(output);
            }

        } else if (command.equals("deadline")) {
            try{
                String[] splitString = userInput.split("deadline |/by");
                String taskDescription = splitString[1];
                String by = splitString[2];
                Deadline newDeadline = new Deadline(taskDescription, by);
                this.addTask(newDeadline);
            } catch (IndexOutOfBoundsException e) {
                String start = "_____________________________________\n";
                String end = "\n_____________________________________";
                String emptyDescription = "☹ OOPS!!! The description/by of a deadline cannot be empty.";
                String output = start + emptyDescription + end;
                System.out.println(output);
            }

        } else if (command.equals("event")) {
            try {
                String[] splitString = userInput.split("event |/at");
                String taskDescription = splitString[1];
                String at = splitString[2];
                Event newEvent = new Event(taskDescription, at);
                this.addTask(newEvent);
            } catch (IndexOutOfBoundsException e) {
                String start = "_____________________________________\n";
                String end = "\n_____________________________________";
                String emptyDescription = "☹ OOPS!!! The description/at of an event cannot be empty.";
                String output = start + emptyDescription + end;
                System.out.println(output);
            }
        } else if (command.equals("delete")) {
            try {
                this.delete(userInput);
            } catch (IndexOutOfBoundsException e) {
                String start = "_____________________________________\n";
                String end = "\n_____________________________________";
                String invalidNumber = "☹ OOPS!!! Please enter a valid task number.";
                String output = start + invalidNumber + end;
                System.out.println(output);
            }
        } else {
            String start = "_____________________________________\n";
            String end = "\n_____________________________________";
            String lost = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            String output = start + lost + end;
            System.out.println(output);
        }
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private void echo(String args) {
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + args + end;
        System.out.println(output);
    }

    private void exit() {
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + "Bye. Hope to see you again soon!" + end;
        System.out.println(output);
        System.exit(0);
    }

    private void add(String taskDescription) {
        Task newTask = new Task(taskDescription);
        this.taskList.add(newTask);
        numTask = numTask + 1;
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + "added: " + newTask.getDescription() + end;
        System.out.println(output);
    }

    private void addTask(Task task) {
        this.taskList.add(task);
        numTask = numTask + 1;
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String addTask = String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(), numTask);
        String output = start + addTask + end;
        System.out.println(output);
    }

    private void list() {
        String task;
        if (numTask == 0) {
            task = " ";
        } else {
            task = "1. " + taskList.get(0).toString();
            for(int taskNumber = 2; taskNumber <= numTask; taskNumber++) {
                task = task + "\n" + taskNumber + ". " + taskList.get(taskNumber - 1).toString();
            }
        }
        String start = "_____________________________________\n";
        String listStatement = "Here are the tasks in your list:\n";
        String end = "\n_____________________________________";
        String output = start + listStatement + task + end;
        System.out.println(output);
    }

    private void done(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.markDone();
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String markDone = "Nice! I've marked this task as done:\n";
        String taskStatus = currentTask.toString();
        String output = start + markDone + taskStatus + end;
        System.out.println(output);
    }

    private void delete(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        Task deleteTask = taskList.get(taskNumber - 1);
        String taskStatus = deleteTask.toString();
        taskList.remove(taskNumber - 1);
        numTask = numTask - 1;
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String delete = String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.",
                taskStatus, numTask) ;
        String output = start + delete + end;
        System.out.println(output);
    }
}
