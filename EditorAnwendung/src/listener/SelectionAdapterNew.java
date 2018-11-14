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
		this.text = new Text(tabFolder, SWT.LEFT | SWT.MULTI);
		this.tab.setControl(text);
		this.tab.setText("New");
		this.tabFolder.setSelection(tab);

	}

}
