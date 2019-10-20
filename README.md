# HAG-Sound
Reads the JSON content from the Spotify analyze API (Echonest) and processes the content in a GUI

// GERMAN:

Damit dieses Tool funktioniert muss man über ein Spotify Account beherbergen. Wenn diese Vorraussetzung erfüllt ist, muss die die Audio Analyse von Spotify heruntergeladen werden:
https://developer.spotify.com/console/get-audio-analysis-track/

Den kompletten Inhalt in den Ordner "sounds" speichern. Name der Datei: "%ID%.spotify".
Die ID ist bei Spotifysongs die URI. Diese kann man herausfinden, wenn man ein Spotifysong teilen möchte und die URI kopiert.

Beispielinhalt:
{
  "meta": {
    "analyzer_version": "4.0.0",
    "platform": "Linux",
    "de ...  
    ...
        -117.176,
        18.823
    }
  ]
}

Die Musikdatei muss heruntergeladen werden und in ein *.wav-Format konvertiert werden. Die Datei muss im Ordner "sounds" mit dem Namen "%ID%.wav". 

Der Spotify-Player kann auch verwendet werden, wenn man diesen mit JAVA ansteuert. Hierzu wurde aber noch nix gemacht.

Das ist ein OpenSource Projekt. Jeder soll daran mitarbeiten. Da ich keine Ahnung von Musik habe, wäre es ganz gut, wenn Bässe oder "extreme" Töne durch die Segmenten herausgefiltert werden können. Jedes Segment hat sogenannte Pitches und Timbres, die einmal die Tonhöhe und die Klangqualität beschreiben.

Hier ist die Dokumentation der API:
http://docs.echonest.com.s3-website-us-east-1.amazonaws.com/_static/AnalyzeDocumentation.pdf



// ENGLISH:

For this tool to work, you must have a Spotify account. If this requirement is met, you need to download the audio analysis from Spotify:
https://developer.spotify.com/console/get-audio-analysis-track/

Save the complete content in the folder "sounds". Name of the file: "%ID%.spotify".
The ID for Spotifysongs is the URI. You can find this out if you want to share a spotifysong and copy the URI.

Example content:
{
  "meta": {
    "analyzer_version": "4.0.0",
    "platform": "Linux",
    "de...  
    ...
        -117.176,
        18.823
    }
  ]
}

The music file must be downloaded and converted to a *.wav format. The file must be in the folder "sounds" with the name "%ID%.wav". 

The Spotify player can also be used if you control it with JAVA. But nothing was done for this yet.

This is an OpenSource project. Everybody should work on it. Since I have no idea of music, it would be quite good, if basses or "extreme" sounds can be filtered out by the segments. Each segment has so-called pitches and timbres, which describe the pitch and the sound quality.

Here is the documentation of the API:
http://docs.echonest.com.s3-website-us-east-1.amazonaws.com/_static/AnalyzeDocumentation.pdf 

Translated with www.DeepL.com/Translator
