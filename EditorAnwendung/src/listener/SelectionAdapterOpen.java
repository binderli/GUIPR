package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import XML.XMLread;
import main.TabElement;

public class SelectionAdapterOpen extends SelectionAdapter
{

	private final FileDialog openDialog;
	private final CTabItem tab;
	private CTabFolder TabFolder;
	private Shell shell;

	public SelectionAdapterOpen(Shell shell, Text text, CTabFolder tabFolder)
	{
		TabFolder = tabFolder;
		this.shell = shell;
		openDialog = new FileDialog(shell, SWT.OPEN);
		tab = tabFolder.getSelection();
	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{

		String file = null;
		XMLread read;
		try
		{
			file = openDialog.open();
			System.out.println(file);
			read = new XMLread(file, shell);
			TabElement.createTab(TabFolder, file, read.getText(), read.getColor());
		} catch (NullPointerException e1)
		{

		}

	}

}
