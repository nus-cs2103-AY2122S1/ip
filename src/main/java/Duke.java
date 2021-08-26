import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        List<Task> list = storage.load(storage.getFilePath());

        System.out.println(list);

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
                    storage.write(list, storage.getFilePath());
                    System.out.println("\tBye. Hope to see you again soon!");
                    drawLine();
                    break;
                } else if (s.equals("list")) {
                    drawLine();
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + list.get(i).toString());
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
                    list.get(taskNumber).markAsDone();
                    System.out.println("\t\t[" + list.get(taskNumber).getStatusIcon() + "] "
                            + list.get(taskNumber).getDescription());
                    drawLine();
                    continue;
                } else if (s.startsWith("todo")) {
                    if (s.length() == 4 || s.length() == 5) {
                        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(s.substring(s.indexOf(" ") + 1));
                    list.add(todo);
                    drawLine();
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + todo);
                    System.out.println("\tNow you have " + list.size() + " tasks in the list.");
                    drawLine();
                } else if (s.startsWith("deadline")) {
                    Deadline deadline = new Deadline(s.substring(s.indexOf(" ") + 1, s.indexOf(" /by")),
                            s.substring(s.indexOf("/by") + 4));
                    list.add(deadline);
                    drawLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    drawLine();
                } else if (s.startsWith("event")) {
                    Event event = new Event(s.substring(s.indexOf(" ") + 1, s.indexOf(" /at")),
                            s.substring(s.indexOf("/at") + 4));
                    list.add(event);
                    drawLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    drawLine();
                } else if (s.startsWith("delete")) {
                    if (s.length() == 6 || s.length() == 7) {
                        throw new DukeException("\t☹ OOPS!!! You did not put which task you want me to delete.");
                    }
                    drawLine();
                    System.out.println("\tNoted. I've removed this task:");
                    taskNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1)) - 1;
                    System.out.println("\t\t" + list.get(taskNumber));
                    list.remove(taskNumber);
                    System.out.println("\tNow you have " + list.size() + " in the list.");
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
