package duke;

import java.util.ArrayList;

public class EntryList extends ArrayList<Entry> {

    private int numberOfEntries;

    EntryList() {
        super();
        this.numberOfEntries = super.size();
    }

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

    public void addEntry(Entry entry) {
        super.add(numberOfEntries++, entry);
    }

    public void addEntry(Entry entry, String command, Ui ui) throws DukeException {
        if (!entry.isEmpty()){
            super.add(numberOfEntries++, entry);
            ui.addEntry(entry, numberOfEntries);
        } else {
            throw new DukeException("The " + command + " description can't be empty!");
        }
    }

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
