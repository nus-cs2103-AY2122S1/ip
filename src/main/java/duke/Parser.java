package duke;

/**
 * Class that associates user input to its respective Command.
 */
public class Parser {
    /**
     * Receives input and parses it into its respective task command.
     * @param response
     * @return Command object     The command inputted by the user
     * @throws DukeException
     */
    public static Command parse(String response) throws DukeException {
        if (response.equals("list")) {
            return new ListCommand();
        } else if (response.equals("bye")) {
            return new ExitCommand();
        } else if (response.contains("done")) {
            String[] str = response.split(" ");
            String task = str[0];
            int num = Integer.parseInt(str[1]);
            return new DoneCommand(num);
        } else if (response.contains("todo")) {
            if (response.length() > 4) {
                String[] str = response.split(" ");
                String task = str[0];
                String command = str[1];
                command = response.substring(response.indexOf(" ") + 1);
                return new AddCommand(new Todo(command));
            } else {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
        } else if (response.contains("deadline")) {
            int tLabelFirst = response.indexOf(" ") + 1;
            int tTimeFirst = response.indexOf("/");
            String tLabel = response.substring(tLabelFirst, tTimeFirst - 1);
            String tTime = response.substring(tTimeFirst + 4);
            return new AddCommand(new Deadline(tLabel, tTime));
        } else if (response.contains("event")) {
            int tLabelFirst = response.indexOf(" ") + 1;
            int tTimeFirst = response.indexOf("/");
            String tLabel = response.substring(tLabelFirst, tTimeFirst - 1);
            String tTime = response.substring(tTimeFirst + 4);
            return new AddCommand(new Event(tLabel, tTime));

        } else if (response.contains("delete")) {
            String[] str = response.split(" ");
            String task = str[0];
            int num = Integer.parseInt(str[1]);
            return new DeleteCommand(num);
        } else if (response.contains("find")) {
            if (response.length() > 4) {
                String[] str = response.split(" ");
                String task = str[0];
                String keyword = str[1];
                return new FindCommand(keyword);
            } else {
                throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "Try todo, event, or deadline");
        }
    }
}