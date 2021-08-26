package duke;
import java.util.ArrayList;

public class Ui {

    /**
     * Returns the welcome message of the duke chat bot.
     * 
     * @return Welcome Message
     */
    public static String WelcomeMessage() {
        String separator = "    ____________________________________________________________";
        return separator + "\n" + "     Hello! I'm Duke" + "\n" + "     What can I do for you?"  + "\n" + separator;
    }

    public ArrayList<Task> run(ArrayList<Task> taskList, String input) {
        ArrayList<Task> list = taskList;
        String separator = "    ____________________________________________________________";
        try {
            if (Parser.parse(input).equals("list")) {
                if (list.size() == 0) {
                    System.out.println(separator);
                    System.out.println("     You have no tasks in your list!");
                } else {
                    System.out.println(separator);
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task item = list.get(i);
                        System.out.println(String.format("     %d. %s", (i + 1), item.toString()));
                    }
                }
            } else if (Parser.parse(input).equals("done")) {
                if (input.replaceAll("\\s", "").length() == 4) {
                    throw new DukeException(DukeException.Type.EmptyDone);
                } else {
                    String modInput = input.replaceAll("\\s", "");
                    int index = Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1;
                    if (list.size() <= index) {
                        throw new DukeException(DukeException.Type.InvalidTaskNumber);
                    } else {
                        Task item = list.get(index);
                        item.markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println(String.format("       %s", item.toString()));
                    }
                }
            } else if (Parser.parse(input).equals("delete")) {
                if (input.replaceAll("\\s", "").length() == 6) {
                    throw new DukeException(DukeException.Type.EmptyDelete);
                } else {
                    String modInput = input.replaceAll("\\s", "");
                    int index = Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1;
                    if (list.size() <= index) {
                        throw new DukeException(DukeException.Type.InvalidTaskNumber);
                    } else {
                        Task item = list.remove(index);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println(String.format("       %s", item.toString()));
                        System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                    }
                }
            } else if (Parser.parse(input).equals("todo")) {
                if (input.replaceAll("\\s", "").length() == 4) {
                    throw new DukeException(DukeException.Type.EmptyTodo);
                } else {
                    Todo t = new Todo(input.substring(5));
                    System.out.println();
                    list.add(t);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + t.toString());
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                }
            } else if (Parser.parse(input).equals("event")) {
                if (input.replaceAll("\\s", "").length() == 5) {
                    throw new DukeException(DukeException.Type.EmptyEvent);
                } else {
                    String[] splitString = input.substring(6).split(" /at ", 2);
                    Event e = new Event(splitString[0], splitString[1]);
                    list.add(e);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + e.toString());
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                }
            } else if (Parser.parse(input).equals("deadline")) {
                if (input.replaceAll("\\s", "").length() == 8) {
                    throw new DukeException(DukeException.Type.EmptyDeadline);
                } else {
                    String[] splitString = input.substring(9).split(" /by ", 2);
                    Deadline d = new Deadline(splitString[0], splitString[1]);
                    list.add(d);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + d.toString());
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                }
            } else if (Parser.parse(input).equals("InvalidCommand")) {
                throw new DukeException(DukeException.Type.InvalidCommand);
            }
            System.out.println(separator);
        } catch (DukeException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("    ____________________________________________________________");
        }
        return list;
    }

}
