package duke;

/**
 * The Parser class that makes sense of the user input.
 */
public class Parser {
    private String input;
    private String[] inputArr;
    private String command;

    /**
     * Constructor for a Parser object.
     *
     * @param input The user input to be parsed.
     */
    public Parser(String input) {
        this.input = input;
        this.inputArr = input.split(" ");
        this.command = inputArr[0];
    }

    private boolean isEditingTask() {
        return command.equals("done") || command.equals("delete");
    }

    private boolean isFindingTask() {
        return command.equals("find");
    }

    private boolean isAddingNewTask() {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private String[] getAddTaskArgs() throws DukeException {
        int inputLen = inputArr.length;
        if (inputLen == 1) {
            throw new DukeException("OOPS!! The description of a " + command
                    + " cannot be empty :(");
        }

        if (command.equals("todo")) {
            String desc = "";
            for (int i = 1; i < inputLen; i++) {
                desc = desc + inputArr[i] + " ";
            }
            return new String[] {command, desc.strip()};
        } else {
            boolean hasByAt = false;
            String desc = "";
            String time = "";
            for (int i = 1; i < inputLen; i++) {
                String curr = inputArr[i];
                if (curr.equals("/by") || curr.equals("/at")) {
                    hasByAt = true;
                    continue;
                }
                if (hasByAt) {
                    time += curr + " ";
                } else {
                    desc += curr + " ";
                }
            }
            if (desc.length() == 0 || time.length() == 0) {
                throw new DukeException("Something is missing..."
                        + "\nPlease specify the task in the correct format"
                        + "\ni.e. deadline finish homework /by 2021-03-21");
            } else if (!hasByAt) {
                throw new DukeException("Make sure to specify the time after a '/by' or '/at'");
            } else {
                return new String[] {command, desc.strip(), time.strip()};
            }
        }
    }

    /**
     * Method that parses the user input and returns the relevant commands and arguments as
     * strings in an array.
     *
     * @return An array of Strings with valid commands and arguments.
     * @throws DukeException If an invalid command or argument is given as user input.
     */
    public String[] parse() throws DukeException {
        int inputLen = inputArr.length;
        if (input.equals("bye") || input.equals("list")) {
            return new String[] {command};
        } else if (isFindingTask()) {
            if (inputLen < 2) {
                throw new DukeException("Please specify the search keyword"
                        + "\n i.e. find book");
            } else {
                return new String[] {command, inputArr[1]};
            }
        } else if (isEditingTask()) {
            if (inputLen < 2) {
                throw new DukeException("Please specify the index of the task to be edited"
                        + "\n i.e. done 3");
            } else {
                try {
                    int index = Integer.parseInt(inputArr[1]);
                    return new String[] {command, inputArr[1]};
                } catch (NumberFormatException e) {
                    throw new DukeException("duke.Task index should be a valid integer");
                }
            }
        } else if (isAddingNewTask()) {
            return getAddTaskArgs();
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
    }
}
