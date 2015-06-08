package mainGui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import common.ListenerSetException;


/**
 * SimpleFrameTest
 *
 */
public class MainFrameTest {
    public static void main(String[] args){
		UIManager.LookAndFeelInfo laf[] = UIManager
				.getInstalledLookAndFeels();
		for (int i = 0, n = laf.length; i < n; i++) {
			System.out.print("LAF Name: " + laf[i].getName() + "\t");
			System.out.println("  LAF Class name: "
					+ laf[i].getClassName());
		}

    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	    	 MainFrame frame = null;
				try {
					frame = new MainFrame();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				} catch (ListenerSetException e) {
					e.printStackTrace();
				}
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
    	});
   }
}
