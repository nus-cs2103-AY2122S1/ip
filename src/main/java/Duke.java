import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean readingInput;
    private ArrayList<String> taskList;

    Duke() {
        this.readingInput = true;
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Duke d = new Duke();

        String openingMessage = "   -------------------------------------------- \n"
                        + "   Hello! I'm Duke \n"
                        + "   What can I do for you? \n"
                        + "   -------------------------------------------- \n";
        System.out.println(openingMessage);

        Scanner sc = new Scanner(System.in);

        while (d.readingInput) {
            String input = sc.nextLine();
            String output;

            switch (input) {
                case "list":
                    output = d.printList();
                    break;

                case "bye":
                    output = "   -------------------------------------------- \n"
                            + "   Bye! Hope to see you again soon! \n"
                            + "   -------------------------------------------- \n";
                    d.readingInput = false;
                    break;

                default:
                    output = "   -------------------------------------------- \n"
                            + "   added: " + input + "\n"
                            + "   -------------------------------------------- \n";
                    d.taskList.add(input);

            }
            System.out.println(output);
        }
        sc.close();
    }

    public String printList() {
        boolean isEmptyList = false;
        int counter = 1;
        String output = "   -------------------------------------------- \n";

        while (!isEmptyList) {
            if (this.taskList.isEmpty()) {
                output += "   Current List is empty... \n"
                        + "   -------------------------------------------- \n";
                isEmptyList = true;
            } else if (counter - this.taskList.size() == 1) { // when there are no more tasks to iterate
                output += "   -------------------------------------------- \n";
                isEmptyList = true;
            } else { // adds current task to the list based on counter index
                String lineAdded = String.format("   %d. %s \n", counter, this.taskList.get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        return output;
    }
}
