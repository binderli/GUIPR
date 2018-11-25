package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import main.FileIO;
import main.TabElement;
import XML.XMLread;

public class SelectionAdapterOpen extends SelectionAdapter {

	private final FileDialog openDialog;
	private final Text text;
	private final CTabItem tab;
	private CTabFolder parent;
	private Shell shell;


	public SelectionAdapterOpen(Shell shell, Text text, CTabFolder tabFolder) {
		this.parent = tabFolder;
		this.shell = shell;
		openDialog = new FileDialog(shell, SWT.OPEN);
		this.text = text;
		this.tab = tabFolder.getSelection();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		
		String file = null;
		XMLread reader;
		try {
			file = openDialog.open();
			switch(System.getProperty("os.name")){
			
			case "Mac OS X":
				System.out.println(file);
				//text.setText(FileIO.read(file));
				reader = new XMLread(file, shell);
				TabElement.createTab(parent, file, reader.getText(), reader.getColor());
				
				//tab.setText(fileName);
				break;
				
			
			case "Windows 10":
				System.out.println(file);
//				text.setText(FileIO.read(file));
//				tab.setText(fileName);
				reader = new XMLread(file, shell);
				TabElement.createTab(parent, file, reader.getText(), reader.getColor());
				break;
				
				
			default: break;
		}
		} catch (NullPointerException e1) {
			
		}
		
	
		
	}

}
