import java.util.Scanner;

public class Duke {

    private static final String LINE = "----------------------------------------------";
//    private String[] store = new String[100];
    private Task[] taskList = new Task[100];
    private int size = 0;

    public void greeting() {
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
        String greeting = "I am Jo the Frog! RIBBIT! \n";
        System.out.println(frog + greeting + "How may I help you?\n" + LINE);
    }

    public void echo() {
        String input = "";
        do {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("See you again in my frog hole! RIBBIT!");
            } else if (input.equals("list")) {
                for (int i = 0; i < size; i++) {
                    System.out.println(i + 1 + "." + taskList[i].print());
                }
                System.out.println(LINE);
            } else {
                taskList[size] = new Task(input);
                System.out.println("croaked: " + taskList[size].getName() + "\n" + LINE);
                size++;
            }
        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {
        Duke jo = new Duke();
        jo.greeting();
        jo.echo();
    }
}
