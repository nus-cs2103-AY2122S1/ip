import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String wrapOutput(String s) {
        // Align list items properly
        // Adapted regex from https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java

        String mstr = s.replaceAll("(?m)^", "\t\t\t ");
        return "\n\t@Herb:~$" + mstr.substring(3) + "\n";
    }

    private static void handleDone(ArrayList<Task> taskList, String[] in) throws DukeException {
        if (in.length != 2) {
            throw new InvalidFormatException("`done ${i}`");
        } else {
            int index;
            try {
                index = Integer.parseInt(in[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidIntegerException();
            }
            if (index < 1 || index > taskList.size()) {
                throw new InvalidTaskNumberException();
            } else {
                Task t = taskList.get(index - 1);
                t.markAsDone();
                System.out.println(wrapOutput("Nice, I've marked this task as done!\n   " +
                        t.toString()));
            }
        }
    }

    public static void main(String[] args) {
        String welcomeMessage = "\n\tHi! I'm Herbert, you can call me Herb  ٩(˘◡˘)۶\n"
                + "\tHow can I help you?\n\n"
                + "\tYou can type:\n"
                + "\t\t `list` to get a list of tasks\n"
                + "\t\t `todo ${item}` to add a todo\n"
                + "\t\t `deadline ${item} /by ${time}` to add a deadline\n"
                + "\t\t `event ${item} /at ${time}` to add an event\n"
                + "\t\t `done ${i}` to mark task i as completed\n"
                + "\t\t `bye` to end this chat\n";
        String endMessage = "\n\tSad to see you go :(\n\t...shutting down...";

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("--> ");
                String input = sc.nextLine();

                if (input.equals("list")) {
                    if (taskList.size() == 0) {
                        System.out.println(wrapOutput("No tasks added yet!"));
                        continue;
                    }
                    StringBuilder res = new StringBuilder();
                    for (int i = 0; i < taskList.size(); i++) {
                        Task t = taskList.get(i);
                        res.append(i + 1).append(". ").append(t.toString()).append("\n");
                    }
                    System.out.println(wrapOutput(res.substring(0, res.length() - 1)));
                } else if (input.equals("bye")) {
                    System.out.println(endMessage);
                    break;
                } else {
                    String[] words = input.split(" ");
                    String mainCommand = words[0];
                    Task t;

                    switch (mainCommand) {
                        case "done":
                            handleDone(taskList, words);
                            continue;
                        case "todo": {
                            String[] split = input.split(" ");
                            if (split.length < 2) {
                                throw new MissingDescriptionException();
                            }
                            t = new ToDo(input.substring(5));
                            break;
                        }
                        case "deadline": {
                            String[] split = input.split("/by ");
                            if (split.length < 2) {
                                throw new InvalidFormatException("`deadline ${item} /by ${time}`");
                            }
                            t = new Deadline(split[0].substring(9).trim(), split[1].trim());
                            break;
                        }
                        case "event": {
                            String[] split = input.split("/at ");
                            if (split.length < 2) {
                                throw new InvalidFormatException("`event ${item} /at ${time}`");
                            }
                            t = new Event(split[0].substring(5).trim(), split[1].trim());
                            break;
                        }
                        default:
                            throw new UnknownCommandException();
                    }

                    taskList.add(t);
                    String plurality = " task";
                    if (taskList.size() != 1) {
                        plurality += "s";
                    }
                    System.out.println(wrapOutput("Got it! I've added this task:\n   "
                            + t.toString() + "\nNow you have " + taskList.size()
                            + plurality + " in the list."));

                }
            } catch (DukeException e) {
                System.out.println(wrapOutput(e.getMessage()));
            }
        }
    }
}