public class EmptyDescriptionException extends Exception {
    @Override
    public String toString() {
        return this.getMessage();
    }
}
