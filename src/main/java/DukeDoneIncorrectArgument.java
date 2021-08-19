public class DukeDoneIncorrectArgument extends DukeIncorrectInputs {
    public DukeDoneIncorrectArgument() {
        super("\t⚠️ After the keyword 'done', you have to enter an integer.",
                new IllegalArgumentException());
    }
}
