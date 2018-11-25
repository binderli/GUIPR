package listener;

import java.util.Properties;

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

public class SelectionAdapterSave extends SelectionAdapter {

	private final FileDialog saveDialog;
	private final Text text;
	private final CTabItem tab;
	private Shell shell;
	private CTabFolder parent;

	public SelectionAdapterSave(Shell shell, Text text, CTabFolder tabFolder) {
		this.parent = tabFolder;
		this.shell = shell;
		this.saveDialog = new FileDialog(shell, SWT.SAVE);
		this.text = text;
		this.tab = tabFolder.getSelection();

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		CTabItem item = parent.getSelection();
		Text text =  (Text) item.getControl();
		FileDialog fileSave = new FileDialog(shell, SWT.SAVE);
		int index;
		String n = null;
		try {
			String file = fileSave.open();
			if(file != null) {
				Color c = text.getForeground();
				
				//FileIO.write(fileName, text.getText());
				//item.setText(fileName);
				
				//XML Parster Write!
				XMLwrite writer = new XMLwrite();
				switch(System.getProperty("os.name")){
				
				case "Mac OS X":
					index = file.lastIndexOf("\\");
					n = file.substring(index + 1);
					file = "/" + n;
					FileIO.write(file, text.getText());
					this.tab.setText(n);
					System.out.println(file);
					break;
					
				
				case "Windows 10":
					index = file.lastIndexOf("\\");
					n = file.substring(index + 1);
					file = "\\" + n;
					FileIO.write(file, text.getText());
					this.tab.setText(n);
					System.out.println(file);
					break;
					
					default: break;
			}
				this.tab.setText(n);
				writer.writeDown(file, text.getText(), c);
				System.out.println(file);
			}
		}
		catch (NullPointerException ex) {	
			// wenn kein Name oder Datei gew�hlt worden ist!
		}
		
		
		
//		saveDialog.open();
//		final String path = saveDialog.getFilterPath();
//		final String fileName = saveDialog.getFileName();
//		String file;
//		switch(System.getProperty("os.name")){
//			
//			case "Mac OS X":
//				file = path + "/" + fileName;
//				FileIO.write(file, text.getText());
//				this.tab.setText(fileName);
//				System.out.println(file);
//				break;
//				
//			
//			case "Windows 10":
//				file = path + "\\" + fileName;
//				FileIO.write(file, text.getText());
//				this.tab.setText(fileName);
//				System.out.println(file);
//				break;
//				
//				default: break;
//		}
	}

}
