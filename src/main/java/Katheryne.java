import java.io.*;
import java.util.*;

public class Katheryne {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<Task> lst = new ArrayList<Task>();
        System.out.println("Ad astra abyssosque! I am Katheryne, the receptionist here at the Adventurers' Guild.");
        System.out.println("How may I assist?");
        while (true) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String userInput = in.readLine();
                String[] keywordInput = userInput.split(" ", 2);
                if (userInput.equalsIgnoreCase("BYE")) {
                    System.out.println("Ad Astra Abyssoque, Traveller!");
                    break;
                } else if (keywordInput[0].equalsIgnoreCase("done") || keywordInput[0].equalsIgnoreCase("complete")) {
                    int i = Integer.parseInt(keywordInput[1]);
                    if (i < lst.size()) {
                        lst.get(i - 1).markAsDone();
                        System.out.println(lst.get(i - 1));
                    }
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Here's the list I've stored for you:");
                    for(int i = 1; i <= lst.size(); i++) {
                        System.out.println(i + ") " + lst.get(i - 1));
                    }
                } else if (keywordInput[0].equalsIgnoreCase("deadline")) {
                    String[] parsedDeadline = keywordInput[1].split(" /by ");
                    lst.add(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                    System.out.println(parsedDeadline[0] + " added to your list, do by " + parsedDeadline[1]);
                } else if (keywordInput[0].equalsIgnoreCase("event")) {
                    String[] parsedDeadline = keywordInput[1].split(" /at ");
                    lst.add(new Event(parsedDeadline[0], parsedDeadline[1]));
                    System.out.println(parsedDeadline[0] + " added to your list, do at " + parsedDeadline[1]);
                } else if (keywordInput[0].equalsIgnoreCase("todo")) {
                    lst.add(new Todo(keywordInput[1]));
                    System.out.println("Todo item " + keywordInput[1] + " added to your list");
                } else {
                    lst.add(new Task(userInput));
                    System.out.println("Okay, I've added '"+ userInput + "' to your list");
                }
                System.out.println("There are currently " + lst.size() + " items in your list.");
            } catch(IOException exc) {
                System.out.println("I/O Exception: " + exc);
            }

    }
    }

}
