import HAGSound.Collections.SongAnalyse;
import HAGSound.Disco.SoundEngine;
import HAGSound.Disco.SpotifyDecoder;
import HAGSound.Global.global;

public class SoundMain {

	public static void main(String[] args) {
		// Song file ID from Spotify
		if(args.length == 1) {
			String lID = args[0];
			
			SoundEngine lSE = new SoundEngine(lID);
			SongAnalyse lAnalyse = SpotifyDecoder.DecodeSpotifySound(lID);
			if(lAnalyse != null) {
				lSE.setSoundAnalyse(lAnalyse);
				if(lSE.PlaySound())
					lSE.SeekAudioClipAndExecute();
			}		
		} else {
			global.WriteOutput("Invalid argument\r\n" + 
					"Example: HAG-Sound.jar 3f1xMO1C4ED8p2OMxQc1oL");
		}
	}
}
