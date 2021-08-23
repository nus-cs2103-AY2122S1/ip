public class Parser {

    public Parser() {

    }

    public static String parse(String command) {
        String[] words = command.split(" ");
        String init = words[0];
        return init;
    }
}
