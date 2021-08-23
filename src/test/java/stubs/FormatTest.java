package stubs;

import petal.components.Responses;

public class FormatTest {

    public String formatForTest(String message) {
        return Responses.LINE + "\n" + message.toString() + "\n" + Responses.LINE;
    }

}
