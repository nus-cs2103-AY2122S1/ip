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
                    int count = 1;
                    System.out.println("Here are the tasks in your list:");
                    for (Task listItem : strList) {
                        System.out.println(count + "."
                                + listItem.toString());
                        count++;
                    }
                } else if (str.startsWith("done")) {
                    try {
                        int doneTaskIndex = Integer.parseInt(str.substring(5)) - 1;
                        Task doneTask = strList.get(doneTaskIndex);
                        doneTask.setDone();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + "[" + doneTask.getStatusIcon() + "] "
                                + doneTask.getDescription());
                    } catch (Exception e) {
                        throw new DukeDoneException();
                    }
                } else if (str.startsWith("todo")) {
                    if (str.length() > 5) {
                        Todo newTodo = new Todo(str.replaceFirst("todo", ""));
                        strList.add(newTodo);
                        int numOfTasks = strList.size();
                        System.out.println("Got it. I've added this task:\n"
                                + newTodo.toString() + "\n"
                                + "Now you have " + numOfTasks
                                + " tasks in the list.");
                    } else {
                        throw new DukeTodoException();
                    }
                } else if (str.startsWith("deadline")) {
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
                        throw new DukeDeadlineException();
                    }
                } else if (str.startsWith("event")) {
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
                        throw new DukeEventException();
                    }
                } else if (str.startsWith("delete")) {
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
                        throw new DukeDeleteException();
                    }
                } else {
//                    strList.add(new Task(str));
//                    System.out.println("added: " + str);
                    throw new DukeUnknownException();
                }
            } catch (DukeUnknownException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
                continue;
            } catch (DukeDoneException e) {
                System.out.println("OOPS!!! The index of a DONE command cannot be empty.");
                continue;
            } catch (DukeTodoException e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                continue;
            } catch (DukeDeadlineException e) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                continue;
            } catch (DukeEventException e) {
                System.out.println("OOPS!!! The description of a event cannot be empty.");
                continue;
            } catch (DukeDeleteException e) {
                System.out.println("OOPS!!! The index of a DELETE command cannot be empty.");
                continue;
            }
        }
    }
}
