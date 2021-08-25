import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public enum Keyword {
        todo, list, deadline, event, done, delete
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        Storage storage = new Storage("data/duke.txt", list);
        storage.load();
        greeting();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            String[] tokens = cmd.split(" ");
            try {
                Keyword keyword = validifyCommand(tokens);
                int middle;
                int index;
                String description;
                String dateString;
                LocalDateTime at;
                LocalDateTime by;
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
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
                        dateString = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
                        try {
                            by = LocalDateTime.parse(dateString, format);
                            addToList(list, new Deadline(description, by));
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter a date in the following format: dd/MM/yyyy HHmm");
                        }
                        break;
                    case event:
                        middle = Arrays.asList(tokens).indexOf("/at");
                        if (middle == -1) {
                            throw new DukeException("Missing time of event.");
                        }
                        description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
                        dateString = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
                        try {
                            at = LocalDateTime.parse(dateString, format);
                            addToList(list, new Event(description, at));
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter a date in the following format: dd/MM/yyyy HHmm");
                        }
                        break;
                    case done:
                        index = Integer.parseInt(tokens[1]) - 1;
                        completeATask(index, list);
                        break;
                    case delete:
                        index = Integer.parseInt(tokens[1]) - 1;
                        deleteTask(index, list);
                }
                storage.save();
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
            if ((keyword.equals(Keyword.done) || keyword.equals(Keyword.delete)) && (cmd.length < 2)) {
                throw new DukeException("☹ OOPS!!! Missing task number");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return keyword;
    }

    private static void iterList(ArrayList<Task> ls) {
        System.out.println("Here are the tasks in your list:");
        if (ls.size() == 0) {
            System.out.println("---- No Tasks currently ----");
        }
        int i = 1;
        for (Task s : ls) {
            System.out.println(i + "." + s);
            i++;
        }
    }

    private static void addToList(ArrayList<Task> ls, Task text) {
        ls.add(text);
        System.out.println("Sure thing! I've added this task:\n  " + text);
        if (ls.size() < 1) {
            System.out.println(String.format("Now you have %d task in the list.", ls.size()));
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", ls.size()));
        }
    }

    private static void completeATask(int index, ArrayList<Task> ls) throws DukeException {
        if (index < 0 || index > ls.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = ls.get(index);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    private static void deleteTask(int index, ArrayList<Task> ls) throws DukeException {
        if (index < 0 || index > ls.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = ls.get(index);
        ls.remove(index);
        System.out.println("Noted! I've removed this task:\n" + task);
        if (ls.size() > 1) {
            System.out.println(String.format("Now you have %d task in the list.", ls.size()));
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", ls.size()));
        }
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
