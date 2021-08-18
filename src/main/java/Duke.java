import java.text.spi.NumberFormatProvider;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static ArrayList<Task> list = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        reply("Hello i is Duke\nWhat u want?");
        manageInput();
        scanner.close();
    }

    private static void manageInput() {
        if (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit();
            } else if (input.equals("list")) {
                displayList();
            } else {
                try {
                    if (input.contains("done")) {
                        done(input);
                    } else if (input.contains("todo")) {
                        todo(input);
                    } else if (input.contains("deadline")) {
                        deadline(input);
                    } else if (input.contains("event")) {
                        event(input);
                    } else if (input.contains("delete")) {
                        delete(input);
                    } else {
                        reply("Can type properly pls?");
                        manageInput();
                    }
                } catch(DukeException err) {
                    reply(err.getMessage());
                    manageInput();
                }
            }
        }
    }

    private static void reply(String content) {
        System.out.println("________________________________");
        System.out.println(content);
        System.out.println("________________________________");
    }

    private static void exit() {
        reply("i zao first");
    }

//    private static void echo(String input) {
//        reply(input);
//        manageInput();
//    }

    private static void add(Task task) {
        list.add(task);
        reply("one more thing: " + task.toString() + "\nNow you got " + list.size() + " thing(s). sian");
    }

    private static void displayList() {
        System.out.println("________________________________");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
        System.out.println("________________________________");
        manageInput();
    }

    private static void done(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(5));
            Task currTask = list.get(taskNumber - 1);
            currTask.markAsDone();
            reply("noice this thing done:\n" + currTask);
            manageInput();
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("can type properly? liddis: 'done taskNumber'");
        } catch(NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch(ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }

    private static void todo(String input) throws DukeException {
        try {
            Todo todo = new Todo(input.substring(5));
            add(todo);
            manageInput();
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("u never say what to do?");
        }
    }

    private static void deadline(String input) throws DukeException {
        try {
            int split = input.indexOf("/");
            Deadline deadline = new Deadline(input.substring(9, split - 1), input.substring(split + 3));
            add(deadline);
            manageInput();
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("this one by when ah? can do it liddis or not: 'deadline task /by when'");
        }
    }

    private static void event(String input) throws DukeException {
        try {
            int split = input.indexOf("/");
            Event event = new Event(input.substring(6, split - 1), input.substring(split + 3));
            add(event);
            manageInput();
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("this one when ah? can do it liddis or not: 'event task /at when'");
        }
    }

    private static void delete(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            reply("this one no more liao ah :\n" + list.get(taskNumber).toString()
                + "\nNow you got " + (list.size() - 1) + " thing(s). sian");
            list.remove(taskNumber);
            manageInput();
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("what u wan delete?");
        } catch(NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch(ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }
}
