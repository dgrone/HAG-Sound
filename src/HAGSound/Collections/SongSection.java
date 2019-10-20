package HAGSound.Collections;

public class SongSection {
	private double Start = 0.0;
	private double Duration = 0.0;
	private double Confidence = 0.0;
	private double Loudness = 0.0;
	private double Tempo = 0.0;
	private double TempoConfidence = 0.0;
	private double Key = 0.0;
	private double KeyConfidence = 0.0;
	private double Mode = 0.0;
	private double ModeConfidence = 0.0;
	private double TimeSignature = 0.0;
	private double TimeSignatureConfidence = 0.0;
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
	public double getLoudness() {
		return Loudness;
	}
	public void setLoudness(double loudness) {
		Loudness = loudness;
	}
	public double getTempo() {
		return Tempo;
	}
	public void setTempo(double tempo) {
		Tempo = tempo;
	}
	public double getTempoConfidence() {
		return TempoConfidence;
	}
	public void setTempoConfidence(double tempoConfidence) {
		TempoConfidence = tempoConfidence;
	}
	public double getKey() {
		return Key;
	}
	public void setKey(double key) {
		Key = key;
	}
	public double getKeyConfidence() {
		return KeyConfidence;
	}
	public void setKeyConfidence(double keyConfidence) {
		KeyConfidence = keyConfidence;
	}
	public double getMode() {
		return Mode;
	}
	public void setMode(double mode) {
		Mode = mode;
	}
	public double getModeConfidence() {
		return ModeConfidence;
	}
	public void setModeConfidence(double modeConfidence) {
		ModeConfidence = modeConfidence;
	}
	public double getTimeSignature() {
		return TimeSignature;
	}
	public void setTimeSignature(double timeSignature) {
		TimeSignature = timeSignature;
	}
	public double getTimeSignatureConfidence() {
		return TimeSignatureConfidence;
	}
	public void setTimeSignatureConfidence(double timeSignatureConfidence) {
		TimeSignatureConfidence = timeSignatureConfidence;
	}
}
