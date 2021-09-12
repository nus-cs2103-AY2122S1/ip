package taubot;

/**
 * Represents the Taubot parser to interpret user commands.
 */
public class Parser {

    protected String[] currentLine;

    /**
     * Method to split command into a <code>String</code> array.
     *
     * @param command The command to be split.
     */
    public void interpretCommand(String command) {
        String commandInLowerCase = command.toLowerCase();
        currentLine = commandInLowerCase.split(" ");
    }

    /**
     * Method to obtain the main command in the whole command.
     *
     * e.g. <code>done 5</code> -> returns "done".
     * e.g. <code>deadline hw /by 2000-10-10 1000</code> -> returns "deadline"
     * @return The first command.
     */
    public String getFirstCommand() {
        return currentLine[0];
    }

    /**
     * Finds the index of the command.
     *
     * e.g. <code>done 5</code> -> returns 5
     * e.g. <code>delete 3</code> -> returns 3
     * @return The command index.
     * @throws NumberFormatException Thrown when user does not put a number.
     */
    public int findCommandIndex() throws NumberFormatException {
        return Integer.parseInt(currentLine[1]);
    }

    /**
     * Obtain the keyword to look for after the find command.
     * @return Keyword after the find command.
     */
    public String findKeyword() {
        return currentLine[1];
    }

    private int findStartingIndexOfDateInCommand() {
        int startingIndexOfDate = -1;
        for (int i = 0; i < currentLine.length; i++) {
            if (currentLine[i].equals("/by") && currentLine[0].equals("event")) {
                throw new TaubotException("Error: Use /at for events!");
            } else if (currentLine[0].equals("deadline") && currentLine[i].equals("/at")) {
                throw new TaubotException("Error: Use /by for deadlines!");
            } else if (currentLine[i].equals("/by") || currentLine[i].equals("/at")) {
                startingIndexOfDate = i + 1;
                break;
            }
        }
        return startingIndexOfDate;
    }

    /**
     * Finds the date in the command if command is deadline or event.
     *
     * @return The date in the command (if any).
     */
    public String findDateInCommand() {
        int startingIndexOfDate = findStartingIndexOfDateInCommand();
        if (getFirstCommand().equals("schedule")) {
            startingIndexOfDate = 1;
        }
        if (startingIndexOfDate == -1) {
            return "";
        } else {
            StringBuilder fullDate = new StringBuilder();
            for (int i = startingIndexOfDate; i < currentLine.length; i++) {
                fullDate.append(currentLine[i]);
                if (i != currentLine.length - 1) {
                    fullDate.append(" ");
                }
            }
            return fullDate.toString();
        }
    }

    /**
     * Finds the task description in the command list.
     *
     * @return The description of the task.
     */
    public String findTaskDescription() {
        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < currentLine.length; i++) {
            if (currentLine[i].equals("/by") || currentLine[i].equals("/at")) {
                break;
            }
            taskDescription.append(currentLine[i]).append(" ");
        }
        return taskDescription.toString();
    }
}


