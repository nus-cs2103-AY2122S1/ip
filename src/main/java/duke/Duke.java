package duke;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        ChatBot bot = new ChatBot();
        Parser parser = new Parser();
        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        boolean running = true;

        while (running) {
            try{
                int temp = parser.parse(input, bot);
                if (temp == 0) {
                    running = false;
                    break;
                }
                input = sc.nextLine();

            } catch (DukeException e) {
                bot.handleErrorMessage(e.getMessage());
                input = sc.nextLine();
            } catch (NumberFormatException e) {
                bot.handleErrorMessage("Please enter a valid number after done");
                input = sc.nextLine();
            }
        }
        sc.close();
    }
}


