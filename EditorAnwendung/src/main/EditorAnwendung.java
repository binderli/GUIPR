package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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

import listener.SelectionAdapterNew;
import listener.SelectionAdapterOpen;
import listener.SelectionAdapterQuit;
import listener.SelectionAdapterSave;

public class EditorAnwendung {

	private Button buttonOpen, buttonSave; //Button für CoolBar
	private CoolBar coolBar;
	private Display display;
	
	
	private MenuItem[] editMenuItem;// Edit Menu
	private final String[] editMenuNames = { "&Text Color" };//Name von Edit Menu
	private MenuItem[] fileMenuItem; //File Menu
	private final String[] fileMenuNames = { "&New", "&Open...", "&Save...", "&Quit" }; //Names von file Menue 
	private MenuItem[] helpMenuItem;//help Menu
	private final String[] helpMenuNames = { "&Version" };//Name von help Menu
	private CoolItem itemOpen, itemSave; 
	
	private Menu layer1[];//deffiniert den bereich als Menu
	private Menu menuBar;//
	private MenuItem[] menuBarItem;// optionen der MenuBar(Edit,File,Help)
	private final String[] menuNames = { "&File", "&Edit", "&Help" };
	
	private Shell shell;
	private CTabItem tab; // einzelne Tabs
	private CTabFolder tabFolder; //Bereich für Tabs
	private Text text;//text in einem Tab 
	private TextColorDialog dlg;//Dialog feld für textfarbe

	public EditorAnwendung() {
		createDisplay();
		createShell();
		createMenu();
		createCoolBar();
		createTabFolder();
		createTab();
		createListeners();

	}

	private void createListeners() {

		// New
		fileMenuItem[0].addSelectionListener(new SelectionAdapterNew(tabFolder));
		// Open
		fileMenuItem[1].addSelectionListener(new SelectionAdapterOpen(shell, text, tabFolder));
		buttonOpen.addSelectionListener(new SelectionAdapterOpen(shell, text, tabFolder));
		// Save
		fileMenuItem[2].addSelectionListener(new SelectionAdapterSave(shell, text, tabFolder));
		buttonSave.addSelectionListener(new SelectionAdapterSave(shell, text, tabFolder));
		// Quit
		fileMenuItem[3].addSelectionListener(new SelectionAdapterQuit());
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

	private void createDisplay() {
		display = new Display();
	}

	private void createMenu() {

		menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
		menuBarItem = new MenuItem[menuNames.length];
		layer1 = new Menu[menuNames.length];

		for (int i = 0; i < menuNames.length; i++) {

			menuBarItem[i] = new MenuItem(menuBar, SWT.CASCADE);
			menuBarItem[i].setText(menuNames[i]);
			layer1[i] = new Menu(shell, SWT.DROP_DOWN);
			menuBarItem[i].setMenu(layer1[i]);

		}

		// File Menu
		fileMenuItem = new MenuItem[fileMenuNames.length];
		for (int i = 0; i < fileMenuNames.length; i++) {
			fileMenuItem[i] = new MenuItem(layer1[0], SWT.PUSH);
			fileMenuItem[i].setText(fileMenuNames[i]);
		}

		// Edit Menu

		editMenuItem = new MenuItem[editMenuNames.length];
		for (int i = 0; i < editMenuNames.length; i++) {
			editMenuItem[i] = new MenuItem(layer1[1], SWT.PUSH);
			editMenuItem[i].setText(editMenuNames[i]);
		}

		// Help Menu

		helpMenuItem = new MenuItem[helpMenuNames.length];
		for (int i = 0; i < helpMenuNames.length; i++) {
			helpMenuItem[i] = new MenuItem(layer1[2], SWT.PUSH);
			helpMenuItem[i].setText(helpMenuNames[i]);
		}

	}

	private void createShell() {
		shell = new Shell(display);
		final GridLayout shellLayout = new GridLayout(1, true);
		shell.setLayout(shellLayout);
	}

	public void createTabFolder() {
		tabFolder = new CTabFolder(shell, SWT.NONE);
		final GridData gDataTabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1); // x/y auf FILL setzen, grabExcess... auf true
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
