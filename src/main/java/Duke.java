import java.util.Scanner;

/**
 * Duke is a chatbot application for CS2103T individual project.
 */
public class Duke {

    public static void main(String[] args) {
        CommonUtils.greet();
        Scanner sc = new Scanner(System.in);
        TaskManagement taskManagement = new TaskManagement();
        ActionParser.run(sc, taskManagement);
        sc.close();
    }
}
