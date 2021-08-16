public class Parser {
    public Parser() {

    };

    public String[] parse(String item) {
        String[] result = new String[]{"", "", ""};
        String[] split = item.split(" ");
        int firstWordLength = split[0].length();
        result[0] = split[0];

        String remainder = "";
        int separator = 0;
        if (split.length > 1) {
            remainder = item.substring(firstWordLength + 1);
            for (int i = 0; i < remainder.length(); i++) {
                if (Character.toString(remainder.charAt(i)).equals("/")) {
                    separator = i;
                }
            }
        }
        if (separator == 0) {
            result[1] = remainder;
        } else {
            result[1] = remainder.substring(0, separator);
            result[2] = remainder.substring(separator + 4);
        }
//        System.out.println(result[0]);
//        System.out.println(result[1]);
//        System.out.println(result[2]);
        return result;
    }

    public static void main(String[] args) {
        Parser p = new Parser();
        p.parse("deadline return book /by Sunday");
    }
}
