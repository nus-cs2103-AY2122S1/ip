import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>(100);
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

            String[] parsed = parseInput(strArr);
            String content = parsed[0];
            String time= parsed[1];

            switch (cmd) {
            case "":
                break;
            case "list":
                listTask();
                break;
            case "done":
                markTaskDone(Integer.parseInt(strArr[1]));
                break;
            case "todo":
                addTask(new Task(content));
                break;
            case "deadline":
                addTask(new Deadline(content, time));
                break;
            case "event":
                addTask(new Event(content, time));
                break;
            }
        }
        sayBye("Alex");
    }

    private void addTask(Task task) {
        this.taskList.add(task);
        printHorizLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        printHorizLine();
    }

    private void listTask() {
        printHorizLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.taskList.size(); i++) {
            System.out.format("%d.%s\n", i, taskList.get(i-1));
        }
        printHorizLine();
    }

    private void markTaskDone(int idx) {
        printHorizLine();
        Task curr = this.taskList.get(idx - 1);
        curr.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + curr);
        printHorizLine();
    }

    private String[] parseInput(String[] strArr) {
        StringBuilder contentSb = new StringBuilder();
        StringBuilder timeSb = new StringBuilder();
        int i=1;
        for(; i < strArr.length; i++) {
            String curr = strArr[i];
            if(!curr.equals("/by") && !curr.equals("/at")) {
                contentSb.append(curr + " ");
            } else {
                i++;
                break;
            }
        }
        for(; i < strArr.length-1; i++) {
            timeSb.append(strArr[i] + " ");
        }
        timeSb.append(strArr[strArr.length-1]);

        return new String[]{contentSb.toString(),timeSb.toString()};
    }

    private void greeting(String name) {
        printHorizLine();
        System.out.println("Hello " + name + "!");
        System.out.println("I'm Duke");
        printHorizLine();
    }

    private void sayBye(String name) {
        printHorizLine();
        System.out.println("Bye " + name + ", hope to see you soon!");
        printHorizLine();
    }

    private void printHorizLine() {
        System.out.println("————————————————————————————————————————");
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
