package utils;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * utility class not neccerily needed for running the game
 *
 * <p> handles loading, playing, and stopping, sound files. </p>
 */

public class Sound {

    private Clip clip;

    /**
     * method loads the sound file from specifed path
     * @param path path to the audiofile
     */
    public Sound(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays the loaded audio file.
     */
    public void play() {
        //return nothing if there is no file
        if (clip == null) return;
        // checks if the clip is already running if so stops it
        if (clip.isRunning()) clip.stop();
        //puts the clip to the start and plays it
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * method checks if specified clip is playing if it is stops it
     * otherwise do nothing.
     */
    public void stop() {
        if (clip.isRunning()) clip.stop();
    }
}
