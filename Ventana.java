
import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame{
    
    JPanel panel;
    JPanel pnl;
    
    public Ventana(){
        iniciar();
    }
    
    private void iniciar(){
        
        setSize(420, 410);
        setTitle("GridBagLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagLayout lay = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        panel = new JPanel(lay);
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        //Fila 1
        pnl = new JPanel();
        pnl.setBackground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(pnl, gbc);
        
        pnl = new JPanel();
        pnl.setBackground(Color.ORANGE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel.add(pnl, gbc);
        
        //Fila 2
        pnl = new JPanel();
        pnl.setBackground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(pnl, gbc);
        
        pnl = new JPanel();
        pnl.setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(pnl, gbc);
        
        pnl = new JPanel();
        pnl.setBackground(Color.CYAN);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        panel.add(pnl, gbc);
        
        //Fila 3
        pnl = new JPanel();
        pnl.setBackground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(pnl, gbc);
        
        pnl = new JPanel();
        pnl.setBackground(Color.MAGENTA);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(pnl, gbc);
        
        add(panel);
        
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        new Ventana().setVisible(true);
    }
}
