/* 
 * This file contains the main logic behind the Duke chatbot.
 * 
 * @author: @rish-16
 * @version: CS2103, AY21/22 Semester 1
 */

import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

/**
 * This represents the Duke chatbot and its business logic.
 */
public class Duke {
    public static final String FILE_PATH = "../../../data/duke.txt";
    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        chatbot.greet();

        ArrayList<String> store = new ArrayList<String>();

        try {
            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                store.add(data); // store line in collection
            }
            myReader.close();
            chatbot.ingestData(store); // pass in tasks from flatfile memory
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (true) {
            // get user input until "bye" is typed
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            String input = sc.nextLine();

            if (input.equals("bye")) {
                // commit memory to flatfile after session ends
                chatbot.commitToMemory(FILE_PATH);

                // exit loop and close chatbot program
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }

            // handle user input – send to Chatbot brain
            try {
                chatbot.processInput(input);
            } catch (BotException err) {
                System.out.println(err);
            }
        }
    }
}