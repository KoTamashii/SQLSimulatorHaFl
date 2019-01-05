package Control;

import MYF.Level;
import MYF.MusicManager;
import MYF.UIDesigner;
import Model.Background;
import Model.Shop;
import Model.Spieler;
import View.Framework.DrawFrame;
import View.Framework.DrawTool;
import View.Framework.DrawableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Haydar Genc on 13.10.2017.
 */
public class GameManager implements DrawableObject{

    private MusicManager musicManager;

    //Attribute
    private String level = "assets/images/level.png";

    public GameManager(DrawFrame df){

        musicManager = new MusicManager();
        musicManager.addNewSoundFile("copycat", "assets/music/copycat.wav",true);
        musicManager.addNewSoundFile("CatSong", "assets/music/CatSong.wav",true);

        musicManager.searchForSoundFileWithName("copycat").startSoundFile();

        JButton playButton = UIDesigner.addButtonWithImageWithStandardDesign("assets/images/play.png", df.getActiveDrawingPanel(),
                                                        new Point((Config.WINDOW_WIDTH/2)-63, (Config.WINDOW_HEIGHT/2)-63 ), new Point(126,126), null);

        df.getActiveDrawingPanel().add(playButton);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                df.getActiveDrawingPanel().remove(playButton);

                new Level(level, df.getActiveDrawingPanel(), df);

                Shop shop = new Shop(df);

                df.getActiveDrawingPanel().addObject(new Spieler(0,0,0,0,null, df.getActiveDrawingPanel(), shop));
            }
        });

    }

    @Override
    public void draw(DrawTool drawTool) {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_X){
            if(musicManager.getCurrentlyPlayingSF().equals(musicManager.searchForSoundFileWithName("copycat"))){
                musicManager.getCurrentlyPlayingSF().stopSoundFile();
                musicManager.searchForSoundFileWithName("CatSong").startSoundFile();
            } else {
                musicManager.getCurrentlyPlayingSF().stopSoundFile();
                musicManager.searchForSoundFileWithName("copycat").startSoundFile();
            }
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
