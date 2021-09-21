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
        } else if (response.equals("L sort items")) {
            return new ListSortedCommand();
        } else if (response.equals("bye")) {
            return new ExitCommand();
        } else if (response.contains("done")) {
            return new DoneCommand(indexOfString(response));
        } else if (response.contains("todo")) {
                return new AddCommand(new Todo(commandDescription(response)));
        } else if (response.contains("deadline")) {
            String [] deadlineDescription = deadlineEventDescription(response);
            return new AddCommand(new Deadline(deadlineDescription[0], deadlineDescription[1]));
        } else if (response.contains("event")) {
            String [] eventDescription = deadlineEventDescription(response);
            return new AddCommand(new Event(eventDescription[0], eventDescription[1]));
        } else if (response.contains("delete")) {
            return new DeleteCommand(indexOfString(response));
        } else if (response.contains("find")) {
                return new FindCommand(commandDescription(response));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "Try todo, event, or deadline");
        }
    }
    public static int indexOfString(String command){
        String[] str = command.split(" ");
        int num = Integer.parseInt(str[1]);
        return num;
    }
    public static String commandDescription(String command) throws DukeException{
        if(!command.contains(" ")){
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        }
        return command.substring(command.indexOf(" ") + 1);
    }
    public static String[] deadlineEventDescription(String command){
        String[] description = new String[2];
        int tLabelFirst = command.indexOf(" ") + 1;
        int tTimeFirst = command.indexOf("/");
       description[0] = command.substring(tLabelFirst, tTimeFirst - 1);
       description[1] = command.substring(tTimeFirst + 4);
       return description;
    }

}