package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class.
 * Uses storage class to load existing data.
 * If there is not existing file, it will create one.
 * It takes in user input and create the task object.
 * It will save the final list into the file when programmes end.
 */
public class Duke {
    static void drawLine() {
        System.out.println("___________________________________________");
    }

    public static void main(String[] args) throws IOException {
        String s;
        int taskNumber;

        Storage storage = new Storage();
        TaskList list = new TaskList(storage.load(storage.getFilePath()));
        Ui ui = new Ui(storage, list);

        ui.greet();
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                s = input.nextLine();

                if (s.startsWith("bye")) {
                    ui.goodbye();
                    break;
                } else if (s.equals("list")) {
                    ui.listTasks();
                    continue;
                } else if (s.startsWith("done")) {
                    if (s.length() == 4 || s.length() == 5) {
                        throw new DukeException("☹ OOPS!!! You did not put which task you want me to mark it complete.");
                    }
                    taskNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1)) - 1;
                    ui.markDone(taskNumber);
                    continue;
                } else if (s.startsWith("todo")) {
                    if (s.length() == 4 || s.length() == 5) {
                        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo todo = new Todo(s.substring(s.indexOf(" ") + 1));
                    ui.add(todo);
                } else if (s.startsWith("deadline")) {
                    Deadline deadline = new Deadline(s.substring(s.indexOf(" ") + 1, s.indexOf(" /by")),
                            s.substring(s.indexOf("/by") + 4));
                    ui.add(deadline);
                } else if (s.startsWith("event")) {
                    Event event = new Event(s.substring(s.indexOf(" ") + 1, s.indexOf(" /at")),
                            s.substring(s.indexOf("/at") + 4));
                    ui.add(event);
                } else if (s.startsWith("delete")) {
                    if (s.length() == 6 || s.length() == 7) {
                        throw new DukeException("\t☹ OOPS!!! You did not put which task you want me to delete.");
                    }
                    taskNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1)) - 1;
                    ui.delete(taskNumber);
                    continue;
                } else if (s.startsWith("find")) {
                    if (s.length() == 4 || s.length() == 5) {
                        throw new DukeException("\t☹ OOPS!!! You did not put which task you want me to find.");
                    }
                    ui.findTasks(s.substring(s.indexOf(" ") + 1));
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
