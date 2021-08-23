package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    private final Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void greet() {
        reply("Hello! I'am Duke\n" +
                "What can I do for you?");
    }

    public void reply(String msg) {
        String indentedMsg = Arrays.stream(msg.split("\n"))
                .map(s -> String.format("\t%s\n", s))
                .collect(Collectors.joining(""));
        System.out.printf("\t____________________________________\n" +
                "%s" +
                "\t____________________________________\n", indentedMsg);
    }

    public void handleError(Exception e) {
        reply(e.getMessage());
    }

}
