package tiger.utils;

public class StringUtils {

    public String removeLastSpaces(String input) {
        int last = input.length() - 1;
        while (input.charAt(last) == ' ') {
            last -= 1;
        }
        return input.substring(0, last + 1);
    }

    public String removeFrontSpaces(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        String cut = this.removeLastSpaces(reversed);
        String reversedCut = new StringBuilder(cut).reverse().toString();
        return reversedCut;
    }

    public String removeBackAndFrontSpaces(String input) {
        String cutBack = this.removeLastSpaces(input);
        String cutFront = this.removeFrontSpaces(cutBack);
        return cutFront;
    }

    public String capitaliseFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
