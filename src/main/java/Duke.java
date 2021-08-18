import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm Katheryne of the Adventurers Guild");
        System.out.println("Right now, I only echo your statements.");
        while (true) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String userInput = in.readLine();
                if (userInput.equalsIgnoreCase("BYE")) {
                    System.out.println("Ad Astra Abyssoque, Traveller!");
                    break;
                } else {
                    System.out.println(userInput);
                }
            } catch(IOException exc) {
                System.out.println("I/O Exception: " + exc);
            }

    }
    }

}
