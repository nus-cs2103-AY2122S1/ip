package bubbles;

class IndexOutOfBoundsException extends Exception {
    public IndexOutOfBoundsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The number you have entered is out of bounds, please enter another number! ☹\n";
    }
}
