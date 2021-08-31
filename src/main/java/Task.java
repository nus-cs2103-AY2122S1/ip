public abstract class Task {
    protected String desc;
    protected boolean isComplete;

    public Task(String desc) throws DukeException.EmptyDescriptionException {
        this.desc = desc.trim();
        if (desc.trim().length() == 0) {
            throw new DukeException.EmptyDescriptionException();
        }
        this.isComplete = false;
    }

    public Task(boolean isComplete, String desc) throws DukeException.EmptyDescriptionException {
        this.desc = desc.trim();
        if (desc.trim().length() == 0) {
            throw new DukeException.EmptyDescriptionException();
        }
        this.isComplete = isComplete;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    public String getRepr() {
        return String.format("%d|%s", this.isComplete ? 1 : 0, this.desc);
    };

    public static Task parseRepr(String s) throws DukeException {
        if (!s.matches("[TED][|][01][|].+[|].*")) {
            throw new DukeException.ParseException(s);
        }
        String[] arr = s.split("[|]");
        char ch = arr[0].charAt(0);
        boolean isComplete = arr[1].charAt(0) == '1';
        String desc = arr[2];
        String extra = arr[3];
        Task t;
        switch (ch) {
        case 'E':
            t = new Event(isComplete, desc, extra);
            break;
        case 'D':
            t = new Deadline(isComplete, desc, extra);
            break;
        case 'T':
            t = new Todo(isComplete, desc);
            break;
        default:
            throw new DukeException.ParseException(s); // shouldnt come here
        }
        return t;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isComplete ? 'X' : ' ', this.desc);
    }
}