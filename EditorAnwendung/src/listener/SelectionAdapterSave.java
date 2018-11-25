package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import XML.XMLwrite;
import main.FileIO;

public class SelectionAdapterSave extends SelectionAdapter
{
	private final CTabItem tab;
	private Shell shell;
	private CTabFolder tabFolder;
	private FileDialog saveDialog;

	public SelectionAdapterSave(Shell shell, Text text, CTabFolder tabFolder)
	{
		tabFolder = tabFolder;
		this.shell = shell;
		saveDialog = new FileDialog(shell, SWT.SAVE);
		tab = tabFolder.getSelection();

	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		CTabItem item = tabFolder.getSelection();
		Text text = (Text) item.getControl();
		FileDialog fileSave = new FileDialog(shell, SWT.SAVE);
		int index;
		String n = null;
		try
		{
			String file = fileSave.open();
			if (file != null)
			{
				Color c = text.getForeground();

				// XML ParsÏer
				XMLwrite writer = new XMLwrite();
				switch (System.getProperty("os.name"))
				{

					case "Mac OS X":
						index = file.lastIndexOf("\\");
						n = file.substring(index + 1);
						file = "/" + n;
						FileIO.write(file, text.getText());
						tab.setText(n);
						System.out.println(file);
						break;

					case "Windows 10":
						index = file.lastIndexOf("\\");
						n = file.substring(index + 1);
						file = "\\" + n;
						FileIO.write(file, text.getText());
						tab.setText(n);
						System.out.println(file);
						break;

					default:
						break;
				}
				tab.setText(n);
				writer.writeDown(file, text.getText(), c);
				System.out.println(file);
			}
		} catch (NullPointerException ex)
		{
			// keine Name an datei angehängt
		}
	}

}
