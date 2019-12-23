package HAGSound.Collections;

public class SongInfo {
	private String SongID = "";
	private long LastSystemTime = 0;
	private long CurrentPosition = 0;
	private long Duration = 0;
	private String AccessToken = "";
	private boolean IsPlaying = false;
	private long ExternTimeUntilRequest = 0;
	
	public long getExternTimeUntilRequest() {
		return ExternTimeUntilRequest;
	}
	public void setExternTimeUntilRequest(long externTimeUntilRequest) {
		System.out.println("SetExternTimeUntilRequest: " + externTimeUntilRequest);
		ExternTimeUntilRequest = externTimeUntilRequest;
	}
	
	public String getSongID() {
		return SongID;
	}
	public void setSongID(String songID) {
		SongID = songID;
	}
	public long getLastSystemTime() {
		return LastSystemTime;
	}
	public void setLastSystemTime(long lastSystemTime) {
		LastSystemTime = lastSystemTime;
	}
	public long getCurrentPosition() {
		return CurrentPosition;
	}
	public void setCurrentPosition(long currentPosition) {
		CurrentPosition = currentPosition;
	}
	public long getDuration() {
		return Duration;
	}
	public void setDuration(long duration) {
		Duration = duration;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public boolean isIsPlaying() {
		return IsPlaying;
	}
	public void setIsPlaying(boolean isPlaying) {
		IsPlaying = isPlaying;
	}
	
	
}
