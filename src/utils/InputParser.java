package utils;

public class InputParser {

    private String command = "";
    private String otherArguments = "";

    public InputParser(String string) {
        String[] array = string.split(" ");
        this.command = array[0];
        for (int i = 1; i < array.length; i++) {
            if (i == array.length - 1) {
                this.otherArguments += (array[i] + " ");
            }
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String getOtherArguments() {
        return this.otherArguments;
    }
}
