import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        introduceDuke();
    }

    public static void formatMessages(String message) {
        String output = "     -------------------------------------- \n"
                + "      " + message + "\n"
                + "\n     --------------------------------------";
        System.out.println(output);
    }

    public static void introduceDuke() {
        Scanner sc = new Scanner(System.in);
        String introduction = "\nHello, I am Ah Seng, the foodcourt uncle. Come chitchat with me.\n";
        formatMessages(introduction);
        respondTo(sc);
    }

    public static void terminateProgramme() {
        String endingMessage = "Ah ok bye. Next time treat uncle kopi ok?";
        formatMessages(endingMessage);
    }

    public static void respondTo(Scanner sc) {
        String input = sc.nextLine();
        if(input.equals("bye")) {
            terminateProgramme();
        } else {
            formatMessages(input);
            respondTo(sc);
        }
    }


}
