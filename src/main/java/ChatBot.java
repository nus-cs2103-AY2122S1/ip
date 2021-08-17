import java.util.Scanner;

public class ChatBot {

    private Task[] list = new Task[100];
    private int lastIndex = 0;


    private String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";

        return greeting;
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

    private void printList() {
        for (int i = 0; i <= lastIndex; i++) {
            if (list[i] != null) {
                System.out.println(i + 1 + "." + this.list[i].printTask());
            }
        }
    }

    private boolean checkDone(String str) {
        boolean isDone = false;
        if (str.length() > 3) {
            isDone = str.substring(0,4).equals("done");
        }
        return isDone;
    }

    private void doneSeq(String str) {
        System.out.println("Good job for this thing done man:");
        int indexNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
        list[indexNum - 1].setComplete();
        System.out.println("   " + list[indexNum - 1].printTask());
    }

    private boolean checkToDo(String str) {
        boolean isToDo = false;
        if (str.length() >= 4) {
            isToDo = str.substring(0,4).equals("todo");
        }
        return isToDo;
    }

    private void todoSeq(String str) {
        System.out.println("Alrighty! I have added this task:");
        list[lastIndex] = new ToDo(str.substring(5));
        System.out.println("   " + list[lastIndex].printTask());
        lastIndex++;
        System.out.println("Now you have " + lastIndex + " task(s) in total!");
    }

    private boolean checkDeadLine(String str) {
        boolean isDeadLine = false;
        if (str.length() >= 8) {
            isDeadLine = str.substring(0,8).equals("deadline");
        }
        return isDeadLine;
    }

    private void deadlineSeq(String str) {
        System.out.println("Alrighty! I have added this task:");
        list[lastIndex] = new Deadline(str.substring(9, str.indexOf("/")), str.substring(str.indexOf("/") + 4));
        System.out.println("   " + list[lastIndex].printTask());
        lastIndex++;
        System.out.println("Now you have " + lastIndex + " task(s) in total!");
    }

    private boolean checkEvent(String str) {
        boolean isEvent = false;
        if (str.length() >= 5) {
            isEvent= str.substring(0,5).equals("event");
        }
        return isEvent;
    }

    private void eventSeq(String str) {
        System.out.println("Alrighty! I have added this task:");
        list[lastIndex] = new Event(str.substring(6, str.indexOf("/")), str.substring(str.indexOf("/") + 4));
        System.out.println("   " + list[lastIndex].printTask());
        lastIndex++;
        System.out.println("Now you have " + lastIndex + " task(s) in total!");
    }


    private void startInput() {
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        boolean doneInput = checkDone(input);
        boolean todoInput = checkToDo(input);
        boolean deadlineInput = checkDeadLine(input);
        boolean eventInput = checkEvent(input);

        if (input.equals("bye")) {                                          //bye input
            System.out.println("See ya again later!");
            userInput.close();
            return;
        } else if (input.equals("list")) {                                   //list input
            if (this.listEmpty()) {                                          //empty list check
                System.out.println("You haven't added anything yet!");
            } else {                                                         //non-empty list
                printList();
            }
        } else if (doneInput) {                                              //done input
            doneSeq(input);
        } else if (todoInput){
            todoSeq(input);
        } else if (deadlineInput) {
            deadlineSeq(input);
        } else if (eventInput) {
            eventSeq(input);
        } else {
            System.out.println("I'm not too sure what you meant.");
            System.out.println("Try again with these keywords.");
            System.out.println("todo deadline event");
        }
        startInput();
    }

    void welcomeSeq() {
        System.out.println(this.welcomeMessage());
        startInput();
    }

}
