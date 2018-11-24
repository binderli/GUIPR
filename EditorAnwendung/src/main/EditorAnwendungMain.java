package main;

public class EditorAnwendungMain {

	public static void main(String[] args) {
		String language;
		String country;
		// Locale currentLocale;
		// final ResourceBundle messages;

		if (args.length != 2) {
			language = new String("en");
			country = new String("US");
		} else {
			language = new String(args[0]);
			country = new String(args[1]);
		}

		// currentLocale = new Locale(language, country);
		// messages = ResourceBundle.getBundle("MessageBundle", currentLocale);

		final EditorAnwendung gui = new EditorAnwendung();
		gui.open();

	}

}
