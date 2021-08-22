package Duke;

public class Parser {
    public static String[] parseInput(String input){
        String[] cmd_args = input.split(" ", 2);
        return cmd_args;
    }
}
