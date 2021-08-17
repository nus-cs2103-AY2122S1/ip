import java.util.*;
public class Duke {
    private String[] taskList;
    private int numTask;

    public Duke() {
        taskList = new String[100];
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
        if (userInput.equals("exit")) {
            this.exit();
        } else if (userInput.equals("list")) {
            this.list();
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

    private void add(String args) {
        this.taskList[numTask] = args;
        numTask = numTask + 1;
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + "added: " + args + end;
        System.out.println(output);
    }

    private void list() {
        String task;
        if (numTask == 0) {
            task = " ";
        } else {
            task = "1. " + taskList[0];
            for(int taskNumber = 2; taskNumber <= numTask; taskNumber++) {
                task = task + "\n" + taskNumber + ". " + taskList[taskNumber - 1];
            }
        }
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + task + end;
        System.out.println(output);
    }
}
