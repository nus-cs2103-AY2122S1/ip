import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);

        String name = "Catobot";
        String banner = "(=^･ω･^=)(=^･ω･^=)(=^･ω･^=)";
        String greeting = "Hello I am " + name + " (>^ω^<)\nWhat can I do for you meow?";
        System.out.println(banner + "\n" + greeting + "\n" + banner);

        // takes input from the keyboard
        String response = sc.nextLine();
        while (!response.equals("bye")) {
            // prints the response
            System.out.println(banner + "\n" + response + "\n" + banner);
            response = sc.nextLine();
        }
        String byeMessage = "Bye meow! I will always wait here meow(>^ω^<)";
        System.out.println(banner + "\n" + byeMessage + "\n" + banner);
        // closes the scanner
        sc.close();
    }
}
