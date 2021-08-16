import java.time.Period;
import java.util.Scanner;

public class Duke {

    private final String LOGO =
            "░░░░░░░░░░░░░░░░▄▄▄███████▄▄░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░▄███████████████▄░░░░░░░░░\n" +
            "░░░░░░░░░░░░░█▀▀▀▄░░░░█████▀▀███▄░░░░░░░\n" +
            "░░░░░░░░░░░░█░░▄░░█▄▄█░░░░▀▄▄█████░░░░░░\n" +
            "░░░░░░░░░▄▀▀▀▄▄▀▀▀▀▀▀▄░░▀░░█▀▀▀░▀██░░░░░\n" +
            "░░░░░░░▄▀░░░░░█░░░░░░▀▄▄▄▄█░░░░░░░▀▄░░░░\n" +
            "░░░░░░▄▀░░░░░▄█▀▄▄▄▄░░░░░▄░░░░░░░░░█░░░░\n" +
            "░░░░░░█░░░░░▄█▀▄▄▄▄▄▄▄▄▄▀▀░░░░░░░░░▀▄░░░\n" +
            "░░░░░█░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░█░░░\n" +
            "░░░░░█░░░░░░░░▀▄░░░░░░░░░░░░░░░░░░░░█░░░\n" +
            "░░░░░█░░░░░░░░▄█░░░░░░░░░░░░░░░░░░░░█░░░\n" +
            "░░░░░█░░░░░░▄▀░░░░░░░░▀▄░░░░░░░░░░░░█░░░\n" +
            "░░░░░█░░░░░░▀▄░░░▄░░░░░█░░░░░░░░░░░░█░░░\n" +
            "░░░░░█░░░░░░░░▀▀▀▀▀█▄▄▀░░░░░░░░░░░░░█░░░\n" +
            "░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄██░░\n" +
            "░░░░░█▄░░░░░░░░░░░░░░░░░░░░░░░░░░▄▀▀▄▀▄▄\n" +
            "░░░░▄█▀▄░░░░░░░░░░░░░░░░░░░░░░▄▄▀░░▄▀░░░\n" +
            "░▄░▀░░█░▀▄▄░░░░░░░░░░░░░░░▄▄▄▀░░░▄▀░░░░░\n" +
            "▀░░░░░░▀▄░░▀▄░░░░░░░░▄▄▄▀▀░░░░▄▀▀░░░░░░░";
    private final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n" +
                    "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n" +
                    "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";
    private final String GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    private final String GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";


    private void printHorizontalLine(){
        System.out.println(HORIZONTAL_LINE);
    }

    private void printNewLine(String input){
        System.out.println("\t" + input);
    }

    private void response(String input){
        printHorizontalLine();
        printNewLine(input);
        printHorizontalLine();
    }

    private void run(){
        Scanner sc = new Scanner(System.in);
        while (true){
            String input = sc.nextLine().trim();
            if (input.equals("bye")){
                response(GOODBYE);
                return;
            } else {
                response(input);
            }

        }
    }

    void operate(){
        System.out.println("\t" + LOGO.replaceAll("\n", "\n\t"));
        response(GREETING);
        this.run();
    }

    public static void main(String[] args) {
        new Duke().operate();
    }
}