/* 
 * This file contains the main logic behind the Duke chatbot.
 * 
 * @author: @rish-16
 * @version: CS2103, AY21/22 Semester 1
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner;

/**
 * This represents the Duke chatbot and its business logic.
 */
public class Duke {
    private MemoryBuffer memBuff;
    private Ui userInt;
    private DataStore dataStore;
    private Parser parser;

    public Duke(String filePath) {
        dataStore = new DataStore();
        memBuff = new MemoryBuffer(filePath);
        parser = new Parser();
        userInt = new Ui();
        ArrayList<String> store;

        try {
            store = memBuff.readFile();
            dataStore.ingestExternalSource(store, parser);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            dataStore = new DataStore();
        }
    }

    public void run() {
        userInt.greet();
        boolean exit = false;

        while (!exit) {
            try {
                String userInput = userInt.getUserInput();
                parser.execute(userInput, dataStore, userInt, memBuff);
            } catch (IOException e) {
                userInt.say(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}