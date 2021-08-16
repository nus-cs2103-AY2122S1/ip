public class Deadline extends Task {
    final private String date;

    public static Deadline of(String input) throws DukeException {
        String[] eachWord = input.split("/by");
        if (eachWord.length == 0 || eachWord[0].length() == 0 || eachWord[0].equals(" ")) {
            throw new DukeException("Description cannot be empty. Type description before /by");
        }
        if (eachWord.length == 1 || eachWord[1].length() == 0 || eachWord[1].equals(" ")) {
            throw new DukeException("The date for this deadline cannot be empty. Type date after /by");
        }
        return new Deadline(input);
    }

    private Deadline(String input) {
        super(input.split("/by")[0]);
        this.date = input.split("/by")[1];
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + String.format("(by:%s)", this.date));
    }
}
