
import javax.swing.*;
import java.awt.*;

public class grid extends JPanel{
    
    JPanel panel;
    JPanel pnl;
    
    public grid(){
        iniciar();
    }
    
    private void iniciar(){
        
       
        GridBagLayout lay = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        this.setLayout(lay);
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
       
	JPanel p = new JPanel();
	p.setBackground(Color.RED);
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	this.add(p,gbc);

	p = new JPanel();
	p.setBackground(Color.BLUE);
	gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.gridwidth = 1;
	gbc.gridheight = 2;
	this.add(p,gbc);

	p = new JPanel();
	p.setBackground(Color.GREEN);
	gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	this.add(p,gbc);

	p = new JPanel();
	p.setBackground(Color.YELLOW);
	gbc.gridx = 0;
	gbc.gridy = 2;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	this.add(p,gbc);

	p = new JPanel();
	p.setBackground(Color.black);
	gbc.gridx = 1;
	gbc.gridy = 2;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	this.add(p,gbc);

	p = new JPanel();
	p.setBackground(Color.CYAN);
	gbc.gridx = 2;
	gbc.gridy = 0;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	this.add(p,gbc);



    }
    
    public static void main(String[] args) {
      	 JFrame f = new JFrame();
	    f.setSize(420, 410);
        	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	  	grid g = new grid();
		f.getContentPane().add(g);
  	f.setLocationRelativeTo(null);
    		f.setVisible(true);
    
    }
}
