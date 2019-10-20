package HAGSound.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class SongAnalyseWindow extends javax.swing.JFrame {
	private static final long serialVersionUID = 1234678L;
	
	public static boolean IsLoaded = false;
	public static ArrayList<JLabel> Pitches = new ArrayList<JLabel>(); 
	public static ArrayList<JLabel> Timbres = new ArrayList<JLabel>(); 
	public static ArrayList<JLabel> Lamps  = new ArrayList<JLabel>(); 
	public static JLabel Bass  = new JLabel(); 
	public static JLabel Takt  = new JLabel(); 
	public static JLabel Tatum = new JLabel(); 
	public static JLabel Bar = new JLabel(); 
	public static JLabel Segment = new JLabel(); 
	public static JLabel Section = new JLabel(); 
	
	public static void main(String[] args)
    {    
		IsLoaded = true;
        JFrame window = new JFrame("DG's Soundanalyse");  
        window.setSize(400,360);
        window.setLayout(null);
        
        window.add(CreateLabel("Lampen:", 18, 10, 10)); 
        
        for(int i = 0; i < 6; i++) {
        	JLabel lLabel = CreateLabel("x", 28, 10 + 30 * i, 30);
            window.add(lLabel); 
            Lamps.add(lLabel);
        }

        window.add(CreateLabel("Pitches:", 18, 10, 80)); 
        for(int i = 0; i < 12; i++) {
        	JLabel lLabel = CreateLabel("x", 28, 10 + 30 * i, 100);
            window.add(lLabel);  
            Pitches.add(lLabel);
        }
        
        window.add(CreateLabel("Timbres:", 18, 10, 140)); 
        for(int i = 0; i < 12; i++) {
        	JLabel lLabel = CreateLabel("x", 28, 10 + 30 * i, 160);
            window.add(lLabel);  
            Timbres.add(lLabel);
        }
        
        window.add(CreateLabel("Bass", 16, 10, 210)); 
    	JLabel lLabel = CreateLabel("x", 28, 21, 225);
        window.add(lLabel);  
        Bass = lLabel;

        window.add(CreateLabel("Takt", 16, 62, 210)); 
    	JLabel lLabelTakt = CreateLabel("x", 28, 71, 225);
        window.add(lLabelTakt);  
        Takt = lLabelTakt;

        window.add(CreateLabel("Tatum", 16, 110, 210)); 
    	JLabel lLabelTatum = CreateLabel("x", 28, 126, 225);
        window.add(lLabelTatum);  
        Tatum = lLabelTatum;

        window.add(CreateLabel("Bar", 16, 176, 210)); 
    	JLabel lLabelBar = CreateLabel("x", 28, 180, 225);
        window.add(lLabelBar);  
        Bar = lLabelBar;

        window.add(CreateLabel("Segment", 16, 216, 210)); 
    	JLabel lLabelSegment = CreateLabel("x", 28, 245, 225);
        window.add(lLabelSegment);  
        Segment = lLabelSegment;

        window.add(CreateLabel("Section", 16, 296, 210)); 
    	JLabel lLabelSection = CreateLabel("x", 28, 320, 225);
        window.add(lLabelSection);  
        Section = lLabelSection;

        window.setVisible(true);
    }
	
	 
 
	private static JLabel CreateLabel(String aText, int aSize, int x, int y) {
    	JLabel lLabel = new JLabel(aText);
    	lLabel.setFont(new Font("Arial", Font.BOLD, aSize));
    	lLabel.setForeground(Color.gray);
        Dimension size = lLabel.getPreferredSize();
        lLabel.setBounds(x, y, size.width + 6, size.height);
        return lLabel;		
	}
    
}