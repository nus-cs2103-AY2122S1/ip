package tiger.utils;

public class RemoveLastSpaces {

    // TODO: remove front spaces as well

    public RemoveLastSpaces() {
    }

    public String removeLastSpaces(String input) {
        int last = input.length() - 1;
        while (input.charAt(last) == ' ') {
            last -= 1;
        }
        return input.substring(0, last + 1);
    }
}
