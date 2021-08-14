import java.util.Scanner;

public class Meow {

    /**
     * A public constructor to initialize a Meow object.
     *
     */
    public Meow() {

    }

    /**
     * Print the greeting message from the chat bot cat Meow.
     *
     */
    public void greet() {
        System.out.println("Meow: Hi human, I'm your cat Meow~ What can I do for you?");
    }

    /**
     * Print the commands entered by the user.
     *
     * @param input The user input.
     */
    public void echo(String input) {
        System.out.println(input);
    }

    /**
     * Print the goodbye message from the chat bot cat Meow.
     *
     */
    public void exit() {
        System.out.println("Meow: Bye human, see you soon! Your cat Meow is going to sleep now~");
    }

    /**
     * Return a boolean indicating whether the user wants or exit or not
     * by checking user's input. This method is not case-sensitive.
     *
     * @param input The user input.
     * @return A boolean indicating whether the user wants or exit or not.
     */
    public boolean checkExit(String input) {
        String userInput = input.toLowerCase();
        if (userInput.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Meow meow = new Meow();
        meow.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while (!meow.checkExit(input)) {
            meow.echo(input);
            input = scanner.next();
        }

        meow.exit();
        scanner.close();

    }
}
