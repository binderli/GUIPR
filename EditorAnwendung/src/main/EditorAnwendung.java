package main;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import listener.SelectionAdapterHelp;
import listener.SelectionAdapterNew;
import listener.SelectionAdapterOpen;
import listener.SelectionAdapterQuit;
import listener.SelectionAdapterSave;

public class EditorAnwendung {

	private Button buttonOpen, buttonSave;
	private CoolBar coolBar;
	private CoolItem itemOpen, itemSave;
	private Display display;

//	private MenuItem[] editMenuItem;
//	private final String[] editMenuNames = { "&Text Color" };
//	private MenuItem[] fileMenuItem;
//	private final String[] fileMenuNames = { "&New", "&Open...", "&Save...", "&Quit" };
//	private MenuItem[] helpMenuItem;
//	private final String[] helpMenuNames = { "&Version" };

//	private Menu layer1[];

	private Menu menuBar;
//	private MenuItem[] menuBarItem;
//	private final String[] menuNames = { "&File", "&Edit", "&Help" };
	private Shell shell;

	private CTabItem tab;
	private CTabFolder tabFolder;
	private Text text;
	private TextColorDialog dlg;
	
	private Color color;
	private ResourceBundle message;
	  
	private MenuItem fileTitle, editTitle, helpTitle;
    private Menu fileMenu, editMenu, helpMenu;
    private MenuItem fileNewItem, fileOpenItem, fileSaveItem, fileQuitItem;
    private MenuItem editTextColorItem;
    private MenuItem helpHelpItem;  

	public EditorAnwendung(ResourceBundle rb) {
		createDisplay(rb);
		createShell();
		createMenu();
		createCoolBar();
		createTabFolder();
		createTab();
		createListeners();

	}

	private void createListeners() {

		// New
				fileNewItem.addSelectionListener(new SelectionAdapterNew(tabFolder));
				// Open
				fileOpenItem.addSelectionListener(new SelectionAdapterOpen(shell, text, tabFolder));
				buttonOpen.addSelectionListener(new SelectionAdapterOpen(shell, text, tabFolder));
				// Save
				fileSaveItem.addSelectionListener(new SelectionAdapterSave(shell, text, tabFolder));
				buttonSave.addSelectionListener(new SelectionAdapterSave(shell, text, tabFolder));
				// Quit
				fileQuitItem.addSelectionListener(new SelectionAdapterQuit(tabFolder, message));
				//Help
				helpHelpItem.addSelectionListener(new SelectionAdapterHelp(shell, message));
				
				editTextColorItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						ColorEditor edit = new ColorEditor(shell, shell.getStyle(), message);
						color = (Color) edit.open();
						Text text =  (Text) tabFolder.getSelection().getControl();
						text.setForeground(color);
					}
					});
				
				shell.addDisposeListener(new SelectionAdapterQuit(tabFolder, message));
					
		
//		// New
//		fileMenuItem[0].addSelectionListener(new SelectionAdapterNew(tabFolder));
//		// Open
//		fileMenuItem[1].addSelectionListener(new SelectionAdapterOpen(shell, text, tabFolder));
//		buttonOpen.addSelectionListener(new SelectionAdapterOpen(shell, text, tabFolder));
//		// Save
//		fileMenuItem[2].addSelectionListener(new SelectionAdapterSave(shell, text, tabFolder));
//		buttonSave.addSelectionListener(new SelectionAdapterSave(shell, text, tabFolder));
//		// Quit
//		fileMenuItem[3].addSelectionListener(new SelectionAdapterQuit());
//		editMenuItem[0].addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				ColorEditor edit = new ColorEditor(shell, shell.getStyle(), message);
//				color = (Color) edit.open();
//				Text text =  (Text) tabFolder.getSelection().getControl();
//				text.setForeground(color);
//			}
		
	}

	public void createCoolBar() {
		// final Image image =
		// PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		// Coolbar erstellen
		coolBar = new CoolBar(shell, SWT.HORIZONTAL);
		// Button fuer Oeffnen erstellen
		itemOpen = new CoolItem(coolBar, SWT.NONE);
		buttonOpen = new Button(coolBar, SWT.PUSH);

		// buttonOpen.setImage(image);
		buttonOpen.setText("Open");
		itemOpen.setPreferredSize(itemOpen.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		itemOpen.setControl(buttonOpen);

		// Button fuer Speichern erstellen
		itemSave = new CoolItem(coolBar, SWT.NONE);
		buttonSave = new Button(coolBar, SWT.PUSH);
		new Image(display, "Pictures/Save.png");
		// buttonSave.setImage(saveImage);
		buttonSave.setText("Save");
		itemSave.setPreferredSize(itemSave.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		itemSave.setControl(buttonSave);
		coolBar.pack();
	}

	// private void createCoolBar() {
	//
	// coolBar = new CoolBar(shell, SWT.BORDER);
	// final GridData gDataCoolBar = new GridData(SWT.FILL, SWT.FILL, true, false,
	// 1, 1);
	// coolBar.setLayoutData(gDataCoolBar);
	//
	// coolBarItem = new CoolItem[coolBarItemNames.length];
	//
	// for (int i = 0; i < coolBarItemNames.length; i++) {
	// coolBarItem[i] = new CoolItem(coolBar, SWT.NONE);
	// buttons[i] = new Button(coolBar, SWT.PUSH);
	// Image buttonImage = new Image(display, "Pictures/Open.jpg");
	// buttons[i].setImage(buttonImage);
	//
	// }
	// coolBar.pack();
	//
	// }

	private void createDisplay(ResourceBundle rb) {
		display = new Display();
		this.message = rb;
	}

	private void createMenu() {

		  menuBar = new Menu(shell, SWT.BAR);
		    shell.setMenuBar(menuBar);
		    
		    // Toolbar mit Items bef�llen
		    fileTitle = new MenuItem(menuBar, SWT.CASCADE);
		    fileTitle.setText(message.getString("menuFile"));
		 	fileMenu = new Menu(shell, SWT.DROP_DOWN);
		 	fileTitle.setMenu(fileMenu);
		 
		 	editTitle = new MenuItem(menuBar, SWT.CASCADE);
		 	editTitle.setText(message.getString("menuEdit"));
		 	editMenu = new Menu(shell, SWT.DROP_DOWN);
		 	editTitle.setMenu(editMenu);
		 	
		 	helpTitle = new MenuItem(menuBar, SWT.CASCADE);
		 	helpTitle.setText(message.getString("menuHelp"));
		 	helpMenu = new Menu(shell, SWT.DROP_DOWN);
		 	helpTitle.setMenu(helpMenu);
		 	
		 	// Item File mit Elemte bef�llen
		 	fileNewItem = new MenuItem(fileMenu, SWT.PUSH);
		 	fileNewItem.setText(message.getString("fileNewItem"));
		 	fileNewItem.setAccelerator(SWT.CTRL + 'N');
		 	
		 	fileOpenItem = new MenuItem(fileMenu, SWT.PUSH);
		 	fileOpenItem.setText(message.getString("fileOpenItem"));
		 	fileOpenItem.setAccelerator(SWT.CTRL + 'O');
		 	
		 	fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		 	fileSaveItem.setText(message.getString("fileSaveItem"));
		 	fileSaveItem.setAccelerator(SWT.CTRL + 'S');
		 	
		 	fileQuitItem = new MenuItem(fileMenu, SWT.PUSH);
		 	fileQuitItem.setText(message.getString("fileQuitItem"));
		 	fileQuitItem.setAccelerator(SWT.CTRL + 'Q');
		 	
		 	// Item Edit mit Elemente bef�llen
		 	editTextColorItem = new MenuItem(editMenu, SWT.PUSH);
		 	editTextColorItem.setText(message.getString("editColorItem"));
		 	editTextColorItem.setAccelerator(SWT.CTRL + 'C');
		 	
		 	// Item Help mit Elemente bef�llen
		 	helpHelpItem = new MenuItem(helpMenu, SWT.PUSH);
		 	helpHelpItem.setText(message.getString("helpItem"));
		 	helpHelpItem.setAccelerator(SWT.CTRL + 'V');
		
		
//		menuBar = new Menu(shell, SWT.BAR);
//		shell.setMenuBar(menuBar);
//		menuBarItem = new MenuItem[menuNames.length];
//		layer1 = new Menu[menuNames.length];
//
//		for (int i = 0; i < menuNames.length; i++) {
//
//			menuBarItem[i] = new MenuItem(menuBar, SWT.CASCADE);
//			menuBarItem[i].setText(menuNames[i]);
//			layer1[i] = new Menu(shell, SWT.DROP_DOWN);
//			menuBarItem[i].setMenu(layer1[i]);
//
//		}
//
//		// File Menu
//		fileMenuItem = new MenuItem[fileMenuNames.length];
//		for (int i = 0; i < fileMenuNames.length; i++) {
//			fileMenuItem[i] = new MenuItem(layer1[0], SWT.PUSH);
//			fileMenuItem[i].setText(message.getString("menuFile"));
//		}
//
//		// Edit Menu
//
//		editMenuItem = new MenuItem[editMenuNames.length];
//		for (int i = 0; i < editMenuNames.length; i++) {
//			editMenuItem[i] = new MenuItem(layer1[1], SWT.PUSH);
//			editMenuItem[i].setText(editMenuNames[i]);
//		}
//
//		// Help Menu
//
//		helpMenuItem = new MenuItem[helpMenuNames.length];
//		for (int i = 0; i < helpMenuNames.length; i++) {
//			helpMenuItem[i] = new MenuItem(layer1[2], SWT.PUSH);
//			helpMenuItem[i].setText(helpMenuNames[i]);
//		}

	}

	private void createShell() {
		shell = new Shell(display);
		final GridLayout shellLayout = new GridLayout(1, true);
		shell.setLayout(shellLayout);
	}

	public void createTabFolder() {
		tabFolder = new CTabFolder(shell, SWT.NONE);

		// x/y auf FILL setzen, grabExcess... auf true
		final GridData gDataTabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tabFolder.setLayoutData(gDataTabFolder);
	}

	public void createTab() {
		tab = new CTabItem(tabFolder, SWT.NONE);
		createText();
		tab.setText("New");
		tab.setControl(text);
		tabFolder.setSelection(tab);
	}

	private void createText() {
		text = new Text(tabFolder, SWT.LEFT | SWT.MULTI);
	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			} // end if
		} // end while
	}// end method

}
