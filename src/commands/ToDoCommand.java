package commands;

public class ToDoCommand extends Command {

    public String todo = "";

    public ToDoCommand(String input) {
        String[] array = input.split(" ");
        for (int i = 1; i < array.length; i++) {
            this.todo += (array[i] + " ");
        }
    }
}