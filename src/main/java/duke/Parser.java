package duke;

public class Parser {

    /**
     * Makes sense of user input command
     *
     * @param command
     * @return String list to make sense of user input
     * @throws DukeException
     */
    public static String[] parse(String command) throws DukeException {
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            String[] fullCommand = new String[] { "end" };
            return fullCommand;
        } else if (command.equals("list")) {
            String[] fullCommand = new String[] { "list" };
            return fullCommand;
        } else if (command.startsWith("done")) {
            String[] split = command.split(" ");
            String index = split[1];
            String[] fullCommand = new String[] { "done", index };
            return fullCommand;
        } else if (command.startsWith("delete")) {
            String[] split = command.split(" ");
            String index = split[1];
            String[] fullCommand = new String[] { "delete", index };
            return fullCommand;
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String[] split = command.split("todo ");
            String[] fullCommand = new String[] { "todo", split[1] };
            return fullCommand;
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] split = command.split("deadline ");
            String description = split[1].split(" /")[0];
            String time = split[1].split("/by ")[1];
            String[] fullCommand = new String[] { "deadline", description, time };
            return fullCommand;
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            String[] split = command.split("event ");
            String description = split[1].split(" /")[0];
            String time = split[1].split("/at ")[1];
            String[] fullCommand = new String[] { "event", description, time };
            return fullCommand;
        } else if (command.startsWith("find")) {
            String[] split = command.split("find ");
            String key = split[1];
            String[] fullCommand = new String[] { "find", key };
            return fullCommand;
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
