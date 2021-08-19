import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String[] list = new String[100];
        int amount = 0;

        String lineBreak = "\t____________________________________________________________";
        System.out.println(lineBreak
                + "\n\t Hello! I'm Luke, your slightly useful personal assistant!\n"
                + "\t What can I do for you, my liege?\n"
                + "\t Type 'list' to show previous inputs\n"
                + "\t Type 'bye' to end\n"
                + lineBreak);

        String response = scanner.nextLine();

        while (!response.equals("bye")) {
            if(response.equals("list")) {
                System.out.println(lineBreak);
                for (int i = 0; i < amount; i++) {
                    System.out.println("\n\t " + (i + 1) + ". " + list[i]);
                }
                System.out.println(lineBreak);
            } else {
                System.out.println(lineBreak
                        + "\n\t added: "
                        + response
                        + "\n"
                        + lineBreak);
                list[amount] = response;
                amount++;
            }
            response = scanner.nextLine();
        }

        System.out.println(lineBreak
                + "\n\t Bye! Talk again sometime!\n"
                + lineBreak);

        scanner.close();
    }
}
