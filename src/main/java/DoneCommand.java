package main.java;

public class DoneCommand extends Command {

    protected String input;

    protected TaskList list;

    DoneCommand(String input, TaskList list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {

        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            this.list[index].toggleState();
            return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Great! I've marked the following task as done:\n"
                    + this.list[index].getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        } catch (NumberFormatException e) {
            throw new DukeException("You have entered an incorrect format for the \"done\" command."
                    + "\nThe command should be in the format \"done X\" where X is a non-zero integer.");
        } catch (Exception e) {
            throw new DukeException("Something went wrong...");
        }
    }
}