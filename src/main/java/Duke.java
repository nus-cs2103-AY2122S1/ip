import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void echoWithAdd() {
        ArrayList<String> userInputRecord = new ArrayList<>();
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();
        while(!userInput.equals("bye")) {
            if(userInput.equals("list")) {
                printUserInputRecord(userInputRecord);
            } else {
                userInputRecord.add(userInput);
                System.out.println("    ____________________________________________________________\n" +
                        "      added: " + userInput + "\n" +
                        "    ____________________________________________________________");
            }
            userInput = myScanner.nextLine();
        }
    }

    private static void  printUserInputRecord(ArrayList<String> userInputRecord) {
        if(userInputRecord.isEmpty()) {
            System.out.println("    ____________________________________________________________\n" +
                    "       Ah oh, seems like nothing is added yet :( \n" +
                    "       Try to input something first! \n" +
                    "    ____________________________________________________________"
            );
        }
        System.out.println("    ____________________________________________________________ch");
        for (int i = 0; i < userInputRecord.size(); i++) {
            System.out.println("       " + (i + 1) + ". " + userInputRecord.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Peoduo \n" +
                "     Can I help you? \n" +
                "    ____________________________________________________________";
        System.out.println(greeting);
        echoWithAdd();
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
