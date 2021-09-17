package duke;

import java.util.ArrayList;

/**
 * Class that implements methods to store an Arraylist of Entries
 */
public class EntryList extends ArrayList<Entry> {

    private int numberOfEntries;

    /**
     * Constructor for an Entrylist.
     */
    EntryList() {
        super();
        this.numberOfEntries = super.size();
    }

    /**
     * Prints all the Entries stored currently.
     *
     * @param ui The UI object for the current Duke execution.
     * @throws DukeException Exception if no entries are to be displayed.
     */
    public void displayEntries(Ui ui) throws DukeException {
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                ui.printEntry(super.get(i), i + 1);
            }
            ui.endCommand();
        } else {
            throw new DukeException("No entries to display!");
        }
    }

    /**
     * Prints all the Entries stored currently.
     *
     * @throws DukeException Exception if no entries are to be displayed.
     */
    public String getEntries() throws DukeException {
        StringBuilder entryList = new StringBuilder();
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                entryList.append(i + 1).append(". ").append(super.get(i)).append("\n");
            }
            return entryList.toString();
        } else {
            throw new DukeException("No entries to display!");
        }
    }

    public void findEntry(String keyword, Ui ui) {
        int len = super.size();
        int count = 1;
        ui.listMatches();
        for (int i = 0; i < len; i++) {
            Entry currentEntry = super.get(i);
            if (currentEntry.contains(keyword)) {
                if (count == 1) {
                    ui.foundMatches();
                }
                ui.printEntry(currentEntry, count++);
            }
        }
        ui.endCommand();
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
     * @throws DukeException Error thrown when no entry exists for the given entryNumber.
     */
    public void markEntryAsDone(int entryNumber) throws DukeException {
        if (entryNumber > 0 && entryNumber <= numberOfEntries) {
            if (super.get(entryNumber - 1).setDone()) {
                System.out.println("Nice! I've marked this entry as done:\n");
                System.out.println("\t" + super.get(entryNumber - 1));
            } else {
                System.out.println("Entry is already marked as done!");
            }
        } else {
            throw new DukeException("There's no Entry corresponding to that Number!");
        }
    }

    /**
     * Marks a given entry number as done.
     *
     * @param entryNumber The number of the entry to be marked.
     * @return String for marked entry.
     * @throws DukeException Error thrown when no entry exists for the given entryNumber.
     */
    public String getEntryAsDone(int entryNumber, Ui ui) throws DukeException {
        StringBuilder output = new StringBuilder();
        if (entryNumber > 0 && entryNumber <= numberOfEntries) {
            if (super.get(entryNumber - 1).setDone()) {
                output.append(ui.getMarkedEntry());
                output.append("\t").append(ui.getPrintEntry(super.get(entryNumber - 1), entryNumber));
            } else {
                output.append(ui.getEntryThatIsMarked());
            }
            return output.toString();
        } else {
            throw new DukeException("There's no Entry corresponding to that Number!");
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
     * @param ui UI object to print Entry if added.
     * @throws DukeException Error thrown if Entry is empty.
     */
    public void addEntry(Entry entry, String command, Ui ui) throws DukeException {
        if (!entry.isEmpty()){
            super.add(numberOfEntries++, entry);
            ui.addEntry(entry, numberOfEntries);
        } else {
            throw new DukeException("The " + command + " description can't be empty!");
        }
    }

    /**
     * Adds given Entry to the List.
     * Prints Success Message if Successful.
     *
     * @param entry The Entry to be added.
     * @param command The command corresponding to the Entry.
     * @return String to show added Entry.
     * @throws DukeException Error thrown if Entry is empty.
     */
    public String getAddEntry(Entry entry, String command, Ui ui) throws DukeException {
        StringBuilder output = new StringBuilder();
        if (!entry.isEmpty()){
            super.add(numberOfEntries++, entry);
            output.append(ui.getAddEntry(entry, numberOfEntries));
        } else {
            throw new DukeException("The " + command + " description can't be empty!");
        }
        return output.toString();
    }

    /**
     * Deletes entry by index.
     *
     * @param index Index of Entry to be deleted.
     * @param ui UI Object to print deleted Entry message.
     * @throws DukeException Error thrown if no Entry corresponds to the index.
     */
    public void deleteEntry(int index, Ui ui) throws DukeException {
        if (super.isEmpty() || index < 1 || index > numberOfEntries) {
            throw new DukeException("Duke can't find anything to delete!");
        } else {
            Entry deletedEntry = super.remove(index - 1);
            numberOfEntries--;
            ui.showDeletedEntry(deletedEntry);
        }
    }

    /**
     * Deletes entry by index.
     *
     * @param index Index of Entry to be deleted.
     * @param ui UI Object to print deleted Entry message.
     * @return String to show deleted entry.
     * @throws DukeException Error thrown if no Entry corresponds to the index.
     */
    public String getDeleteEntry(int index, Ui ui) throws DukeException {
        if (super.isEmpty() || index < 1 || index > numberOfEntries) {
            throw new DukeException("Duke can't find anything to delete!");
        } else {
            Entry deletedEntry = super.remove(index - 1);
            numberOfEntries--;
            return ui.getDeletedEntry(deletedEntry);
        }
    }
}
