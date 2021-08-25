package duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        ui.hi();
        Parser parser = new Parser();
        String input;
        while (!((input = sc.nextLine()).equals("bye"))) {
            parser.commands(input);

        }

        if (input.equals("bye")) {
            ui.bye();
        }
    }
}

