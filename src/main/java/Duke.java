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

    void greet(){
        System.out.println(greeting);
    }

    void sayFarewell() {
        System.out.println(farewell);
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println(greeting);
//        boolean running = true;
//        while(running) {
//            String toEcho = userInput.nextLine();
//            if(toEcho.equalsIgnoreCase("bye")) {
//                running = false;
//                break;
//            }
//            System.out.println('1');
//            System.out.println(toEcho);
//            System.out.println('2');
//        }
//        System.out.println("Bye. Hope to see you again soon!");

        Duke weekTwo = new Duke();
        weekTwo.greet();

        while(weekTwo.running) {
            weekTwo.setInstruction();
            if(weekTwo.checkBye()){
                break;
            }
            weekTwo.echo();
        }
        weekTwo.sayFarewell();
    }
}
