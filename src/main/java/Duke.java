import java.util.Scanner;

public class Duke {
    public static String lineProducer() {
        return "   -----------------------------------------";
    }
    public static String indentationAdder() {
        return "\n    ";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(lineProducer() + indentationAdder() + "Hello I'm your friendly chatbot Duke!" +
                indentationAdder() + "What can I help you with?\n" + lineProducer() );
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println(lineProducer() + indentationAdder() + "Bye! Please visit me again!\n" + lineProducer());
                break;
            }
            System.out.println(lineProducer() + indentationAdder() + str + "\n" + lineProducer());
        }

        sc.close();
    }
}
