package duke;

/**
 * Represents the Duke parser to interpret user commands.
 */
public class Parser {

    protected String[] currentLine;

    /**
     * Method to split command into a <code>String</code> array.
     *
     * @param command The command to be split.
     */
    public void interpretCommand(String command) {
        currentLine = command.split(" ");
    }

    /**
     * Method to obtain the main command in the whole command.
     *
     * eg. <code>≈done 5</code> -> returns "done".
     * eg. <code>deadline hw /by 2000-10-10 1000</code> -> returns "deadline"
     * @return The first command.
     */
    public String getFirstCommand() {
        return currentLine[0];
    }

    /**
     * Finds the index of the command.
     *
     * eg. <code>done 5</code> -> returns 5
     * eg. <code>delete 3</code> -> returns 3
     * @return The command index.
     * @throws NumberFormatException Thrown when user does not put a number.
     */
    public int findCommandIndex() throws NumberFormatException {
        return Integer.parseInt(this.currentLine[1]);
    }

    public String findKeyword() {
        return currentLine[1];
    }


    /**
     * Finds the date in the command if command is deadline or event.
     *
     * @return The date in the command (if any).
     */
    public String findDateInCommand() {
        int startingIndexOfDate = -1;
        for (int i = 0; i < this.currentLine.length; i++) {
            if (this.currentLine[i].equals("/by") || this.currentLine[i].equals("/at")) {
                if (currentLine[0].equals("deadline") && this.currentLine[i].equals("/at")) {
                    throw new DukeException("☹ OOPS!!! Use /by for deadlines!");
                } else if (currentLine[0].equals("event") && this.currentLine[i].equals("/by")) {
                    throw new DukeException("☹ OOPS!!! Use /at for events!");
                }
                startingIndexOfDate = i + 1;
                break;
            }
        }
        if (startingIndexOfDate == -1) {
            return "";
        } else {
            StringBuilder fullDate = new StringBuilder();
            for (int i = startingIndexOfDate; i < this.currentLine.length; i++) {
                fullDate.append(this.currentLine[i]);
                if (i != this.currentLine.length - 1) {
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
        StringBuilder taskDesc = new StringBuilder();
        for (int i = 1; i < this.currentLine.length; i++) {
            if (this.currentLine[i].equals("/by") || this.currentLine[i].equals("/at")) {
                break;
            } else {
                taskDesc.append(this.currentLine[i]).append(" ");
            }
        }
        return taskDesc.toString();
    }
}


