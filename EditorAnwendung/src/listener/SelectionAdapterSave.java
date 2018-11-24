package listener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterSave extends SelectionAdapter {

	private final FileDialog saveDialog;
	private final CTabFolder tabFolder;
	private Text text;
	private final Shell shell;

	public SelectionAdapterSave(Shell shell, CTabFolder tabFolder) {
		this.saveDialog = new FileDialog(shell, SWT.SAVE);
		this.tabFolder = tabFolder;
		this.shell = shell;

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		int answer;
		FileWriter writer;
		File f;
		boolean b = true;
		final CTabItem tab = tabFolder.getSelection();
		text = (Text) tab.getControl();
		try {

			while (b) {
				final String file = saveDialog.open();
				final String fileName = saveDialog.getFileName();
				f = new File(file);
				if (f.exists()) {
					writer = new FileWriter(f);
					final MessageBox overwrite = new MessageBox(this.shell,
							SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
					overwrite.setMessage("The File already exists. Do you want to overwrite it?");
					switch (answer = overwrite.open()) {
					case SWT.YES:
						writer.write(text.getText());
						tab.setText(fileName);
						writer.close();
						b = false;
						break;

					case SWT.NO:
						b = true;
						break;

					case SWT.CANCEL:
						b = false;
						break;

					}
				} else {
					writer = new FileWriter(f);
					writer.write(text.getText());
					tab.setText(fileName);
					writer.close();
					b = false;
				}
			}
		} catch (final NullPointerException | IOException e1) {
			// Dialog: File wurde nicht gespeichert
			final MessageBox hint = new MessageBox(this.shell, SWT.ICON_INFORMATION | SWT.OK);
			hint.setMessage("File not saved!");
			hint.open();
		}

	}

}
