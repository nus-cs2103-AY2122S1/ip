package luke;

import java.util.ArrayList;

/**
 * Class that implements methods to store an Arraylist of Entries
 */
public class EntryList extends ArrayList<Entry> {

    private int numberOfEntries;

    /**
     * Constructor for EntryList.
     */
    EntryList() {
        super();
        this.numberOfEntries = super.size();
    }

    /**
     * Prints all the Entries stored currently.
     *
     * @throws LukeException Exception if no entries are to be displayed.
     */
    public String getEntries() throws LukeException {
        StringBuilder entryList = new StringBuilder();
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                entryList.append(super.get(i)).append("\n");
            }
            return entryList.toString();
        } else {
            throw new LukeException("No entries to display!");
        }
    }

    /**
     * Finds entries with given keyword.
     * Searches for partial entries as well.
     *
     * @param keyword to search for.
     * @param ui Ui to return info string.
     * @return String of found entries.
     */
    public String getFindEntry(String keyword, Ui ui) {
        int len = super.size();
        int count = 1;
        StringBuilder output = new StringBuilder(ui.getListMatches());
        for (int i = 0; i < len; i++) {
            Entry currentEntry = super.get(i);
            if (currentEntry.contains(keyword)) {
                if (count == 1) {
                    output.append(ui.getFoundMatches());
                }
                output.append(ui.getPrintEntry(currentEntry, count++));
            }
        }
        return output.toString();
    }

    /**
     * Marks a given entry number as done.
     *
     * @param entryNumber The number of the entry to be marked.
     * @return String for marked entry.
     * @throws LukeException Error thrown when no entry exists for the given entryNumber.
     */
    public String getEntryAsDone(int entryNumber) throws LukeException {
        StringBuilder output = new StringBuilder();
        if (entryNumber > 0 && entryNumber <= numberOfEntries) {
            if (super.get(entryNumber - 1).setDone()) {
                output.append("Nice! I've marked this entry as done:");
                output.append("\t").append(super.get(entryNumber - 1));
            } else {
                output.append("Entry is already marked as done!");
            }
            return output.toString();
        } else {
            throw new LukeException("There's no Entry corresponding to that Number!");
        }
    }

    /**
     * Adds given Entry to the List when adding from memory.
     *
     * @param entry Entry to be added.
     */
    public void addEntry(Entry entry) {
        super.add(numberOfEntries++, entry);
    }

    /**
     * Adds given Entry to the List.
     * Prints Success Message if Successful.
     *
     * @param entry The Entry to be added.
     * @param command The command corresponding to the Entry.
     * @return String to show added Entry.
     * @throws LukeException Error thrown if Entry is empty.
     */
    public String getAddEntry(Entry entry, String command, Ui ui) throws LukeException {
        StringBuilder output = new StringBuilder();
        if (!entry.isEmpty()){
            super.add(numberOfEntries++, entry);
            output.append(ui.getAddEntry(entry, numberOfEntries));
        } else {
            throw new LukeException("The " + command + " description can't be empty!");
        }
        return output.toString();
    }

    /**
     * Deletes entry by index.
     *
     * @param index Index of Entry to be deleted.
     * @param ui UI Object to print deleted Entry message.
     * @return String to show deleted entry.
     * @throws LukeException Error thrown if no Entry corresponds to the index.
     */
    public String getDeleteEntry(int index, Ui ui) throws LukeException {
        if (super.isEmpty() || index < 1 || index > numberOfEntries) {
            throw new LukeException("Duke can't find anything to delete!");
        } else {
            Entry deletedEntry = super.remove(index - 1);
            numberOfEntries--;
            return ui.getDeletedEntry(deletedEntry);
        }
    }
}