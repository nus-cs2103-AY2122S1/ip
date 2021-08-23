import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    @SafeVarargs
    public final void print(String... msgLines) {
        if (msgLines.length == 0) {
            return;
        }
        System.out.println("\t_______________________________________________________");
        for (String msg : msgLines) {
            System.out.println("\t  " + msg);
        }
        System.out.println("\t_______________________________________________________");
    }

    public void printError(String... msgLines) {
        if (msgLines.length == 0) {
            return;
        }
        System.err.println("\t_______________________________________________________");
        System.err.println("\t  *Making angry noise*");
        for (String msg : msgLines) {
            System.err.println("\t  " + msg);
        }
        System.err.println("\t_______________________________________________________");
    }

    public void greet() {
        print("Yo, I'm Xiri.", "How can I help you?");
    }

    public void bye() {
        print("Ok bye, see you later.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
