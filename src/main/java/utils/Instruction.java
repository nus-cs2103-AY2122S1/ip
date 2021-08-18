package utils;

/**
 * Instruction class.
 *
 * This class is the product from Parser, which is then used by Duke and TaskManager.
 */
public class Instruction {

    private Command command;

    private String[] strings;

    private int number;

    public Instruction(Command command, String[] strings, int number) {
        this.command = command;
        this.strings = strings;
        this.number = number;
    }

    public Instruction(Command command) {
        this.command = command;
        this.strings = new String[] {};
        this.number = -1;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
