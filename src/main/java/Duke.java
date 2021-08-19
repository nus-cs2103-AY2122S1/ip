import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> xs = new ArrayList<>();

    public static String lineProducer() {
        return "   -----------------------------------------";
    }

    public static String indentationAdder() {
        return "\n    ";
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        System.out.println(lineProducer() + indentationAdder() + "Hello I'm your friendly chatbot Duke!" +
                indentationAdder() + "What can I help you with?\n" + lineProducer() );
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println(lineProducer() + indentationAdder() + "Bye! Please visit me again!\n" + lineProducer());
                break;
            } else if (str.equals("list")) {
                System.out.println(lineProducer());
                for (int i = 0; i < duke.xs.size(); i++) {
                    System.out.println("    " + (i + 1) + ": " + duke.xs.get(i));
                }
                System.out.println(lineProducer());
                continue;
            }
            duke.xs.add(str);
            System.out.println(lineProducer() + indentationAdder() + "added: " + str + "\n" + lineProducer());
        }

        sc.close();
    }
}
