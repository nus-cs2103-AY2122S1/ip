package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import duke.command.Commands;
import duke.exception.DukeException;

/**
 * Storage class that manages the saving and loading of the task list.
 */
public class AliasStorage {
    private static final File SAVE_FILE = new File("aliases.csv");
    private static final HashMap<String, List<String>> LOADED_ALIASES = new HashMap<>();

    /**
     * Loads all aliases from the saveFile.
     */
    public static void load() {
        Scanner sc;

        try {
            sc = new Scanner(SAVE_FILE);
        } catch (FileNotFoundException e) {
            // no save data, nothing to load
            return;
        }

        while (sc.hasNextLine()) {
            String[] datas = sc.nextLine().split(",");
            String key = datas[0];

            List<String> values = new ArrayList<>();
            for (int i = 1; i < datas.length; i++) {
                values.add(datas[i]);
            }

            LOADED_ALIASES.put(key, values);
        }

        // adds these aliases to the commands
        Commands.loadAliases();
    }

    /**
     * Retrieves loaded aliases for the specified command.
     *
     * @param mainCommand The default command for the action.
     * @return All aliases for the command loaded.
     */
    public static List<String> getLoadedAliases(String mainCommand) {
        if (!LOADED_ALIASES.containsKey(mainCommand)) {
            return new ArrayList<>();
        }
        return LOADED_ALIASES.get(mainCommand);
    }

    /**
     * Saves the command aliases into the specified file.
     *
     * @throws DukeException Any exception caught that has to do with the I/O.
     */
    public static void save() throws DukeException {
        String content = Commands.getAliasSaves();

        try {
            // create the file if it does not exist
            SAVE_FILE.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (FileWriter fw = new FileWriter(SAVE_FILE)) {
            fw.write(content);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
