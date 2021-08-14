import java.util.Locale;
import java.util.Scanner;

public class Duke {
    static Scanner userInput = new Scanner(System.in);

    static String linebreaker = "____________________________________________________________";
    static String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
    static String farewell = "Bye. Hope to see you again soon!";
    static String listMessage = "Here are the tasks in your list:";
    static String doneMessage = "Nice! I've marked this task as done: ";

    private boolean running = true;
    private String instruction;
    private Task[] toDo = new Task[100];
    private int counter = 0;

    void setInstruction() {
        instruction = userInput.nextLine();
    }

    void echo() {
        System.out.println(instruction);
    }

    boolean checkBye(){
        if(instruction.equalsIgnoreCase("bye")){
            running = false;
            return true;
        }
        return false;
    }

    void parse() {
        printLineBreak();
        String operative = instruction.split(" ")[0];

        if(operative.equalsIgnoreCase("list")) {
            printList();
        } else if (operative.equalsIgnoreCase("done")) {
            int taskPointer = Integer.parseInt(instruction.split(" ")[1]) - 1;
            toDo[taskPointer].markAsDone();
            completeTaskMessage(toDo[taskPointer]);
        }else {
            toDo[counter] = new Task(instruction);
            counter++;
            addedTaskMessage(instruction);
        }
    }

    void printLineBreak () {
        System.out.println(linebreaker);
    }

    void printList() {
        System.out.println(listMessage);
        for(int x = 0; x < 99;x++) {
            if(toDo[x] == null){
                break;
            }
            String temp = String.valueOf(x + 1);
            System.out.println(temp + "." + toDo[x].toString());
        }
        printLineBreak();
    }

    void greet(){
        System.out.println(greeting);
        printLineBreak();
    }

    void sayFarewell() {
        System.out.println(farewell);
        printLineBreak();
    }

    void completeTaskMessage (Task task) {
        System.out.println(doneMessage + "\n" + task.toString());
        printLineBreak();
    }

    void addedTaskMessage (String instruction) {
        System.out.println("added: " + instruction);
        printLineBreak();
    }

    public static void main(String[] args) {
        Duke weekTwo = new Duke();
        weekTwo.greet();

        while(weekTwo.running) {
            weekTwo.setInstruction();
            if(weekTwo.checkBye()){
                break;
            }
            weekTwo.parse();
        }
        weekTwo.sayFarewell();
    }
}
