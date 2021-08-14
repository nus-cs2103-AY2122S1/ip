package commands;

public class DoneCommand extends Command {

    public int index;

    public DoneCommand(String input) {
        String[] array = input.split(" ");
        // TODO: ensure that assertions work
        this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
    }

}
