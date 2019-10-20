package HAGSound.GUI;

import java.awt.Color;
import javax.swing.JLabel;
import HAGSound.Global.global;

public class SongAnalyseThread extends Thread {
	JLabel _Label;
	int _Duration = 100;

	public SongAnalyseThread(JLabel aLabel) {
		this._Label = aLabel;
	}
	
	public SongAnalyseThread(JLabel aLabel, int aDuration) {
		this._Label = aLabel;
		this._Duration = aDuration;
	}
	
	public void run() {
		if(SongAnalyseWindow.IsLoaded) {
			_Label.setForeground(Color.green);
			global.Sleep(_Duration);
			_Label.setForeground(Color.gray);			
		}
		
	}
}
