import java.util.Scanner; 

public class Processor {
    private static String lineString = "_______________________________________________";
    private static String spaceString = "    ";
    private static String decoratorString = spaceString + lineString; 

    /** 
     * @param input scanner for user input from Duke class
     */
    public static void process(Scanner input) {
        while (true) {
            String newInput = input.nextLine();
            if (newInput.equals("bye")) {
                break; 
            } else {
                echo(newInput); 
            }
        }
    }
    
    /** 
     * print echo messgae
     * @param inputString string to echo 
     */
    private static void echo(String inputString) {
        System.out.println(decoratorString);
        System.out.println(spaceString + inputString);
        System.out.println(decoratorString);
    }
    
    //display exit message
    public static void exit() {
        System.out.println(decoratorString);
        System.out.println("    " + "Bye. Hope to see you again soon!");
        System.out.println(decoratorString);
    }
}