package drawingPanel;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame implements ChangeListener {

	public JTabbedPane tbPanel = new JTabbedPane();
	public ViewingPanel vwp=new ViewingPanel();
	private int  width,height;

	public GUI(String path)  {
		
		// Constructing the main frame ===================== begin

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.height = (int) (.9 * screenSize.height);
		this.width = (int) (.8 * screenSize.width);
		setTitle("FEM Drawing Panel : "+path);
		setPreferredSize(new Dimension(this.width, this.height));
		setLocation((int) (.2 * screenSize.width), 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Confinguring the main frame ===================== end
	

		
		
		  tbPanel = new JTabbedPane();
			tbPanel.setFont(new Font("Arial", 1, 12));
			tbPanel.addTab("Viewer", vwp);
		
			getContentPane().add(tbPanel,"Center");
			this.tbPanel.addChangeListener(this);

			
		pack();

	}


	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}



}