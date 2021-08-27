package duke;

import java.util.ArrayList;

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
        ui.end();
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
                System.out.println("Nice! I've marked this entry as done:");
                System.out.println("\t" + super.get(entryNumber - 1));
            } else {
                System.out.println("Entry is already marked as done!");
            }
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
}
