package configuration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	
	private Clip clip;
    private FloatControl volumeControl;
	
	public MusicPlayer() {

		
		try {
        	

			InputStream audioFile = getClass().getResourceAsStream("music-theme.wav");
			
			if (audioFile == null) {
				System.out.println("aaa");
                throw new IOException("Audio file not found");
            }
			try (BufferedInputStream bufferedIn = new BufferedInputStream(audioFile);
	                 AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn)) {
	                clip = AudioSystem.getClip();
	                clip.open(audioStream);
	                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	            
				
			}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				System.out.println(e.getMessage());
			}
            
		}catch(IOException e) {
			System.out.println(e.getMessage());
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
	
	public void setVolume(float value) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            volumeControl.setValue(Math.min(max, Math.max(min, value)));
        }
    }
}
