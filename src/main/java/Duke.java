import java.util.Scanner;

public class Duke {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List list = new List();
        String command;
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        command = sc.next();
        while (!command.equals("bye")) {
            Task task;
            switch(command) {
            case "list":
                System.out.println(line + list.returnItems() + line);
                break;
            case "done":
                if (sc.hasNextInt()) {
                    int index = sc.nextInt();
                    if (index > list.getSize() || index < 1) {
                        System.err.println(line + "     ☹ OOPS!!! The index of the task is out of range.\n"
                                + line);
                    } else {
                        System.out.println(line + "     Nice! I've marked this task as done:\n"
                                + "       " + list.markDone(index) + "\n" + line);
                    }
                } else {
                    System.err.println(line
                            + "     ☹ OOPS!!! The index of a task to be marked done must be specified.\n"
                            + line);
                }
                break;
            case "delete":
                if (sc.hasNextInt()) {
                    int index = sc.nextInt();
                    if (index > list.getSize() || index < 1) {
                        System.err.println(line + "     ☹ OOPS!!! The index of the task is out of range.\n"
                                + line);
                    } else {
                        System.out.println(line + "     Noted. I've removed this task:\n"
                                + "       " + list.removeTask(index) + "\n"
                                + list.returnItemCount() + "\n" + line);
                    }
                } else {
                    System.err.println(line
                            + "     ☹ OOPS!!! The index of a task to be marked done must be specified.\n"
                            + line);
                }
                break;
            case "todo":
                try {
                    command = sc.nextLine();
                    task = new Todo(command);
                    list.addItem(task);
                    System.out.println(line + "     Got it. I've added this task:\n"
                            + "       " + task + "\n" + list.returnItemCount() + "\n" + line);
                } catch (DukeException e) {
                    System.err.println(line + "     " + e + "\n" + line);
                } finally {
                    break;
                }
            case "deadline":
                try {
                    command = sc.nextLine();
                    String[] deadlineEntry = command.split(" /by ");
                    if (deadlineEntry.length != 2) {
                        System.err.println(line
                                + "     ☹ OOPS!!! Usage of deadline does not match 'description' /by 'deadline'\n"
                                + line);
                    } else {
                        task = new Deadline(deadlineEntry[0], deadlineEntry[1]);
                        list.addItem(task);
                        System.out.println(line + "     Got it. I've added this task:\n"
                                + "       " + task + "\n" + list.returnItemCount() + "\n" + line);
                    }
                } catch (DukeException e) {
                    System.err.println(line + "     " + e + "\n" + line);
                } finally {
                    break;
                }
            case "event":
                try {
                    command = sc.nextLine();
                    String[] eventEntry = command.split(" /at ");
                    if (eventEntry.length != 2) {
                        System.err.println(line
                                + "     ☹ OOPS!!! Usage of event does not match 'description' /at 'timeframe'\n"
                                + line);
                    } else {
                        task = new Event(eventEntry[0], eventEntry[1]);
                        list.addItem(task);
                        System.out.println(line + "     Got it. I've added this task:\n"
                                + "       " + task + "\n" + list.returnItemCount() + "\n" + line);
                    }
                } catch (DukeException e) {
                    System.err.println(line + "     " + e + "\n" + line);
                } finally {
                    break;
                }
            default:
                System.err.println(line + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + line);
                break;
            }
            if (sc.hasNext()) {
                command = sc.next();
            } else {
                return;
            }
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}