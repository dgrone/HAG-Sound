package HAGSound.Collections;

public class SongBeat {
	private double Start = 0.0;
	private double Duration = 0.0;
	private double Confidence = 0.0;
	private boolean Use = false;
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
	public boolean isUse() {
		return Use;
	}
	public void setUse(boolean use) {
		Use = use;
	}
}
