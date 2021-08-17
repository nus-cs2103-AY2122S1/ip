import java.util.Scanner;

class DukeBot {
    private final Scanner sc;
    private final TaskList taskList;

    public DukeBot(Scanner sc) {
        this.sc = sc;
        this.taskList = new TaskList();
    }

    public void start() {
        String greetings = "Hello! What can I do for you?\n";
        System.out.println(greetings);

        String cmd;
        while (true) {
            cmd = sc.nextLine();
            String[] parts = cmd.split(" ");
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                System.out.print(taskList);
            } else if (parts[0].equals("done")) {
                int taskNo = Integer.parseInt(parts[1]);
                String response = taskList.markAsDone(taskNo)
                        ? "I've marked this task as done:\n  " + taskList.get(taskNo)
                        : "Task already done.";
                System.out.println(response);
            } else {
                taskList.add(new Task(cmd));
                System.out.println("Added: " + cmd);
            }
            System.out.println();
        }
    }
}
