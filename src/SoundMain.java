import HAGSound.Collections.SongAnalyse;
import HAGSound.Disco.SoundEngine;
import HAGSound.Disco.SpotifyDecoder;

public class SoundMain {

	public static void main(String[] args) {
		// Song file ID from Spotify
		String lID = "3f1xMO1C4ED8p2OMxQc1oL";
		
		SoundEngine lSE = new SoundEngine(lID);
		SongAnalyse lAnalyse = SpotifyDecoder.DecodeSpotifySound(lID);
		if(lAnalyse != null) {
			lSE.setSoundAnalyse(lAnalyse);
			if(lSE.PlaySound())
				lSE.SeekAudioClipAndExecute();
		}
	}
}
