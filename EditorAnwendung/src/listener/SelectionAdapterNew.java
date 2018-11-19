package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterNew extends SelectionAdapter {

	private final CTabFolder tabFolder;
	private CTabItem tab;
	private Text text;

	public SelectionAdapterNew(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		tab = new CTabItem(tabFolder, SWT.NONE);
		text = new Text(tabFolder, SWT.LEFT | SWT.MULTI);
		tab.setText("New");
		tab.setControl(text);
		tabFolder.setSelection(tab);
	}

}
