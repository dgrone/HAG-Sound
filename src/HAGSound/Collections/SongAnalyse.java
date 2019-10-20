package HAGSound.Collections;

import java.util.ArrayList;

public class SongAnalyse {
	private String Name = "";
	private ArrayList<SongBar> SongBars = new ArrayList<SongBar>();
	private ArrayList<SongBeat> SongBeats = new ArrayList<SongBeat>();
	private SongInfo SongInfo = new SongInfo();
	private ArrayList<SongSection> SongSections = new ArrayList<SongSection>();
	private ArrayList<SongSegment> SongSegments = new ArrayList<SongSegment>();
	private ArrayList<SongTatum> SongTatums = new ArrayList<SongTatum>();
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public ArrayList<SongBar> getSongBars() {
		return SongBars;
	}
	public void setSongBars(ArrayList<SongBar> songBars) {
		SongBars = songBars;
	}
	public ArrayList<SongBeat> getSongBeats() {
		return SongBeats;
	}
	public void setSongBeats(ArrayList<SongBeat> songBeats) {
		SongBeats = songBeats;
	}
	public SongInfo getSongInfo() {
		return SongInfo;
	}
	public void setSongInfo(SongInfo songInfo) {
		SongInfo = songInfo;
	}
	public ArrayList<SongSection> getSongSections() {
		return SongSections;
	}
	public void setSongSections(ArrayList<SongSection> songSections) {
		SongSections = songSections;
	}
	public ArrayList<SongSegment> getSongSegments() {
		return SongSegments;
	}
	public void setSongSegments(ArrayList<SongSegment> songSegments) {
		SongSegments = songSegments;
	}
	public ArrayList<SongTatum> getSongTatums() {
		return SongTatums;
	}
	public void setSongTatums(ArrayList<SongTatum> songTatums) {
		SongTatums = songTatums;
	}
	
	
}
