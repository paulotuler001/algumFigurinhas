package configuration;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	private Clip clip;
	
	public MusicPlayer() {
		try {
			File audioFile = new File("music-theme.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.getMessage();
		}
	}
	
	public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }
	
	public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
