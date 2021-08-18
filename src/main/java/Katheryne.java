import java.io.*;
import java.util.*;

public class Katheryne {
    public static void main(String[] args) throws KatheryneExceptions {
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
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("Traveller, please specify by index which task you completed.");
                    }
                    int i = Integer.parseInt(keywordInput[1]);
                    if (i < lst.size()) {
                        lst.get(i - 1).markAsDone();
                        System.out.println(lst.get(i - 1));
                    }
                } else if (keywordInput[0].equalsIgnoreCase("delete")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("Traveller, please specify by index what I should delete.");
                    }
                    int i = Integer.parseInt(keywordInput[1]);
                    System.out.println("Okay, I'll delete the following item:");
                    if (i <= lst.size()) {
                        System.out.println(lst.remove(i - 1));
                    }
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Here's the list I've stored for you:");
                    for(int i = 1; i <= lst.size(); i++) {
                        System.out.println(i + ") " + lst.get(i - 1));
                    }
                } else if (keywordInput[0].equalsIgnoreCase("deadline")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("A deadline needs a description and a /by time to complete it");
                    }
                    String[] parsedDeadline = keywordInput[1].split(" /by ");
                    if (parsedDeadline.length == 2) {
                        lst.add(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                        System.out.println(parsedDeadline[0] + " added to your list, do by " + parsedDeadline[1]);
                    } else {
                        throw new KatheryneExceptions("A deadline needs a description and a /by time to complete it");
                    }
                } else if (keywordInput[0].equalsIgnoreCase("event")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("An event needs a description and an /at time when it occurs");
                    }
                    String[] parsedEvent = keywordInput[1].split(" /at ");
                    if (parsedEvent.length == 2) {
                        lst.add(new Event(parsedEvent[0], parsedEvent[1]));
                        System.out.println(parsedEvent[0] + " added to your list, do at " + parsedEvent[1]);
                    } else {
                        throw new KatheryneExceptions("An event needs a description and an /at time when it occurs");
                    }
                } else if (keywordInput[0].equalsIgnoreCase("todo")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("A todo needs a description ");
                    } else {
                        lst.add(new Todo(keywordInput[1]));
                        System.out.println("Todo item " + keywordInput[1] + " added to your list");
                    }
                } else {
                    throw new UnknownCommandException();
                }
                System.out.println("There are currently " + lst.size() + " items in your list.");
            } catch(IOException exc) {
                System.out.println("I/O Exception: " + exc);
            } catch(UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (KatheryneExceptions e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("You need to specify a number in the correct format. ERROR: " 
                        + e.getMessage());
            }

    }
    }

}
