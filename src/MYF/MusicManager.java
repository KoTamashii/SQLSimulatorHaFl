package MYF;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class MusicManager {

    //Future Plans: Add index stuff to the SoundFile class

    //Info: Instantiate a new MusicManager for each type of sound
    //for example: button sfx, music, backgroundmusic...

        //Attribute

        //Referenzen
    private ArrayList<SoundFile> soundFiles;
    private SoundFile currentlyPlayingSF;

    public MusicManager(){
        //Initializes soundFiles ArrayList
        soundFiles = new ArrayList<>();
    }
    /**
     *
     * @param name name of the file
     * @param filePath path to the audio file
     */
    public void addNewSoundFile(String name, String filePath){
        soundFiles.add(new SoundFile(name, filePath));
    }

    public void addNewSoundFile(String name, String filePath, int timesToRepeat){
        soundFiles.add(new SoundFile(name, filePath, timesToRepeat));
    }
    public void addNewSoundFile(String name, String filePath, boolean repeatEndless){
        soundFiles.add(new SoundFile(name, filePath, repeatEndless));
    }

    public SoundFile searchForSoundFileWithName(String name){
        Iterator<SoundFile> iterator = soundFiles.iterator();
        while(iterator.hasNext()){
            SoundFile tempSF = iterator.next();
            if(tempSF.getName().equals(name)){
                return tempSF;
            }
        }
        return null;
    }

    public void playNextSoundFile(){
        Iterator iterator = soundFiles.iterator();
        boolean sfPlayed = false;
        while (iterator.hasNext() && !sfPlayed){
            SoundFile soundFile = (SoundFile)iterator.next();
            if(soundFile != currentlyPlayingSF) {
                soundFile.startSoundFile();
                currentlyPlayingSF.stopSoundFile();
                sfPlayed = true;
            }
        }
    }


    //GETTER /// SETTER
    public SoundFile getCurrentlyPlayingSF() {
        return currentlyPlayingSF;
    }

    public class SoundFile{

            //Attribute
        private String name;
        private String filePath;

        private AudioInputStream stream;
        private AudioFormat format;
        private DataLine.Info info;
        private Clip clip;

        //For looping the song
        private boolean repeatAudio = false;
        private int timesToRepeat;
        private boolean repeatEndless;

        /**
         *
         * @param name name of the file
         * @param filePath path to the audio file
         */
        public SoundFile(String name, String filePath ) {
            this.name = name;
            this.filePath = filePath;

            try {
                stream = AudioSystem.getAudioInputStream(new File(filePath));
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
            } catch (LineUnavailableException l) {
                System.err.println("Die MyFrameWork Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                l.printStackTrace();
            } catch (UnsupportedAudioFileException u) {
                System.err.println("Die MyFrameWork Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                u.printStackTrace();
            } catch (IOException i) {
                System.err.println("Die MyFrameWork Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                i.printStackTrace();
            }
        }

        /**
         *
         * @param name name of the file
         * @param filePath path to the audio file
         * @param timesToRepeat how often should the song be looped
         */
        public SoundFile(String name, String filePath, int timesToRepeat) {
            this.name = name;
            this.filePath = filePath;

            //Intern for the startSoundFile method
            repeatAudio = true;
            this.timesToRepeat = timesToRepeat;

            try {
                stream = AudioSystem.getAudioInputStream(new File(filePath));
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
            } catch (LineUnavailableException l) {
                System.err.println("Die MyFrameWork Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                l.printStackTrace();
            } catch (UnsupportedAudioFileException u) {
                System.err.println("Die MyFrameWork Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                u.printStackTrace();
            } catch (IOException i) {
                System.err.println("Die MyFrameWork Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                i.printStackTrace();
            }
        }

        /**
         * @param name name of the file
         * @param filePath path to the audio file
         * @param repeatEndless the audio will be looped
         */
        public SoundFile(String name, String filePath, boolean repeatEndless) {
            this.name = name;
            this.filePath = filePath;

            //Intern for the startSoundFile method
            repeatAudio = true;
            this.repeatEndless = repeatEndless;

            try {
                stream = AudioSystem.getAudioInputStream(new File(filePath));
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
            } catch (LineUnavailableException l) {
                System.err.println("Die Audio Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                l.printStackTrace();
            } catch (UnsupportedAudioFileException u) {
                System.err.println("Die Audio Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                u.printStackTrace();
            } catch (IOException i) {
                System.err.println("Die Audio Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
                i.printStackTrace();
            }
        }

        public void startSoundFile() {
            currentlyPlayingSF = this;
            if(!repeatAudio) {
                clip.stop();
                clip.setMicrosecondPosition(0);
                clip.start();
            } else{
                if(!repeatEndless) {
                    clip.loop(timesToRepeat);
                } else {
                    clip.loop(Integer.MAX_VALUE);
                }
            }
        }

        public void stopSoundFile(){
            clip.stop();
        }

        //Getter and setters
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public AudioInputStream getStream() {
            return stream;
        }

        public void setStream(AudioInputStream stream) {
            this.stream = stream;
        }

        ///////////////

        public AudioFormat getFormat() {
            return format;
        }

        public void setFormat(AudioFormat format) {
            this.format = format;
        }

        public DataLine.Info getInfo() {
            return info;
        }

        public void setInfo(DataLine.Info info) {
            this.info = info;
        }

        public Clip getClip() {
            return clip;
        }

        public void setClip(Clip clip) {
            this.clip = clip;
        }
    }
}
