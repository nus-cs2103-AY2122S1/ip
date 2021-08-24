package duke;

public class Parser {

    public Integer doneInputParser(String input) {
        return Integer.parseInt(input.substring(5)) - 1;
    }

    public Integer deleteInputParser(String input) {
        return Integer.parseInt(input.substring(7)) - 1;
    }

    public String toDoInputParser(String input) {
        return input.replace("todo ", "");
    }

    public String deadlineInputTaskParser(String input) {
        String[] parts = input.split(" /by ");
        return parts[0].replace("deadline " , "");
    }

    public String deadlineInputDateParser(String input) {
        String[] parts = input.split(" /by ");
        return parts[1];
    }

    public String eventInputTaskParser(String input) {
        String[] parts = input.split(" /at ");
        return parts[0].replace("event " , "");
    }

    public String eventInputDateParser(String input) {
        String[] parts = input.split(" /at ");
        return parts[1];
    }
}
