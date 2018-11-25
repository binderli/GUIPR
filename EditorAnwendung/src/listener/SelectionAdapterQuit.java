package listener;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import XML.XMLwrite;

public class SelectionAdapterQuit implements SelectionListener, DisposeListener
{

	private CTabFolder allTabs;
	private ResourceBundle message;

	public SelectionAdapterQuit(CTabFolder tabs, ResourceBundle rb)
	{
		allTabs = tabs;
		message = rb;

	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		CTabItem[] allMyItems = allTabs.getItems();
		Shell shell = (Shell) allTabs.getParent();

		MessageBox question = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
		question.setMessage(message.getString("question"));
		int answerQuit = question.open();

		switch (answerQuit)
		{
			case SWT.YES:
				shell.dispose();
				break;
			case SWT.NO:
				// Schleife über alle Items
				for (CTabItem a : allMyItems)
				{
					// Textfeld aus allMyITems[i] abfragen mit getControl()
					Text text = (Text) a.getControl();
					FileDialog dlg = new FileDialog(shell, SWT.SAVE);
					try
					{
						String filename = dlg.open();
						// Inhalt aus Texfeld abfragen mit getText()
						String content = text.getText();
						if (filename != null)
						{
							Color c = text.getForeground();
							// XML Parser
							XMLwrite writer = new XMLwrite();
							writer.writeDown(filename, content, c);
							c = null;
						}
					} catch (NullPointerException ex)
					{
						// keine Name an datei angehängt
					}

				}
				// Ganz am Schluss: alles disposen mit shell.dispose()
				shell.dispose();
				break;
			case SWT.CANCEL:
				break;
		}

	}

	@Override
	public void widgetDisposed(DisposeEvent e)
	{
		CTabItem[] allMyItems = allTabs.getItems();
		Shell shell = (Shell) allTabs.getParent();

		MessageBox question = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
		question.setMessage(message.getString("question"));
		int answerQuit = question.open();

		switch (answerQuit)
		{
			case SWT.YES:
				shell.dispose();
				break;
			case SWT.NO:
				for (CTabItem a : allMyItems)
				{
					Text text = (Text) a.getControl();
					FileDialog dlg = new FileDialog(shell, SWT.SAVE);
					try
					{
						String filename = dlg.open();
						String content = text.getText();
						if (filename != null)
						{
							Color c = text.getForeground();
							// XML Parser
							XMLwrite writer = new XMLwrite();
							writer.writeDown(filename, content, c);
							c = null;
						}
					} catch (NullPointerException ex)
					{
						// keine Name an datei angehängt
					}

				}
				shell.dispose();
				break;
			case SWT.CANCEL:
				break;
		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e)
	{
		CTabItem[] allMyItems = allTabs.getItems();
		Shell shell = (Shell) allTabs.getParent();

		MessageBox question = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
		question.setMessage(message.getString("question"));
		int answerQuit = question.open();

		switch (answerQuit)
		{
			case SWT.YES:
				shell.dispose();
				break;
			case SWT.NO:
				for (CTabItem a : allMyItems)
				{
					Text text = (Text) a.getControl();
					FileDialog dlg = new FileDialog(shell, SWT.SAVE);
					try
					{
						String filename = dlg.open();
						String content = text.getText();
						if (filename != null)
						{
							Color c = text.getForeground();
							// XML Parser
							XMLwrite writer = new XMLwrite();
							writer.writeDown(filename, content, c);
							c = null;
						}
					} catch (NullPointerException ex)
					{
						// keine Name an datei angehängt
					}

				}
				shell.dispose();
				break;
			case SWT.CANCEL:
				break;
		}

	}

}
