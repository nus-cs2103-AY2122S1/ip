public class Event extends Task {
    final private String time;

    public static Event of(String input) throws DukeException {
        String[] eachWord = input.split("/at");
        if (eachWord.length == 0 || eachWord[0].length() == 0 || eachWord[0].equals(" ")) {
            throw new DukeException("Description cannot be empty. Type description before /at");
        }
        if (eachWord.length == 1 || eachWord[1].length() == 0 || eachWord[1].equals(" ")) {
            throw new DukeException("The date for this deadline cannot be empty. Type date after /at");
        }
        return new Event(input);
    }

    private Event(String input) {
        super(input.split("/at")[0]);
        this.time = input.split("/at")[1];
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + String.format("(at:%s)", this.time));
    }
}
