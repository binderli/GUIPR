package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SelectionAdapterQuit extends SelectionAdapter {

	private final Shell shell;

	public SelectionAdapterQuit(Shell shell) {
		this.shell = shell;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		int answer;
		final MessageBox quit = new MessageBox(this.shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		quit.setMessage("All unsaved changes will be deleted. Do you really want to quit?");

		switch (answer = quit.open()) {

		case SWT.YES:
			System.exit(0);
			break;
		case SWT.NO:
			break;
		}
		// System.exit(0);

	}

}
