package listener;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import main.FileIO;

public class SelectionAdapterSave extends SelectionAdapter {

	private final FileDialog saveDialog;
	private final CTabFolder tabFolder;
	private Text text;

	public SelectionAdapterSave(Shell shell, CTabFolder tabFolder) {
		this.saveDialog = new FileDialog(shell, SWT.SAVE);
		this.tabFolder = tabFolder;

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		final CTabItem tab = tabFolder.getSelection();
		text = (Text) tab.getControl();
		saveDialog.open();
		final String path = saveDialog.getFilterPath();
		final String fileName = saveDialog.getFileName();
		final String file = path + File.separator + fileName;
		tab.setText(fileName);
		System.out.println(tab.getText());
		FileIO.write(file, text.getText());

	}

}
