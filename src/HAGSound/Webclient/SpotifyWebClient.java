package HAGSound.Webclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

import HAGSound.Collections.SongAnalyse;
import HAGSound.Collections.SongInfo;
import HAGSound.Disco.SoundEngine;
import HAGSound.Disco.SpotifyDecoder;
import HAGSound.Global.global;

public class SpotifyWebClient {
	ChromeDriver _Driver;

	public SpotifyWebClient() {
		System.setProperty("webdriver.chrome.driver", "C:\\git\\HAG-Sound\\libs\\chromedriver.exe");
		_Driver = new ChromeDriver();
	}

	public void StartSpotifyURI() {
		_Driver.get("http://localhost/player.html");
	}

	private void WriteAnalyseFile(String aJSONStr, String aSongID) {
		try {
			BufferedWriter bwr = new BufferedWriter(
					new FileWriter(new File("C:\\git\\HAG-Sound\\sounds\\" + aSongID + ".spotify")));
			bwr.write(aJSONStr);
			bwr.flush();
			bwr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getElementFromPage(String aElement) {
		String lStr = "";
		try {
			 lStr = _Driver.findElementById(aElement).getText();
		} catch (Exception e) {
		}
		
		return lStr;
	}

	public SongInfo ReadSongInfoFromWebclient() {
		SongInfo lSongInfo = new SongInfo();
		String lLastSongID = "";
		boolean lRefesh = false;

		String songID = getElementFromPage("songID");
		String songPositionStr = getElementFromPage("songPosition");
		String songDurationStr = getElementFromPage("songDuration");
		String lRefeshStr = getElementFromPage("Refresh");
		if (lRefeshStr.equals("YES")) {
			_Driver.executeScript("document.getElementById('Refresh').innerHTML = 'NO'");
			lRefesh = true;
		} else
			lRefesh = false;
		String timeRefeshStr = getElementFromPage("timeRefesh");
		String songToken = getElementFromPage("songToken");

		if ((!lLastSongID.equals(songID) || lRefesh) && !songID.isEmpty()) {
			lLastSongID = songID;
			try {
				// Zeit, die auf der externen Seite gebraucht wurde, bis das ganze aufgerufen
				// wurde.
				// CurrentPosition bleibt dann stehen
				lSongInfo.setExternTimeUntilRequest(Long.parseLong(timeRefeshStr));
				lSongInfo.setCurrentPosition(Long.parseLong(songPositionStr));
				lSongInfo.setDuration(Long.parseLong(songDurationStr));
				lSongInfo.setAccessToken(songToken);
				lSongInfo.setSongID(songID);
				return lSongInfo;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public SongInfo ReadUntilSongInfo() {
		SongInfo lSI = null;
		while (lSI == null) {
			lSI = ReadSongInfoFromWebclient();
			global.Sleep(100);
		}
		return lSI;
	}

	public void MakeDisco(SongInfo aSI) {
		if (aSI.getSongID().length() > 0) {
			try {
				aSI.setLastSystemTime(System.currentTimeMillis());

				GetAnalysisAndSave(aSI);
				SongAnalyse lAnalyse = SpotifyDecoder.DecodeSpotifySound(aSI.getSongID());

				// Zeit anspassen, damit das Syncron läuft - danach wird es sofort gestartet
				long estimatedTime = System.currentTimeMillis() - aSI.getLastSystemTime();
				aSI.setExternTimeUntilRequest(aSI.getExternTimeUntilRequest() + estimatedTime);

				SoundEngine lSE = new SoundEngine(aSI);
				lSE.setSoundAnalyse(lAnalyse);
				lSE.ShowSongAnalyseWindow();
				lSE.SeekAudioClipAndExecute();

			} catch (Exception e) {
				global.WriteOutput(e.getMessage());
			}
		}
	}
	
	private void GetAnalysisAndSave(SongInfo aSongInfo) {
		URL url;
		try {
			url = new URL("https://api.spotify.com/v1/audio-analysis/" + aSongInfo.getSongID());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("Authorization", "Bearer " + aSongInfo.getAccessToken());
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output + "\n");
			}
			in.close();

			WriteAnalyseFile(response.toString(), aSongInfo.getSongID());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
