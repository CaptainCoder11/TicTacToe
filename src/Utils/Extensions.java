package Utils;

import com.sun.tools.javac.Main;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

public class Extensions {
    public static void playSound(final String surl) {
        File soundFile = new File(surl);
        try { // Open an audio input stream.
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            // Open audio clip and load samples from the audio input stream.
                   /* clip.setFramePosition(0);
                    clip.open(audioIn);*/
            Mixer mixer = AudioSystem.getMixer(null);
            AudioFormat format = new AudioFormat(44100, 8, 1, true, false);
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            try {
                // Create a sound of 1 second
                Clip clip = (Clip) mixer.getLine(info);

                //Workaround part 1
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.START)
                            synchronized (clip) {
                                clip.notify();
                            }
                    }
                });


                clip.open(audioIn);
                clip.setFramePosition(0);

                //Workaround part 2
                synchronized (clip) {
                    clip.start();
                    clip.wait();
                    System.out.println("Started");
                }
                clip.drain();
                System.out.println("Drained");
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static Font getfont(String url){
        Font fontAwesome = null;
        try {
            InputStream myStream = new BufferedInputStream(
                    new FileInputStream(url));
            fontAwesome = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        Font newfont = fontAwesome.deriveFont(90f);
        return newfont;
    }
}
