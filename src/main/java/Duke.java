import java.util.Scanner;

public class Duke {

    /**
     * Continuous scan loops until user input "bye"
     */
    public void  run(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Say something to Duke: ");
            String input = sc.nextLine();
            boolean cont = Brain.decide(input);
            if (!cont) {
                break;
            }
        }
    }

    /**
     * Main program
     * @param args
     */
    public static void main(String[] args) {
        Speech.welcome();
        new Duke().run();
    }
}
