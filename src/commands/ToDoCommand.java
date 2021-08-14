package commands;

public class ToDoCommand extends Command {

    public String todo = "";

    public ToDoCommand(String input) {
        String[] array = input.split(" ");
        for (int i = 1; i < array.length; i++) {
            if (i == array.length - 1) {
                this.todo += (array[i] + " ");
            }
        }
    }
}