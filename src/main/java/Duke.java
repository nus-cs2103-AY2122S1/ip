import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String format(String... inputs) {
        StringBuilder str = new StringBuilder();
        String line = "    ____________________________________________________________\n";
        String space = "     ";
        str.append(line);
        for (String s : inputs) {
            str.append(space).append(s).append("\n");
        }
        str.append(line);
        return str.toString();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List list = new List();
        String command;
        System.out.println(format("Hello! I'm Duke", "What can I do for you?"));
        command = sc.next();
        while (!command.equals("bye")) {
            Task task;
            switch(command) {
            case "list":
                System.out.println(format(list.returnItems()));
                break;
            case "done":
                if (sc.hasNextInt()) {
                    int index = sc.nextInt();
                    try {
                        System.out.println(format("Nice! I've marked this task as done:",
                                "  " + list.markDone(index)));
                    } catch (DukeException e) {
                        System.err.println(format(e.toString()));
                    }
                } else {
                    System.err.println(format("☹ OOPS!!! The index of a task to be marked done must be specified."));
                }
                break;
            case "delete":
                if (sc.hasNextInt()) {
                    int index = sc.nextInt();
                    try {
                        System.out.println(format("Noted. I've removed this task:",
                                "  " + list.removeTask(index), list.returnItemCount()));
                    } catch (DukeException e) {
                        System.err.println(format(e.toString()));
                    }
                } else {
                    System.err.println(format("☹ OOPS!!! The index of a task to be marked done must be specified."));
                }
                break;
            case "todo":
                try {
                    command = sc.nextLine();
                    task = new Todo(command);
                    list.addItem(task);
                    System.out.println(format("Got it. I've added this task:",
                            "  " + task, list.returnItemCount()));
                } catch (DukeException e) {
                    System.err.println(format(e.toString()));
                }
                break;
            case "deadline":
                command = sc.nextLine();
                String[] deadlineEntry = command.split(" /by ");
                if (deadlineEntry.length != 2) {
                    System.err.println(format("☹ OOPS!!! Usage of deadline does not match"
                            + "'description' /by 'deadline'"));
                } else {
                    try {
                        task = new Deadline(deadlineEntry[0], deadlineEntry[1]);
                        list.addItem(task);
                        System.out.println(format("Got it. I've added this task:",
                                "  " + task, list.returnItemCount()));
                    } catch (DukeException e) {
                        System.err.println(format(e.toString()));
                    } catch (DateTimeParseException e) {
                        System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                    }
                }
                break;
            case "event":
                command = sc.nextLine();
                String[] eventEntry = command.split(" /at ");
                if (eventEntry.length != 2) {
                    System.err.println(format("☹ OOPS!!! Usage of event does not match"
                            + "'description' /at 'timeframe'"));

                } else {
                    try {
                        task = new Event(eventEntry[0], eventEntry[1]);
                        list.addItem(task);
                        System.out.println(format("Got it. I've added this task:", "  " + task,
                                list.returnItemCount()));
                    } catch (DukeException e) {
                        System.err.println(format(e.toString()));
                    } catch (DateTimeParseException e) {
                        System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                    }
                }
                break;
            case "by":
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(sc.nextLine().trim());
                    ArrayList<DateTimeable> dtarr = list.getEventsBy(dateTime);
                    String[] strarr = new String[dtarr.size() + 1];
                    strarr[0] = "Here are the Events happening before "
                            + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
                    int count = 1;
                    for (DateTimeable dt : dtarr) {
                        strarr[count++] = dt.toString();
                    }
                    System.out.println(format(strarr));
                } catch (DateTimeParseException e) {
                    System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                }
                break;
            case "at":
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(sc.nextLine().trim());
                    ArrayList<DateTimeable> dtarr = list.getEventsAt(dateTime);
                    String[] strarr = new String[dtarr.size() + 1];
                    strarr[0] = "Here are the Deadlines to be completed by "
                            + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
                    int count = 1;
                    for (DateTimeable dt : dtarr) {
                        strarr[count++] = dt.toString();
                    }
                    System.out.println(format(strarr));
                } catch (DateTimeParseException e) {
                    System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                }
                break;
            case "all":
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(sc.nextLine().trim());
                    ArrayList<DateTimeable> dtarr = list.getEventsAll(dateTime);
                    String[] strarr = new String[dtarr.size() + 1];
                    strarr[0] = "Here are the timed tasks occurring before "
                            + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
                    int count = 1;
                    for (DateTimeable dt : dtarr) {
                        strarr[count++] = dt.toString();
                    }
                    System.out.println(format(strarr));
                } catch (DateTimeParseException e) {
                    System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                }
                break;
            default:
                System.err.println(format("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                break;
            }
            if (sc.hasNext()) {
                command = sc.next();
            } else {
                return;
            }
        }
        System.out.println(format("Bye. Hope to see you again soon!"));
    }
}