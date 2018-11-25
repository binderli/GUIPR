package listener;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SelectionAdapterHelp extends SelectionAdapter
{
	private Shell shell;
	private ResourceBundle message;

	public SelectionAdapterHelp(Shell shell, ResourceBundle rb)
	{
		message = rb;
		this.shell = shell;
	}

	// Helpinfo aus MessageBundle auslesen
	@Override
	public void widgetSelected(SelectionEvent e)
	{
		MessageBox information = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
		information.setMessage(message.getString("helpInfo"));
		information.open();
	}
}
