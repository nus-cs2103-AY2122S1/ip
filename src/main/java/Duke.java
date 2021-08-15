import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Hello I am Duke\nWhat can I do for you today?\n" + line);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(line + "\n" + command + "\n" + line);
            command = scanner.nextLine();
        };
        System.out.println("Bye bye Dubai! Have a nice day!\n");
    }
}
