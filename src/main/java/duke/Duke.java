package duke;

/**
 * This file contains the main logic behind the Duke chatbot.
 *
 * @author: Rishabh Anand
 * @version: CS2103, AY21/22 Semester 1
 */

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.File;
import duke.datastore.DataStore;
import duke.membuffer.MemoryBuffer;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {
    final private MemoryBuffer memBuff;
    public Ui userInt;
    public DataStore dataStore;
    final public Parser parser;

    private static final String FILEPATH = "./data/duke.txt";

    public Duke() {
        dataStore = new DataStore();
        memBuff = new MemoryBuffer(FILEPATH);
        parser = new Parser();
        userInt = new Ui();

        File f = new File(FILEPATH);
        if(f.exists() && !f.isDirectory()) {
            try {
                dataStore.ingestExternalSource(memBuff.readFile(), parser);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
        } else {
            try {
                dataStore.createFile(FILEPATH);
            } catch (IOException e) {
                System.out.println("Something went wrong. " + e);
            }
        }
    }

    public String getResponse(String input) {
        return parser.execute(input, dataStore, memBuff);
    }
}