package fr.celso.insectwarfare.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Sound class is designed to handle playing audio clips in the game.
 */

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound() {

        // Initialize URLs for sound files
        soundURL[0] = getClass().getResource("/sound/Dungeon.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/unlock.wav");
    }

    // Method to set the audio file to play
    public void  setFile (int i ){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e) {

        }
    }

    // Method to play the audio clip once
    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    // Method to play the audio clip in a loop
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Method to stop the audio clip
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}

