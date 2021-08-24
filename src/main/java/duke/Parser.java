package duke;

public class Parser {

    protected String[] currentLine;

    public void intepretCommand(String command) {
        currentLine = command.split(" ");
    }

    public String getFirstCommand() {
        return currentLine[0];
    }

    public int findCommandIndex() throws NumberFormatException{
        return Integer.parseInt(this.currentLine[1]);
    }

    public String findKeyword() {
        return currentLine[1];
    }


    /**
     * Finds the date in the command (if any).
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


