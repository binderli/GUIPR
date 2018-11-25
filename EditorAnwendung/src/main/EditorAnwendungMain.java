package main;

import java.util.Locale;
import java.util.ResourceBundle;

public class EditorAnwendungMain {

	public static void main(String[] args) {
		Locale locale;
		ResourceBundle messages;
		String language, country;
		if ( args . length != 2) {
			language = new String ("de");
			country = new String ("DE");
		} else {
			language = new String ( args [0]) ;
			country = new String ( args [1]) ;
		}
		locale = new Locale(language, country);
		messages = ResourceBundle.getBundle("MessageBundle", locale);
		
		EditorAnwendung gui = new EditorAnwendung(messages);
		gui.open();
		
	}

}
