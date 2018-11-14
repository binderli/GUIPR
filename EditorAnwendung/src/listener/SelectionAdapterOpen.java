package listener;

import org.eclipse.swt.SWT;
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

	public SelectionAdapterOpen(Shell shell, Text text, CTabItem tab) {
		openDialog = new FileDialog(shell, SWT.OPEN);
		this.text = text;
		this.tab = tab;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		openDialog.open();
		final String path = openDialog.getFilterPath();
		final String fileName = openDialog.getFileName();
		final String file = path + "\\" + fileName;
		System.out.println(file);
		final String content = FileIO.read(file);
		text.setText(content);
		tab.setText(fileName);

	}

}
