package listener;

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
	private final Text text;
	private final CTabItem tab;

	public SelectionAdapterSave(Shell shell, Text text, CTabFolder tabFolder) {
		this.saveDialog = new FileDialog(shell, SWT.SAVE);
		this.text = text;
		this.tab = tabFolder.getSelection();

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		saveDialog.open();
		final String path = saveDialog.getFilterPath();
		final String fileName = saveDialog.getFileName();
		final String file = path + "//" + fileName;
		FileIO.write(fileName, text.getText());
		this.tab.setText(fileName);

	}

}