package listener;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SelectionAdapterQuit extends SelectionAdapter {

	@Override
	public void widgetSelected(SelectionEvent e) {

		System.exit(0);

	}

}
