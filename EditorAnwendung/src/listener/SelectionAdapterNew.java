package listener;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import main.CTabElement;

public class SelectionAdapterNew extends SelectionAdapter {

	private final CTabFolder tabFolder;
	private final Shell shell;
	private final int insertMark;

	public SelectionAdapterNew(CTabFolder tabFolder) {
		insertMark = tabFolder.getSelectionIndex();
		this.tabFolder = tabFolder;
		this.shell = tabFolder.getShell();

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		CTabElement.createTabNew(tabFolder);
	}

}
