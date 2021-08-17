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
            cmd = sc.next();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                System.out.print(taskList);
            } else if (cmd.equals("done")) {
                int taskNo = Integer.parseInt(sc.nextLine().trim());
                String response = taskList.markAsDone(taskNo)
                        ? "I've marked this task as done:\n  " + taskList.get(taskNo)
                        : "Task already done.";
                System.out.println(response);
            } else if (cmd.equals("todo")) {
                Task toDo = new ToDo(sc.nextLine());
                taskList.add(toDo);
                System.out.println("I've added:\n " + toDo);
            } else if (cmd.equals("deadline")) {
                String[] parse = sc.nextLine().split(" /by ");
                Task deadline = new Deadline(parse[0], parse[1]);
                taskList.add(deadline);
                System.out.println("I've added:\n " + deadline);
            } else if (cmd.equals("event")) {
                String[] parse = sc.nextLine().split(" /at ");
                Task event = new Event(parse[0], parse[1]);
                taskList.add(event);
                System.out.println("I've added:\n " + event);
            } else {
                System.out.println("Unknown command!");
                sc.nextLine();
            }
            System.out.println();
        }
    }
}
