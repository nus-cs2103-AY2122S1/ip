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
            this.done(userInput);
        } else {
            this.add(userInput);
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

    private void list() {
        String task;
        if (numTask == 0) {
            task = " ";
        } else {
            task = "1. " + taskList.get(0).getFullDescription();
            for(int taskNumber = 2; taskNumber <= numTask; taskNumber++) {
                task = task + "\n" + taskNumber + ". " + taskList.get(taskNumber - 1).getFullDescription();
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
        String taskStatus = currentTask.getFullDescription();
        String output = start + markDone + taskStatus + end;
        System.out.println(output);
    }
}
