import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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

	private Display display;
	private Shell shell;
	private Menu menuBar;
	private Menu layer1[];
	private final String[] menuNames = { "&File", "&Edit", "&Help" };
	private final String[] fileMenuNames = { "&New", "&Open...", "&Save...", "&Quit" };
	private final String[] editMenuNames = { "&Text Color" };
	private final String[] helpMenuNames = { "&Version" };
	private MenuItem[] menuBarItem;
	private MenuItem[] fileMenuItem;
	private MenuItem[] editMenuItem;
	private MenuItem[] helpMenuItem;
	private CoolItem[] coolBarItem;
	private final String[] coolBarItemNames = { "Open", "Save" };
	private CTabFolder tabFolder;
	private CTabItem tab;
	private Text text;
	private Button[] buttons;

	private void createDisplay() {
		display = new Display();
	}

	private void createShell() {
		shell = new Shell(display);
		final GridLayout shellLayout = new GridLayout(1, true);
		shell.setLayout(shellLayout);
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

	private void createCoolBar() {

		final CoolBar coolBar = new CoolBar(shell, SWT.BORDER);
		final GridData gDataCoolBar = new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1);
		coolBar.setLayoutData(gDataCoolBar);

		coolBarItem = new CoolItem[coolBarItemNames.length];
		buttons = new Button[coolBarItemNames.length];
		for (int i = 0; i < coolBarItemNames.length; i++) {
			coolBarItem[i] = new CoolItem(coolBar, SWT.NONE | SWT.DROP_DOWN);
			buttons[i] = new Button(coolBar, SWT.PUSH);
			// button.setImage();
		}
		final Button testButton = new Button(coolBar, SWT.PUSH);

		testButton.setText("Hello");
		testButton.pack();

		coolBarItem[0].setControl(testButton);
		coolBar.pack();
	}

	private void createTab() {
		tabFolder = new CTabFolder(shell, SWT.NONE);
		// x/y auf FILL setzen, grabExcess... auf true
		final GridData gDataTabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tabFolder.setLayoutData(gDataTabFolder);

		tab = new CTabItem(tabFolder, SWT.NONE);
		tab.setText("New");
		createText();
		tab.setControl(text);

	}

	private void createText() {
		text = new Text(tabFolder, SWT.LEFT | SWT.MULTI);
	}

	public EditorAnwendung() {
		createDisplay();
		createShell();
		createMenu();
		createCoolBar();
		createTab();

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
