package my.example.jface.standalone;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SimpleApplicationWindow{

	public static void main(String[] args) {
		openShell();
	}

	static void openDialog() {
		MessageDialog.openConfirm(null, "Confirm", "BellaCiao");
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

}
