import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "\n----------------------------------------------";

        // Credits to https://textart4u.blogspot.com/2014/04/frog-leap-unicode-copy-paste-text-art.html
        // for the frog ASCII text art!
        String frog =
                "             ╭━━━╮━━━╮\n" +
                "             ┃ ▉ ┃ ▉ ┃\n" +
                " ╱▔▔╲  ╱▔▔▔╲╱    ╰▔▔▔▔╲        RIBBIT!\n" +
                "╱  ╱╲╲╱╱         ╰━━━━╱\n" +
                "╲╱╱▔▔╲╱╲         ╱▔▔▔▔▔\n" +
                " ╱  ╱╲╲╱╱▔▔╲    ╰━━━╮━━━╮\n" +
                " ╲ ╱  ╲╱    ▔▔▔▔▔▔▔╰╯▔▔╰╯\n";
        String greeting = "I am Jo and I love frogs! RIBBIT! \n";
        System.out.println(frog + greeting + "How may I help you?" + line);

        String input = "";
        do {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("See you again in my frog hole! RIBBIT!");
            } else {
                System.out.println(input + line);
            }

        } while (!input.equals("bye"));
    }
}
