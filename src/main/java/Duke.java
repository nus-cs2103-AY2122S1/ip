import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        File f = new File("duke.txt");
        List list = new List();
        Scanner s = null;
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                String[] taskElements = s.nextLine().split("[|]");
                Task t = null;
                switch(taskElements[0]) {
                case "T":
                    try {
                        t = new Todo(taskElements[2]);
                    } catch (DukeException e) {
                        System.err.println(format("Task description cannot be found in database"));
                    }
                    break;
                case "E":
                    try {
                        t = new Event(taskElements[2], taskElements[3]);
                    } catch (DukeException e) {
                        System.err.println(format("Task description cannot be found in database"));
                    }
                    break;
                case "D":
                    try {
                        t = new Deadline(taskElements[2], taskElements[3]);
                    } catch (DukeException e) {
                        System.err.println(format("Task description cannot be found in database"));
                    }
                    break;
                }
                if (t != null) {
                    if (taskElements[1].equals("X")) {
                        t.markDone();
                    }
                    list.addItem(t);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
                System.out.println(format("No previous tasks found, a new task list will be created"));
            } catch (IOException e2) {
                System.err.println(format("Unable to create new task list", e2.toString()));
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        Scanner sc = new Scanner(System.in);
        String command;
        System.out.println(format("Hello! I'm Duke", "What can I do for you?"));
        command = sc.next();
        while (!command.equals("bye")) {
            boolean isModified = false;
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
                isModified = true;
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
                isModified = true;
                break;
            case "todo":
                try {
                    command = sc.nextLine();
                    task = new Todo(command.trim());
                    list.addItem(task);
                    System.out.println(format("Got it. I've added this task:",
                            "  " + task, list.returnItemCount()));
                } catch (DukeException e) {
                    System.err.println(format(e.toString()));
                }
                isModified = true;
                break;
            case "deadline":
                command = sc.nextLine();
                String[] deadlineEntry = command.split(" /by ");
                if (deadlineEntry.length != 2) {
                    System.err.println(format("☹ OOPS!!! Usage of deadline does not match"
                            + "'description' /by 'deadline'"));
                } else {
                    try {
                        task = new Deadline(deadlineEntry[0].trim(), deadlineEntry[1]);
                        list.addItem(task);
                        System.out.println(format("Got it. I've added this task:",
                                "  " + task, list.returnItemCount()));
                    } catch (DukeException e) {
                        System.err.println(format(e.toString()));
                    } catch (DateTimeParseException e) {
                        System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                    }
                }
                isModified = true;
                break;
            case "event":
                command = sc.nextLine();
                String[] eventEntry = command.split(" /at ");
                if (eventEntry.length != 2) {
                    System.err.println(format("☹ OOPS!!! Usage of event does not match"
                            + "'description' /at 'timeframe'"));
                } else {
                    try {
                        task = new Event(eventEntry[0].trim(), eventEntry[1]);
                        list.addItem(task);
                        System.out.println(format("Got it. I've added this task:", "  " + task,
                                list.returnItemCount()));
                    } catch (DukeException e) {
                        System.err.println(format(e.toString()));
                    } catch (DateTimeParseException e) {
                        System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
                    }
                }
                isModified = true;
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
            if (isModified) {
                try {
                    FileWriter fw = new FileWriter(f);
                    ArrayList<String> dataSet = list.getDataStorage();
                    for (int i = 0; i < dataSet.size() - 1; i++) {
                        fw.write(dataSet.get(i) + "\n");
                    }
                    fw.write(dataSet.get(dataSet.size() - 1));
                    fw.close();
                } catch (IOException e) {
                    System.err.println(format("Unable to modify saved file", e.toString()));
                }
            }
            if (sc.hasNext()) {
                command = sc.next();
            } else {
                break;
            }
        }
        System.out.println(format("Bye. Hope to see you again soon!"));
        sc.close();
    }
}