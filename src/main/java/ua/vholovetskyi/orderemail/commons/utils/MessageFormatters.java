package ua.vholovetskyi.orderemail.commons.utils;

public final class MessageFormatters {

    private MessageFormatters() {
        throw new UnsupportedOperationException("Cannot instantiate MessageFormatter");
    }

    public static final String EMPTY_MSG = null;
    public static final String FORMAT_MSG = "Exception class: %s. %s";

    public static String formatErrorMessage(String clazzName, String message) {
        return FORMAT_MSG.formatted(clazzName, message);
    }
}
