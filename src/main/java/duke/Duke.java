package duke;

/**
 * This file contains the main logic behind the Duke chatbot.
 * 
 * @author: Rishabh Anand
 * @version: CS2103, AY21/22 Semester 1
 *
 */

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;

import duke.datastore.DataStore;
import duke.membuffer.MemoryBuffer;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {
    private MemoryBuffer memBuff;
    public Ui userInt;
    public DataStore dataStore;
    final public Parser parser;

    public Duke() {
        dataStore = new DataStore();
        memBuff = new MemoryBuffer("./data/duke.txt");
        parser = new Parser();
        userInt = new Ui();

        try {
            dataStore.ingestExternalSource(memBuff.readFile(), parser);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            dataStore = new DataStore();
        }
    }

    public String getResponse(String input) {
        String response = parser.execute(input, dataStore, userInt, memBuff);
        return response;
    }

//    public void run() {
//        userInt.greet();
//
//        while (true) {
//            try {
//                String userInput = userInt.getUserInput();
//                parser.execute(userInput, dataStore, userInt, memBuff);
//            } catch (IOException e) {
//                userInt.say(e.getMessage());
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Duke().run();
//    }
}