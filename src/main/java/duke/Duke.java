/**
 * This file runs the Duke chatbot that makes use
 * of other files for logic processing.
 *
 * @author: Rishabh Anand
 * @version: CS2103, AY21/22 Semester 1
 */

package duke;

import java.io.FileNotFoundException;  // Import this class to handle errors
import duke.datastore.DataStore;
import duke.membuffer.MemoryBuffer;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {
    private final MemoryBuffer memBuff;
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
        return parser.execute(input, dataStore, memBuff);
    }
}