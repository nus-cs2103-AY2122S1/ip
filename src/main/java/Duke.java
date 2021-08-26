import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static void drawLine() {
        System.out.println("___________________________________________");
    }

    public static void main(String[] args) throws IOException {
        String s;
        int taskNumber;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Storage storage = new Storage();
        TaskList list = new TaskList(storage.load(storage.getFilePath()));
        //List<Task> list = storage.load(storage.getFilePath());

        System.out.println(logo);
        drawLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        drawLine();

        Scanner input = new Scanner(System.in);

        while(true) {
            try {
                s = input.nextLine();

                if (s.startsWith("bye")) {
                    drawLine();
                    storage.write(list.getTaskList(), storage.getFilePath());
                    System.out.println("\tBye. Hope to see you again soon!");
                    drawLine();
                    break;
                } else if (s.equals("list")) {
                    drawLine();
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < list.length(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + list.getTask(i).toString());
                    }
                    drawLine();
                    continue;
                } else if (s.startsWith("done")) {
                    if (s.length() == 4 || s.length() == 5) {
                        throw new DukeException("☹ OOPS!!! You did not put which task you want me to mark it complete.");
                    }
                    drawLine();
                    System.out.println("\tNice! I've marked this task as done:");
                    taskNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1)) - 1;
                    list.getTask(taskNumber).markAsDone();
                    System.out.println("\t\t[" + list.getTask(taskNumber).getStatusIcon() + "] "
                            + list.getTask(taskNumber).getDescription());
                    drawLine();
                    continue;
                } else if (s.startsWith("todo")) {
                    if (s.length() == 4 || s.length() == 5) {
                        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(s.substring(s.indexOf(" ") + 1));
                    list.addTask(todo);
                    drawLine();
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + todo);
                    System.out.println("\tNow you have " + list.length() + " tasks in the list.");
                    drawLine();
                } else if (s.startsWith("deadline")) {
                    Deadline deadline = new Deadline(s.substring(s.indexOf(" ") + 1, s.indexOf(" /by")),
                            s.substring(s.indexOf("/by") + 4));
                    list.addTask(deadline);
                    drawLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + deadline);
                    System.out.println("Now you have " + list.length() + " tasks in the list.");
                    drawLine();
                } else if (s.startsWith("event")) {
                    Event event = new Event(s.substring(s.indexOf(" ") + 1, s.indexOf(" /at")),
                            s.substring(s.indexOf("/at") + 4));
                    list.addTask(event);
                    drawLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event);
                    System.out.println("Now you have " + list.length() + " tasks in the list.");
                    drawLine();
                } else if (s.startsWith("delete")) {
                    if (s.length() == 6 || s.length() == 7) {
                        throw new DukeException("\t☹ OOPS!!! You did not put which task you want me to delete.");
                    }
                    drawLine();
                    System.out.println("\tNoted. I've removed this task:");
                    taskNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1)) - 1;
                    System.out.println("\t\t" + list.getTask(taskNumber));
                    list.deleteTask(taskNumber);
                    System.out.println("\tNow you have " + list.length() + " in the list.");
                    drawLine();
                    continue;
                } else {
                    throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                drawLine();
                System.out.println(e.getMessage());
                drawLine();
            }
        }
        input.close();
    }
}
