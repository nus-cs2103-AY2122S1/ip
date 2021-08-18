import java.util.*;

public class Duke {
    public static void main(String[] args) {
        displayLogo();
        greet();
        addToList();
    }

    public static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(str);
        }
    }

    public static void addToList() {
        Scanner sc = new Scanner(System.in);
        List<Task> strList = new ArrayList<Task>();
        while(true) {
            try {
                String str = sc.nextLine();

                if (str.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (str.equals("list")) {
                    detectList(str, strList);
                } else if (str.startsWith("done")) {
                    detectDone(str, strList);
                } else if (str.startsWith("todo")) {
                    detectTodo(str, strList);
                } else if (str.startsWith("deadline")) {
                    detectDeadline(str, strList);
                } else if (str.startsWith("event")) {
                    detectEvent(str, strList);
                } else if (str.startsWith("delete")) {
                    detectDelete(str, strList);
                } else {
                    throw new DukeUnknownException("OOPS!!! I'm sorry, but I don't know what that means.");
                }
            } catch (DukeUnknownException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeDoneException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeTodoException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeDeadlineException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeEventException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeDeleteException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    public static void detectList(String str, List<Task> strList) {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task listItem : strList) {
            System.out.println(count + "."
                    + listItem.toString());
            count++;
        }
    }

    public static void detectDone(String str, List<Task> strList) throws DukeDoneException {
        try {
            int doneTaskIndex = Integer.parseInt(str.substring(5)) - 1;
            Task doneTask = strList.get(doneTaskIndex);
            doneTask.setDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + doneTask.toString());
        } catch (Exception e) {
            throw new DukeDoneException("OOPS!!! The index of a done cannot be empty.");
        }
    }

    public static void detectTodo(String str, List<Task> strList) throws DukeTodoException {
        if (str.length() > 5) {
            Todo newTodo = new Todo(str.replaceFirst("todo", ""));
            strList.add(newTodo);
            int numOfTasks = strList.size();
            System.out.println("Got it. I've added this task:\n"
                    + newTodo.toString() + "\n"
                    + "Now you have " + numOfTasks
                    + " tasks in the list.");
        } else {
            throw new DukeTodoException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void detectDeadline(String str, List<Task> strList) throws DukeDeadlineException {
        try {
            String desc = str.split("/")[0]
                    .replaceFirst("deadline", "");
            String by = str.split("/by")[1];
            Deadline newDeadline = new Deadline(desc, by);
            strList.add(newDeadline);
            int numOfTasks = strList.size();
            System.out.println("Got it. I've added this task:\n"
                    + newDeadline.toString() + "\n"
                    + "Now you have " + numOfTasks
                    + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeDeadlineException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void detectEvent(String str, List<Task> strList) throws DukeEventException {
        try {
            String desc = str.split("/")[0]
                    .replaceFirst("event", "");
            String startEnd = str.split("/at")[1];
            Event newEvent = new Event(desc, startEnd);
            strList.add(newEvent);
            int numOfTasks = strList.size();
            System.out.println("Got it. I've added this task:\n"
                    + newEvent.toString() + "\n"
                    + "Now you have " + numOfTasks
                    + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeEventException("OOPS!!! The description of a event cannot be empty.");
        }
    }

    public static void detectDelete(String str, List<Task> strList) throws DukeDeleteException {
        try {
            int deleteTaskIndex = Integer.parseInt(str.substring(7)) - 1;
            Task deleteTask = strList.get(deleteTaskIndex);
            strList.remove(deleteTaskIndex);
            int numOfTasks = strList.size();
            System.out.println("Noted. I've removed this task:\n"
                    + deleteTask.toString() + "\n"
                    + "Now you have " + numOfTasks
                    + " tasks in the list.") ;
        } catch (Exception e) {
            throw new DukeDeleteException("OOPS!!! The index of a delete cannot be empty.");
        }
    }

}
