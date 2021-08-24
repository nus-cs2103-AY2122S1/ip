import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {

    private final Brain brain;
    private final Storage storage;
    private final Speech speech;
    private final Writer writer;

    public Duke() {
        storage = new Storage();
        writer = new Writer();
        speech = new Speech(writer, true, 48);
        brain = new Brain(storage, speech, writer);
    }

    /**
     * Continuous scan loops until user input "bye"
     */
    public void  run(){
        speech.welcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Say something to Duke: ");
            String input = sc.nextLine();
            boolean cont = brain.decide(input);
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
        new Duke().run();
//        String input = "2019-02/12 1900";
//        String[] timeFormat = input.trim().split(" ");
//        LocalDate ok = LocalDate.parse(timeFormat[0]);
//        input = timeFormat[1];

    }
}
