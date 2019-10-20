package HAGSound.Disco;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

import HAGSound.Collections.*;
import HAGSound.GUI.SongAnalyseThread;
import HAGSound.GUI.SongAnalyseWindow;
import HAGSound.Global.global;

public class SoundEngine {
	Clip _AudioClip;
	SongAnalyse  _SoundAnalyse; 
	String _ID = "";
	int _Segments = 0;

	public SongAnalyse getSoundAnalyse() {
		return _SoundAnalyse;
	}

	public void setSoundAnalyse(SongAnalyse aSoundAnalyse) {
		this._SoundAnalyse = aSoundAnalyse;
	}

	public SoundEngine(String aID) {
		_ID = aID;
		SongAnalyseWindow.main(null);
	}
	
	public boolean PlaySound() {
		try {
			_AudioClip = AudioSystem.getClip();

			_AudioClip.addLineListener(event -> {
				if (LineEvent.Type.STOP.equals(event.getType())) {
					_AudioClip.close();
				}
			});
			
			File lFile = new File("sounds\\" + _ID + ".wav");
			if(!lFile.exists()) {
				global.WriteOutput("File ´" + lFile.getAbsolutePath() + "´ doesnt exists!");
				return false;
			}
			System.out.println("Play Sound: " + lFile.getAbsolutePath());
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(lFile);
			_AudioClip.open(inputStream);
			_AudioClip.start();

			while (!_AudioClip.isRunning())
				Thread.sleep(1);
			System.out.println("playing...");

		} catch (Exception ex) {

			System.out.println("Fehler: " + ex.getMessage());
		}
		return true;
	}
	
	public void SeekAudioClipAndExecute() {
		// guckt im Millisekundentakt nach, was ansteht.
		System.out.println("Segments: " + _SoundAnalyse.getSongSegments().size());
		while (_AudioClip.isRunning()) {
			double lClipInMS = _AudioClip.getMicrosecondPosition() / 1000;

			SongTatum lST = getSongTatumInRange(lClipInMS);
			int lWhileSleep;
			double lRange;
			
			if(lST != null) {
				lWhileSleep = (int) lST.getDuration() - 2;
				lRange = lST.getDuration() / 2;
			} else {
				lWhileSleep = 100;
				lRange = 50.0;
			}
			
			for(int i = 0; i < _SoundAnalyse.getSongBars().size(); i++) {
				SongBar lSongBar = _SoundAnalyse.getSongBars().get(i);
				if(!lSongBar.isUse() && global.Between(lClipInMS, lSongBar.getStart() - lRange, lSongBar.getStart() + lRange)) {
					new SongAnalyseThread(SongAnalyseWindow.Bar).start();
				}
			}
			for(int i = 0; i < _SoundAnalyse.getSongTatums().size(); i++) {
				SongTatum lSongTatum = _SoundAnalyse.getSongTatums().get(i);
				if(global.Between(lClipInMS, lSongTatum.getStart() - lRange, lSongTatum.getStart() + lRange)) {
					new SongAnalyseThread(SongAnalyseWindow.Tatum).start();
				}
			}
			
			ArrayList<SongSegment> lSongSegments = getSongSegmentsInRange(lClipInMS, lRange);
			for(int i = 0; i < lSongSegments.size(); i++) {
				SongSegment lSongSegment = lSongSegments.get(i);
				_Segments++;
				new SongAnalyseThread(SongAnalyseWindow.Segment).start();

				if(i==0) 
				{
					SetPitches(lSongSegment.getPitches());
					SetTimbres(lSongSegment.getTimbres());
				}
			}
			
			ArrayList<SongBeat> lSongBeats = getSongBeatsInRange(lClipInMS, lRange);
			for(int i = 0; i < lSongBeats.size(); i++) {
				new SongAnalyseThread(SongAnalyseWindow.Takt).start();
				
				for(int x = 0; x < lSongSegments.size(); x++) {
					SongSegment lSongSegment = lSongSegments.get(x);
					
					// test
					double[] lPitches = lSongSegment.getPitches();
					double lAddPitches = lPitches[0] + lPitches[1] + lPitches[2] + lPitches[3] +lPitches[4] + lPitches[5] +lPitches[6] +lPitches[7] + lPitches[8] + lPitches[9] + lPitches[10] + lPitches[11];

					if(lAddPitches < 4.0) {
						new SongAnalyseThread(SongAnalyseWindow.Bass).start();
						break;
					}
				}
			}
			lSongSegments.removeAll(lSongSegments);
			
			ArrayList<SongSection> lSongSections = getSongSectionsInRange(lClipInMS, lRange);
			for(int i = 0; i < lSongSections.size(); i++) {
				new SongAnalyseThread(SongAnalyseWindow.Section).start();
				global.WriteOutput("Tempo: " + lSongSections.get(i).getTempo()  + " - NOW: " + lClipInMS + " | " + lSongSections.get(i).getStart());
				
			}
			lSongSections.removeAll(lSongSections);
			lSongBeats.removeAll(lSongBeats);
			
			global.Sleep(lWhileSleep);
		}
		System.out.println("Calc Segments: " + _Segments);
	}

	private ArrayList<SongSegment> getSongSegmentsInRange(double aTimeInMS, double aRange) {
		ArrayList<SongSegment> lSongSegments = new ArrayList<SongSegment>();
		
		boolean lFounded = false;
		for(int i = 0; i < _SoundAnalyse.getSongSegments().size(); i++) {
			SongSegment lSongSegment = _SoundAnalyse.getSongSegments().get(i);
			
			if(global.Between(aTimeInMS, lSongSegment.getStart() - aRange, lSongSegment.getStart() + aRange)) {
				//System.out.println("Player: " + aTimeInMS + " | Segment: " + lSongSegment.getStart() + " | Range: " + aRange);
				lSongSegments.add(lSongSegment);
				lFounded = true;
			} else {
				if(lFounded)
					break;
			}
		}
		return lSongSegments;
	}

	private ArrayList<SongBeat> getSongBeatsInRange(double aTimeInMS, double aRange) {
		ArrayList<SongBeat> lSongBeats = new ArrayList<SongBeat>();
		
		for(int i = 0; i < _SoundAnalyse.getSongBeats().size(); i++) {
			SongBeat lSongBeat = _SoundAnalyse.getSongBeats().get(i);

			boolean lFounded = false;
			if(global.Between(aTimeInMS, lSongBeat.getStart() - aRange, lSongBeat.getStart() + aRange)) {
				lSongBeats.add(lSongBeat);
				lFounded = true;
			} else {
				if(lFounded)
					break;
			}
		}
		return lSongBeats;
	}

	private SongTatum getSongTatumInRange(double aTimeInMS) {
		for(int i = 0; i < _SoundAnalyse.getSongTatums().size(); i++) {
			SongTatum lSongTatum = _SoundAnalyse.getSongTatums().get(i);
			
			if(global.Between(aTimeInMS, lSongTatum.getStart() - 20, lSongTatum.getStart() + 20)) {
				return lSongTatum;
			} 
		}
		return null;
	}

	private ArrayList<SongSection> getSongSectionsInRange(double aTimeInMS, double aRange) {
		ArrayList<SongSection> lSections = new ArrayList<SongSection>();
		for(int i = 0; i < _SoundAnalyse.getSongSections().size(); i++) {
			SongSection lSongSection = _SoundAnalyse.getSongSections().get(i);
			
			if(global.Between(aTimeInMS, lSongSection.getStart() - aRange, lSongSection.getStart() + aRange)) {
				lSections.add(lSongSection);
			} 
		}
		return lSections;
	}

	private void SetPitches(double[] pitches) {
		if(SongAnalyseWindow.IsLoaded) {
			for(int m = 0; m < pitches.length; m++) {
				double lPitch = pitches[m];
				if(lPitch > 0.8)
					SongAnalyseWindow.Pitches.get(m).setForeground(Color.green);
				else if(lPitch > 0.5)
					SongAnalyseWindow.Pitches.get(m).setForeground(Color.yellow);
				else if(lPitch > 0.1)
					SongAnalyseWindow.Pitches.get(m).setForeground(Color.orange);
				else if(lPitch <= 0.1)
					SongAnalyseWindow.Pitches.get(m).setForeground(Color.red);
			}
		}
	}

	private void SetTimbres(double[] timbres) {
		if(SongAnalyseWindow.IsLoaded) {
			for(int m = 0; m < timbres.length; m++) {
				double lPitch = timbres[m];
				if(lPitch > 0.8)
					SongAnalyseWindow.Timbres.get(m).setForeground(Color.green);
				else if(lPitch > 0.5)
					SongAnalyseWindow.Timbres.get(m).setForeground(Color.yellow);
				else if(lPitch > 0.1)
					SongAnalyseWindow.Timbres.get(m).setForeground(Color.orange);
				else if(lPitch <= 0.1)
					SongAnalyseWindow.Timbres.get(m).setForeground(Color.red);
			}
		}
	}

}
