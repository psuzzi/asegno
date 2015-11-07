package my.example.jface.standalone;

import java.awt.Dialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SimpleApplicationWindow{

	public static void main(String[] args) {
		openDialog();
	}

	static void openDialog() {
		Display display = new Display();
		Shell shell = new Shell(display);
		MessageDialog.openConfirm(null, "Confirm", "BellaCiao");
		display.dispose();
		System.out.println("Terminated");
	}

	static void openShell(){
		Display display = new Display();
		Shell shell = new Shell(display);

		shell.setText("Simple SWT Shell");

		shell.open();

		// eventloop

		while (!shell.isDisposed()) {
			if(!display.readAndDispatch()){
				// no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();
	}

	static void openTitleAreaDialog() {
		Display display = new Display();
		Shell shell = new Shell(display);

		TitleAreaDialog titleAreaDialog = new TitleAreaDialog(shell);
		titleAreaDialog.create();
		titleAreaDialog.setTitle("Title Area Dialog Title");
		titleAreaDialog.setMessage("Bella ciao, this is a title area dialog message");
		if (titleAreaDialog.open() == Dialog.OK) {
			System.out.println("Ok Pressed");
		}
		display.dispose();
		System.out.println("Terminated");
	}

}
