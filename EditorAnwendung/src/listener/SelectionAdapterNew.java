package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterNew extends SelectionAdapter
{

	private final CTabFolder tabFolder;
	private final Shell shell;
	private Text text;

	public SelectionAdapterNew(CTabFolder tabFolder)
	{
		this.tabFolder = tabFolder;
		shell = tabFolder.getShell();

	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		final CTabItem tab = new CTabItem(tabFolder, SWT.NONE);
		tab.setText("new");
		text = new Text(tabFolder, SWT.MULTI);
		tab.setControl(text);
		tabFolder.setSelection(tab);
	}

}