import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public enum Keyword {
        todo, list, deadline, event, done
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        greeting();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            String[] tokens = cmd.split(" ");
            try {
                Keyword keyword = validifyCommand(tokens);
                int middle;
                int index;
                String description;
                String at;
                String by;
                switch (keyword) {
                    case list:
                        iterList(list);
                        break;
                    case todo:
                        addToList(list, new Todo(cmd.substring(5)));
                        break;
                    case deadline:
                        middle = Arrays.asList(tokens).indexOf("/by");
                        if (middle == -1) {
                            throw new DukeException("Missing deadline.");
                        }
                        description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
                        by = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
                        addToList(list, new Deadline(description, by));
                        break;
                    case event:
                        middle = Arrays.asList(tokens).indexOf("/at");
                        if (middle == -1) {
                            throw new DukeException("Missing time of event.");
                        }
                        description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
                        at = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
                        addToList(list, new Event(description, at));
                        break;
                    case done:
                        index = Integer.parseInt(tokens[1]) - 1;
                        if (index < 0 || index > list.size() - 1) {
                            throw new DukeException("Hey! Invalid Task number given.");
                        }
                        completeATask(index, list);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            cmd = sc.nextLine();
        }
        sc.close();
        exit();
    }

    private static void greeting() {
        System.out.println("Aloha! I'm Sophia.\nWhat can I do for you?");
    }

    private static Keyword validifyCommand(String[] cmd) throws DukeException {
        Keyword keyword;
        try {
            keyword = Keyword.valueOf(cmd[0]);
            if ((keyword.equals(Keyword.todo) || keyword.equals(Keyword.deadline) || keyword.equals(Keyword.event))
                    && cmd.length < 2) {
                throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", keyword));
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return keyword;
    }

    private static void iterList(ArrayList<Task> ls) {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task s : ls) {
            System.out.println(i + "." + s);
            i++;
        }
    }

    private static void addToList(ArrayList<Task> ls, Task text) {
        ls.add(text);
        System.out.println("Sure thing! I've added this task: \n" + text);
        if (ls.size() > 1) {
            System.out.println(String.format("Now you have %d task in the list.", ls.size()));
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", ls.size()));
        }
    }

    private static void completeATask(int index, ArrayList<Task> ls) {
        Task task = ls.get(index);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
