import java.util.Scanner;

public class ChatBot {

    static Scanner userInput = new Scanner(System.in);
    private Task[] list = new Task[100];
    private int lastIndex = 0;


    private String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";

        return logo + greeting;
    }

    private boolean listEmpty() {
        boolean empty = true;
        for (Task task : list) {
            if (task != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    private void printList(){
        for (int i = 0; i <= lastIndex; i++) {
            if (list[i] != null) {
                System.out.println(i + 1 + "." + this.list[i].printTask());
            }
        }
    }

    private boolean checkDone(String str) {
        return str.equals("done");
    }

    private void startInput() {
        String input = userInput.nextLine();
        boolean doneInput = false;

        if (input.length() > 3) {
            doneInput = checkDone(input.substring(0,4));
        }

        if (input.equals("bye") || input.equals("bye ")) {                   //bye input
            System.out.println("See ya again later!");
            return;
        } else if (input.equals("list")) {                                   //list input
            if (this.listEmpty()) {                                          //empty list check
                System.out.println("You haven't added anything yet!");
            } else {                                                         //non-empty list
                printList();
            }
        } else if (doneInput) {                                              //done input
            System.out.println("Good job for this thing done man:");
            int indexNum = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            list[indexNum - 1].setComplete();
            System.out.println("   " + list[indexNum - 1].printTask());
        } else {                                                             //all other inputs
            list[lastIndex] = new Task(input);
            lastIndex++;
            System.out.println("added: " + input);
        }
        startInput();
    }

    void welcomeSeq() {
        System.out.println(this.welcomeMessage());
        startInput();
    }

}
