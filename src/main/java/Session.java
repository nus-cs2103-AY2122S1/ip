import java.util.Scanner;

public class Session {

    private static String SEPARATOR = "_____________________________________________________________________";
    private static String GREETING = "Hello! I'm Jaden\nHow Are You? It's Been A While Since We Last Spoke.";
    private static String GOODBYE_OUTPUT = "Each Day Brings Me Further Away From Our Past And Closer To The Truth. #Bye";
    private static String GOODBYE_INPUT = "bye";
    private Scanner input;

    public Session() {
        this.input = new Scanner(System.in);
        this.greet();

        this.handleInputs();
    }

    private void handleInputs() {
        while (true) {
            if (this.input.hasNext()) {
                String currInput = this.input.nextLine();
                if (currInput.equals(GOODBYE_INPUT)) {
                    this.bye();
                    break;
                }
                this.output(currInput);
            }
        }
    }

    private void output(String s) {
        System.out.println(SEPARATOR);
        System.out.println(s);
        System.out.println(SEPARATOR);
    }

    private void greet() {
        output(GREETING);
    }

    private void bye() {
        output(GOODBYE_OUTPUT);
    }
}
