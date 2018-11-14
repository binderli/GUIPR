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

public class SelectionAdapterOpen extends SelectionAdapter {

	private final FileDialog openDialog;
	private final Text text;
	private final CTabItem tab;

	public SelectionAdapterOpen(Shell shell, Text text, CTabFolder tabFolder) {
		openDialog = new FileDialog(shell, SWT.OPEN);
		this.text = text;
		this.tab = tabFolder.getSelection();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		openDialog.open();
		final String path = openDialog.getFilterPath();
		final String fileName = openDialog.getFileName();
		final String file = path + "\\" + fileName;
		System.out.println(file);
		text.setText(FileIO.read(file));
		tab.setText(fileName);

	}

}