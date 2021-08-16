import java.util.Scanner;

public class Main {
    /**
     * Our main method. Starts up the chatbot and waits for user inputs
     * @param args Command Line Arguments
     */
    private static final String div = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BobbyBot chatbot = new BobbyBot();

        while (true) {
            String userInput = sc.nextLine();
            try {
                chatbot.doCommand(userInput);
            } catch (InvalidCommandException e) {
                System.out.println(div + "OOPS!!! I'm sorry, but I don't know what that means :-(" + div);
            }
        }
    }
}
