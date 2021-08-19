package main.java;

public class DoneCommand extends Command {

    protected String input;

    protected Task[] list;

    DoneCommand(String input, Task[] list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            list[index].markAsDone();
            return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Great! I've marked the following task as done:\n"
                    + this.list[index].getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        } catch (NumberFormatException e) {
            throw new DukeException("It looks like you did not enter a valid integer for the \"done\" command. Please try again!");
        }
    }
}