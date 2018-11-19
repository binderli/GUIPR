package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextColorDialog extends Dialog {

	private String info = null;
	private final Shell parent;

	private void createContents(Shell shellDialogWindow) {
		final FillLayout layout = new FillLayout(SWT.VERTICAL);
		shellDialogWindow.setLayout(layout);
		final Text textInput = new Text(shellDialogWindow, SWT.SINGLE);
		final Button btnOk = new Button(shellDialogWindow, SWT.PUSH);
		btnOk.setText("Ok");
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Achtung: this.info = ... funktioniert nicht:
				// this ist hier nicht das Dialogfenster, sondern
				// das Listener-Objekt
				info = textInput.getText();
				shellDialogWindow.dispose();
			}
		});

		final Button btnCancel = new Button(shellDialogWindow, SWT.PUSH);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				info = null;
				shellDialogWindow.dispose();
			}
		});

		shellDialogWindow.pack();
	}

	public TextColorDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
	}

	public String open() {
		final Shell shellDialogWindow = new Shell(parent);
		final Display display = parent.getDisplay();
		// Tut noch nichts
		createContents(shellDialogWindow);

		// EventLoop des Fensters
		shellDialogWindow.open();
		while (!shellDialogWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return info;
	}
}
