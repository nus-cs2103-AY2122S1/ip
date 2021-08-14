import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 * 
 * Current Progress: Level 1. Greet, Echo, Exit
 *
 * Description:
 * On running the program, Duke greets the user, simply echos commands entered by user,
 * and exits when the user types bye.
 *
 * @author Benedict Chua
 */
public class Duke {
    private static final String INDENTATION = "     ";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________";
    private static final String[] GREETING = {"Hey! I'm Duke (Tsun)!", "What do you want?",
        "...It's not like I want to help you or anything!"};
    private static final String[] GOODBYE = {"Hmph! It's not like I want to see you again or anything!"};

    private static void displayMessage(String[] messages) {
        System.out.println(LINE_SEPARATOR);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_SEPARATOR + "\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Greets user
        displayMessage(GREETING);

        String command = sc.next();
        while(!command.equals("bye")) {
            displayMessage(new String[] {command});
            command = sc.next();
        }

        displayMessage(GOODBYE);
    }
}
