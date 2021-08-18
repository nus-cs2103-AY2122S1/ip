import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        start();
    }

    private static void start() {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Task> list = new ArrayList<>();
            System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
            while (sc.hasNext()) {
                String s = sc.nextLine();
                if (s.equals("bye")) {
                    exit();
                    break;
                } else if (s.equals("list")) {
                    list(list);
                } else if (s.contains("done")) {
                    int num = getNum(s, list.size());
                    if (num != -1) {
                        done(list, num);
                    }
                } else if (s.contains("todo")) {
                    todo(s, list);
                } else if (s.contains("deadline")) {
                    deadline(s, list);
                } else if (s.contains("event")) {
                    event(s, list);
                } else if (s.contains("delete")) {
                    int num = getNum(s, list.size());
                    if (num != -1) {
                        delete(list, num);
                    }
                } else {
                    invalidEntry();
                }
            }
            sc.close();
        } catch (DukeException e) {
            e.printError();
        }
    }


//    private static void echo(String s) {
//        System.out.println("added: " + s);
//    }
    private static void invalidEntry() throws DukeInvalidException {
        try {
            throw new DukeInvalidException("error");
        } catch (DukeException e) {
            e.printError();
        }
    }

    private static void delete(ArrayList<Task> list, int num) {
        Task task = list.get(num - 1);
        list.remove(task);
        String t = list.size() == 1 ? "task" : "tasks";
        System.out.printf("Noted.I've removed this task:\n%s\nNow you have %d %s in the list\n",
                task, list.size(), t);
    }

    private static void todo(String s, ArrayList<Task> list) {
        try {
            String name = getName(s);
            if (!name.equals("")) {
                ToDo todo = new ToDo(name);
                list.add(todo);
                echo(todo, list.size());
            }
        } catch (DukeException e) {
            e.printError();
        }
    }

    private static void deadline(String s, ArrayList<Task> list) throws TaskNoDateTimeException, TaskNoNameException {
        try {
            String description = getName(s);
            if (!description.equals("") && !description.equals("/by")
                    && !(description.startsWith("/by") && description.endsWith(" "))
                    && !(description.startsWith(" ") && description.endsWith("/by"))) {
                if (description.contains("/by")) {
                    if(description.startsWith("/by")) {
                        throw new TaskNoNameException("error", "deadline");
                    } else if (description.endsWith("/by")) {
                        throw new TaskNoDateTimeException("error", "deadline");
                    } else {
                        String[] parts = description.split("/by");
                        if (parts[0].equals(" ")) {
                            throw new TaskNoNameException("error", "deadline");
                        } else if (parts[1].equals(" ")) {
                            throw new TaskNoDateTimeException("error", "deadline");
                        } else {
                            Deadline deadline = new Deadline(parts[0], parts[1]);
                            list.add(deadline);
                            echo(deadline, list.size());
                        }
                    }
                } else {
                    throw new TaskNoDateTimeException("error", "deadline");
                }
            } else {
                throw new DukeEmptyException("error", "deadline");
            }
        } catch (DukeException e) {
            e.printError();
        }
    }

    private static void event(String s, ArrayList<Task> list) throws TaskNoNameException, TaskNoDateTimeException {
        try {
            String description = getName(s);
            if (!description.equals("") && !description.equals("/at")
                    && !(description.startsWith("/at") && description.endsWith(" "))
                    && !(description.startsWith(" ") && description.endsWith("/at"))) {
                if (description.contains("/at")) {
                    if(description.startsWith("/at")) {
                        throw new TaskNoNameException("error", "event");
                    } else if (description.endsWith("/at")) {
                        throw new TaskNoDateTimeException("error", "event");
                    } else {
                        String[] parts = description.split("/at");
                        if (parts[0].equals(" ")) {
                            throw new TaskNoNameException("error", "event");
                        } else if (parts[1].equals(" ")) {
                            throw new TaskNoDateTimeException("error", "event");
                        } else {
                            Event event = new Event(parts[0], parts[1]);
                            list.add(event);
                            echo(event, list.size());
                        }
                    }
                } else {
                    throw new TaskNoDateTimeException("error", "event");
                }
            } else {
                throw new DukeEmptyException("error", "event");
            }
        } catch (DukeException e) {
            e.printError();
        }
    }

    private static void echo(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d %s in the list.\n",
                task.toString(), size, t);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task s = list.get(i);
            System.out.printf("%d.%s%n", i + 1, s);
        }
    }

    private static void done(ArrayList<Task> list, int num) {
        Task task = list.get(num - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    private static int getNum(String s, int size) throws DoneEmptyException, TaskDoesNotExistException {
        try {
            if ((!s.equals("done") && s.contains(" ")) || (!s.equals("delete") && s.contains(" "))) {
                String[] parts = s.split(" ", 2);
                if (parts[0].equals("done") || parts[0].equals("delete")) {
                    try {
                        int num = Integer.parseInt(parts[1]);
                        if (num <= 0 || num > size) {
                            throw new TaskDoesNotExistException("error");
                        } else {
                            return num;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("( ⚆ _ ⚆ ) OOPS!!! Please enter a valid task number!");
                        return -1;
                    }
                }
                return -1;
            } else {
                throw new DoneEmptyException("error");
            }
        } catch (DukeException e) {
            e.printError();
        }
        return -1;
    }

    private static String getName(String s) throws DukeEmptyException {
        if (s.contains(" ")) {
            String[] parts = s.split(" ", 2);
            if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
                if (parts[1].equals("")) {
                    throw new DukeEmptyException("error", parts[0]);
                }
                return parts[1];
            }
        } else {
            throw new DukeEmptyException("error", s);
        }
        return "";
    }
}
