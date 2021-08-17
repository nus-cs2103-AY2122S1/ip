public class Entry {

    private String entry;

    private boolean isDone;

    Entry() {
        this.entry = "";
        this.isDone = false;
    }

    Entry(String value) {
        this.entry = value;
        this.isDone = false;
    }

    public void revertEntry() {
        this.isDone = !this.isDone;
    }

    public boolean markEntryAsDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return this.isDone;
        }
    }

    public String getEntryOnly() {
        return this.entry;
    }

    @Override
    public String toString () {
        char isDoneDisplay = this.isDone ? 'X' : ' ';
        return ("[" + isDoneDisplay + "] " + entry);
    }
}