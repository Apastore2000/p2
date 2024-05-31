package it.unisa.control;

public class XSSSanitizer {
	public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
