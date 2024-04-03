package src.main.java;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A class for playing sound effects and music clips.
 * 
 * @author viktoriya.li
 */
public class soundPlayer {

	private static Clip musicClip; // Clip for playing music
	private static Clip sfxClip; // Clip for playing sound effects

	/**
	 * Plays a sound effect once.
	 * 
	 * @param filePath The file path of the sound effect
	 */
	public void playSoundOnce(String filePath) {
		try {
			// Open an audio input stream to the .wav file
			URL soundURL = getClass().getResource(filePath);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);

			// Get a sound clip resource
			sfxClip = AudioSystem.getClip();

			// Open audio clip and load samples from the audio input stream
			sfxClip.open(audioIn);

			// Play the audio clip
			sfxClip.start();
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println("Error in soundPlayer.playSoundOnce");
		}
	}

	/**
	 * Plays a music clip in a loop.
	 * 
	 * @param filePath The file path of the music clip
	 */
	public void playSoundLoop(String filePath) {
		try {
			// If the clip is null or not currently playing
			if (musicClip == null || !musicClip.isRunning()) {
				// Open an audio input stream to the .wav file
				URL soundURL = getClass().getResource(filePath);
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);

				// Get a sound clip resource
				musicClip = AudioSystem.getClip();

				// Open audio clip and load samples from the audio input stream
				musicClip.open(audioIn);

				// Loop the audio clip indefinitely
				musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println("Error in soundPlayer.playSoundLoop");
		}
	}

	/**
	 * Stops the currently playing music clip.
	 */
	public void stopMusic() {
		if (musicClip != null && musicClip.isRunning()) {
			musicClip.stop();
		}
	}
}
