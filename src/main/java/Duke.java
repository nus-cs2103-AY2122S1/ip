import java.util.Scanner;

public class Duke {
    static Scanner userInput = new Scanner(System.in);
    static String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
    static String farewell = "Bye. Hope to see you again soon!";
    private boolean running = true;
    static String feelsbadman = "__________████████_____██████\n" +
            "_________█░░░░░░░░██_██░░░░░░█\n" +
            "________█░░░░░░░░░░░█░░░░░░░░░█\n" +
            "_______█░░░░░░░███░░░█░░░░░░░░░█\n" +
            "_______█░░░░███░░░███░█░░░████░█\n" +
            "______█░░░██░░░░░░░░███░██░░░░██\n" +
            "_____█░░░░░░░░░░░░░░░░░█░░░░░░░░███\n" +
            "____█░░░░░░░░░░░░░██████░░░░░████░░█\n" +
            "____█░░░░░░░░░█████░░░████░░██░░██░░█\n" +
            "___██░░░░░░░███░░░░░░░░░░█░░░░░░░░███\n" +
            "__█░░░░░░░░░░░░░░█████████░░█████████\n" +
            "_█░░░░░░░░░░█████_████___████_█████___█\n" +
            "_█░░░░░░░░░░█______█_███__█_____███_█___█\n" +
            "█░░░░░░░░░░░░█___████_████____██_██████\n" +
            "░░░░░░░░░░░░░█████████░░░████████░░░█\n" +
            "░░░░░░░░░░░░░░░░█░░░░░█░░░░░░░░░░░░█\n" +
            "░░░░░░░░░░░░░░░░░░░░██░░░░█░░░░░░██\n" +
            "░░░░░░░░░░░░░░░░░░██░░░░░░░███████\n" +
            "░░░░░░░░░░░░░░░░██░░░░░░░░░░█░░░░░█\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█\n" +
            "░░░░░░░░░░░█████████░░░░░░░░░░░░░░██\n" +
            "░░░░░░░░░░█▒▒▒▒▒▒▒▒███████████████▒▒█\n" +
            "░░░░░░░░░█▒▒███████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            "░░░░░░░░░█▒▒▒▒▒▒▒▒▒█████████████████\n" +
            "░░░░░░░░░░████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            "░░░░░░░░░░░░░░░░░░██████████████████\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█\n" +
            "██░░░░░░░░░░░░░░░░░░░░░░░░░░░██\n" +
            "▓██░░░░░░░░░░░░░░░░░░░░░░░░██\n" +
            "▓▓▓███░░░░░░░░░░░░░░░░░░░░█\n" +
            "▓▓▓▓▓▓███░░░░░░░░░░░░░░░██\n" +
            "▓▓▓▓▓▓▓▓▓███████████████▓▓█\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█";
    String instruction;
    String[] toDo = new String[100];
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
        if(instruction.equalsIgnoreCase("list")){
            printList();
        } else {
            toDo[counter] = instruction;
            counter++;
            System.out.println("added: " + instruction);
        }
    }

    void printList() {
        for(int x = 0; x < 99;x++) {
            if(toDo[x] == null){
                break;
            }
            String temp = String.valueOf(x + 1);
            System.out.println(temp + ". " + toDo[x]);
        }
    }

    void greet(){
        System.out.println(greeting);
    }

    void sayFarewell() {
        System.out.println(farewell);
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
