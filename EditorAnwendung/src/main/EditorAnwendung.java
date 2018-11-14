import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
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

public class EditorAnwendung {

	private Button buttonOpen, buttonSave;
	private CoolBar coolBar;
	private Display display;

	private MenuItem[] editMenuItem;
	private final String[] editMenuNames = { "&Text Color" };
	private MenuItem[] fileMenuItem;
	private final String[] fileMenuNames = { "&New", "&Open...", "&Save...", "&Quit" };
	private MenuItem[] helpMenuItem;
	private final String[] helpMenuNames = { "&Version" };
	private CoolItem itemOpen, itemSave;
	private Menu layer1[];

	private Menu menuBar;
	private MenuItem[] menuBarItem;
	private final String[] menuNames = { "&File", "&Edit", "&Help" };
	private Shell shell;

	private CTabItem tab;
	private CTabFolder tabFolder;
	private Text text;

	public EditorAnwendung() {
		createDisplay();
		createShell();
		createMenu();
		createCoolBar();
		createTab();

	}

	public void createCoolBar() {
		// Coolbar erstellen
		coolBar = new CoolBar(shell, SWT.BORDER);
		// Button für Öffnen erstellen
		itemOpen = new CoolItem(coolBar, SWT.NONE);
		buttonOpen = new Button(coolBar, SWT.PUSH);
		Image openImage = new Image(display, "Pictures/Open.png");
		buttonOpen.setImage(openImage);
		itemOpen.setPreferredSize(itemOpen.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		itemOpen.setControl(buttonOpen);

		// Button für Speichern erstellen
		itemSave = new CoolItem(coolBar, SWT.NONE);
		buttonSave = new Button(coolBar, SWT.PUSH);
		Image saveImage = new Image(display, "Pictures/Save.png");
		buttonSave.setImage(saveImage);
		itemSave.setPreferredSize(itemSave.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		itemSave.setControl(buttonSave);
		coolBar.pack();
	}

	//	private void createCoolBar() {
	//
	//		coolBar = new CoolBar(shell, SWT.BORDER);
	//		final GridData gDataCoolBar = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
	//		coolBar.setLayoutData(gDataCoolBar);
	//
	//		coolBarItem = new CoolItem[coolBarItemNames.length];
	//
	//		for (int i = 0; i < coolBarItemNames.length; i++) {
	//			coolBarItem[i] = new CoolItem(coolBar, SWT.NONE);
	//			buttons[i] = new Button(coolBar, SWT.PUSH);
	//			Image buttonImage = new Image(display, "Pictures/Open.jpg");
	//			buttons[i].setImage(buttonImage);
	//
	//		}
	//		coolBar.pack();
	//
	//	}

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

	private void createTab() {
		tabFolder = new CTabFolder(shell, SWT.MULTI);
		final FillLayout tabFolderLayout = new FillLayout();
		tabFolderLayout.type = SWT.VERTICAL | SWT.HORIZONTAL;
		tabFolder.setLayout(tabFolderLayout);
		// x/y auf FILL setzen, grabExcess... auf true

		final GridData gDataTabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tabFolder.setLayoutData(gDataTabFolder);

		tab = new CTabItem(tabFolder, SWT.NONE);
		createText();
		tab.setText("New");
		tab.setControl(text);

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