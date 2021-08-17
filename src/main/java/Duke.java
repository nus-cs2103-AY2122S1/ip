import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<String> taskList;
    boolean[] isDone;

    public Duke() {
        taskList = new ArrayList<>(100);
        isDone = new boolean[100];
    }

    void startConversation() {
        Scanner sc = new Scanner(System.in);

        greeting("Alex");
        while (true) {
            // conversation loop
            String input = sc.nextLine();
            String[] strArr = input.split(" ");
            String cmd = strArr[0];
            if (cmd.equals("bye")) {
                break;
            }

            switch (cmd) {
            case "":
                break;
            case "list":
                listTask();
                break;
            case "done":
                markTaskDone(Integer.parseInt(strArr[1]));
                break;
            default:
                addTask(input);
            }
        }
        sayBye("Alex");
    }

    private void addTask(String task) {
        this.taskList.add(task);
        printHorizLine();
        System.out.println("\tadded " + task);
        printHorizLine();
    }

    private void listTask() {
        printHorizLine();
        for (int i = 1; i <= this.taskList.size(); i++) {
            if (!isDone[i-1]) {
                System.out.format("\t%d.[ ] %s\n", i, taskList.get(i-1));
            } else {
                System.out.format("\t%d.[X] %s\n", i, taskList.get(i-1));
            }

        }
        printHorizLine();
    }

    private void markTaskDone(int idx) {
        printHorizLine();
        this.isDone[idx - 1] = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[X] " + this.taskList.get(idx-1));
        printHorizLine();
    }

    private void greeting(String name) {
        printHorizLine();
        System.out.println("\tHello " + name + "!");
        System.out.println("\tI'm Duke");
        printHorizLine();
    }

    private void sayBye(String name) {
        printHorizLine();
        System.out.println("\tBye " + name + ", hope to see you soon!");
        printHorizLine();
    }

    private void printHorizLine() {
        System.out.println("\t————————————————————————————————————————");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.startConversation();
    }
}
