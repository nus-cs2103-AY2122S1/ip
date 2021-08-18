import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<String> lst = new ArrayList<String>();
        System.out.println("Greetings Traveller, I'm Katheryne of the Adventurers Guild.");
        System.out.println("Right now, I only echo your statements.");
        while (true) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String userInput = in.readLine();
                if (userInput.equalsIgnoreCase("BYE")) {
                    System.out.println("Ad Astra Abyssoque, Traveller!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                        System.out.println("Here's the list I've stored for you:");
                        for(int i = 1; i <= lst.size(); i++) {
                            System.out.println(i + ") " + lst.get(i - 1));
                        }
                } else {
                    lst.add(userInput);
                    System.out.println("Okay, I've added '"+ userInput + "' to your list");
                }
            } catch(IOException exc) {
                System.out.println("I/O Exception: " + exc);
            }

    }
    }

}
