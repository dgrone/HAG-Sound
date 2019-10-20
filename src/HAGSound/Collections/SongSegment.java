package HAGSound.Collections;

public class SongSegment {
	private double Start = 0.0;
	private double Duration = 0.0;
	private double Confidence = 0.0;
	private double LoudnessStart = 0.0;
	private double LoudnessMax = 0.0;
	private double LoudnessMaxTime = 0.0;
	private boolean Use = false;
	private double[] Pitches = {};
	private double[] Timbres = {};
	
	public double getStart() {
		return Start;
	}
	public void setStart(double start) {
		Start = start;
	}
	public double getDuration() {
		return Duration;
	}
	public void setDuration(double duration) {
		Duration = duration;
	}
	public double getConfidence() {
		return Confidence;
	}
	public void setConfidence(double confidence) {
		Confidence = confidence;
	}
	public double getLoadnessStart() {
		return LoudnessStart;
	}
	public void setLoudnessStart(double loadnessStart) {
		LoudnessStart = loadnessStart;
	}
	public double getLoadnessMax() {
		return LoudnessMax;
	}
	public void setLoudnessMax(double loadnessMax) {
		LoudnessMax = loadnessMax;
	}
	public double getLoadnessMaxTime() {
		return LoudnessMaxTime;
	}
	public void setLoudnessMaxTime(double loadnessMaxTime) {
		LoudnessMaxTime = loadnessMaxTime;
	}
	public boolean isUse() {
		return Use;
	}
	public void setUse(boolean use) {
		Use = use;
	}
	public double[] getPitches() {
		return Pitches;
	}
	public void setPitches(double[] pitches) {
		Pitches = pitches;
	}
	public double[] getTimbres() {
		return Timbres;
	}
	public void setTimbres(double[] timbres) {
		Timbres = timbres;
	}
	
	
}
	
