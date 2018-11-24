package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Text;

public class CTabElement {

	private static Text text;

	public static void createTabNew(CTabFolder tabFolder) {
		final CTabItem tab = new CTabItem(tabFolder, SWT.NONE);
		tab.setText("new");
		text = new Text(tabFolder, SWT.MULTI);
		tab.setControl(text);
		tabFolder.setSelection(tab);

	}

	public static void createTabOpen(CTabFolder tabFolder, String fileName, String fileContent) {
		final CTabItem tab = new CTabItem(tabFolder, SWT.NONE);
		tab.setText(fileName);
		text = new Text(tabFolder, SWT.MULTI);
		tab.setControl(text);
		tabFolder.setSelection(tab);
		text.setText(fileContent);

	}

}
