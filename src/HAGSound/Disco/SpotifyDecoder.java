package HAGSound.Disco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import HAGSound.Collections.SongAnalyse;
import HAGSound.Collections.SongBar;
import HAGSound.Collections.SongBeat;
import HAGSound.Collections.SongSection;
import HAGSound.Collections.SongSegment;
import HAGSound.Collections.SongTatum;
import HAGSound.Global.global;

public class SpotifyDecoder {
	
	public static SongAnalyse DecodeSpotifySound(String aID) {
		InputStream fis = null;
		File lFile = new File("sounds\\" + aID + ".spotify");
		if(!lFile.exists()) {
			global.WriteOutput("File ´" + lFile.getAbsolutePath() + "´ doesnt exists!");
			return null;
		}
		try {
			fis = new FileInputStream(lFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		}
		
		//create JsonReader object
		JsonReader jsonReader = Json.createReader(fis);		
		JsonObject jsonObject = jsonReader.readObject();
		SongAnalyse lSongAnalyse = new SongAnalyse();
		
		// SongBars
		JsonArray lJsonArray = jsonObject.getJsonArray("bars");
		for (int i = 0; i < lJsonArray.size(); i++) {
		    JsonObject lJsonObj = lJsonArray.getJsonObject(i);
		    SongBar lSB = new SongBar();
		    lSB.setStart((Double.parseDouble(lJsonObj.get("start").toString())) * 1000);
		    lSB.setDuration((Double.parseDouble(lJsonObj.get("duration").toString())) * 1000);
		    lSB.setConfidence((Double.parseDouble(lJsonObj.get("confidence").toString())));
		    lSongAnalyse.getSongBars().add(lSB);
		}

		// SongBeats
		lJsonArray = jsonObject.getJsonArray("beats");
		for (int i = 0; i < lJsonArray.size(); i++) {
		    JsonObject lJsonObj = lJsonArray.getJsonObject(i);
		    SongBeat lSB = new SongBeat();
		    lSB.setStart((Double.parseDouble(lJsonObj.get("start").toString())) * 1000);
		    lSB.setDuration((Double.parseDouble(lJsonObj.get("duration").toString())) * 1000);
		    lSB.setConfidence((Double.parseDouble(lJsonObj.get("confidence").toString())));
		    lSongAnalyse.getSongBeats().add(lSB);
		}

		// SongBeats
		lJsonArray = jsonObject.getJsonArray("tatums");
		for (int i = 0; i < lJsonArray.size(); i++) {
		    JsonObject lJsonObj = lJsonArray.getJsonObject(i);
		    SongTatum lST = new SongTatum();
		    lST.setStart((Double.parseDouble(lJsonObj.get("start").toString())) * 1000);
		    lST.setDuration((Double.parseDouble(lJsonObj.get("duration").toString())) * 1000);
		    lST.setConfidence((Double.parseDouble(lJsonObj.get("confidence").toString())));
		    lSongAnalyse.getSongTatums().add(lST);
		}

		// SongSections
		lJsonArray = jsonObject.getJsonArray("sections");
		for (int i = 0; i < lJsonArray.size(); i++) {
		    JsonObject lJsonObj = lJsonArray.getJsonObject(i);
		    SongSection lSs = new SongSection();
		    lSs.setStart((Double.parseDouble(lJsonObj.get("start").toString())) * 1000);
		    lSs.setDuration((Double.parseDouble(lJsonObj.get("duration").toString())) * 1000);
		    lSs.setConfidence((Double.parseDouble(lJsonObj.get("confidence").toString())));
		    lSs.setLoudness((Double.parseDouble(lJsonObj.get("loudness").toString())));
		    lSs.setTempo((Double.parseDouble(lJsonObj.get("tempo").toString())));
		    lSs.setTempoConfidence((Double.parseDouble(lJsonObj.get("tempo_confidence").toString())));
		    lSs.setKey((Double.parseDouble(lJsonObj.get("key").toString())));
		    lSs.setKeyConfidence((Double.parseDouble(lJsonObj.get("key_confidence").toString())));
		    lSs.setMode((Double.parseDouble(lJsonObj.get("mode").toString())));
		    lSs.setModeConfidence((Double.parseDouble(lJsonObj.get("mode_confidence").toString())));
		    lSs.setTimeSignature((Double.parseDouble(lJsonObj.get("time_signature").toString())));
		    lSs.setTimeSignatureConfidence((Double.parseDouble(lJsonObj.get("time_signature_confidence").toString())));

		    lSongAnalyse.getSongSections().add(lSs);
		}
		
		// SongSegments
		lJsonArray = jsonObject.getJsonArray("segments");
		for (int i = 0; i < lJsonArray.size(); i++) {
		    JsonObject lJsonObj = lJsonArray.getJsonObject(i);
		    SongSegment lSS = new SongSegment();
		    lSS.setStart((Double.parseDouble(lJsonObj.get("start").toString())) * 1000);
		    lSS.setDuration((Double.parseDouble(lJsonObj.get("duration").toString())) * 1000);
		    lSS.setConfidence((Double.parseDouble(lJsonObj.get("confidence").toString())));
		    lSS.setLoudnessStart((Double.parseDouble(lJsonObj.get("loudness_start").toString())));
		    lSS.setLoudnessMax((Double.parseDouble(lJsonObj.get("loudness_max").toString())));
		    lSS.setLoudnessMaxTime((Double.parseDouble(lJsonObj.get("loudness_max_time").toString())) * 1000);

		    JsonArray lJA = lJsonObj.getJsonArray("pitches");
			double[] lPitches = new double[12];
			for(int x = 0; x < lJA.size(); x++) {
				lPitches[x] = (Double.parseDouble(lJA.get(x).toString()));
			}
			lSS.setPitches(lPitches);
			
			
		    lJA = lJsonObj.getJsonArray("timbre");
			double[] lTimbre = new double[12];
			for(int x = 0; x < lJA.size(); x++) {
				lTimbre[x] = (Double.parseDouble(lJA.get(x).toString()));
			}
			lSS.setTimbres(lTimbre);
			
		    lSongAnalyse.getSongSegments().add(lSS);
		}
		
		jsonReader.close();
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lSongAnalyse;
	}
}
