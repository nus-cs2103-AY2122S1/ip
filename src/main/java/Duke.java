import java.util.Scanner;

public class Duke {

    private static final String LINE = "----------------------------------------------";
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
            } else if (input.startsWith("done")) {
                int index = input.charAt(5) - 49;
                taskList[index].markAsDone();
                System.out.println("You have swallowed that pesky fly! RIBBIT!");
                System.out.println("  " + taskList[index].toString());
                System.out.println(LINE);
            } else if (input.equals("list")) {
                System.out.println("Here is your menu for today:");
                for (int i = 0; i < size; i++) {
                    System.out.println(i + 1 + "." + taskList[i].toString());
                }
                System.out.println(LINE);
            } else {
                if (input.startsWith("todo")) {
                    String name = input.substring(5);
                    taskList[size] = new ToDo(name);
                } else if (input.startsWith("deadline")) {
                    int index = input.indexOf("/by");
                    String name = input.substring(9, index);
                    String by = input.substring(index + 4);
                    taskList[size] = new Deadline(name, by);
                } else { // is an Event
                    int index = input.indexOf("/at");
                    String name = input.substring(6, index);
                    String at = input.substring(index + 4);
                    taskList[size] = new Event(name, at);
                }

                System.out.println("A fly has been added to the menu:");
                System.out.println("  " + taskList[size].toString());
                size++;
                System.out.println("Now you have " + size + " flies to eat! RIBBIT!");
                System.out.println(LINE);
            }
        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {
        Duke jo = new Duke();
        jo.greeting();
        jo.echo();
    }
}
