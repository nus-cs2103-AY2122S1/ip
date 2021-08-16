import java.util.Arrays;
import java.util.stream.Collectors;

public class Duke {
    private static void reply(String msg) {
        String indentedMsg = Arrays.asList(msg.split("\n")).stream()
                                   .map(s -> String.format("\t%s\n", s))
                                   .collect(Collectors.joining(""));
        System.out.printf(
                        "\t____________________________________\n" +
                        "%s" +
                        "\t____________________________________\n", indentedMsg);
    }

    private static void greet() {
        reply("Hello! I'am Duke\n" +
                "What can I do for you?");
    }

    public static void main(String[] args) {
        greet();
    }
}
