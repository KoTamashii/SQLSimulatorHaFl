package MYF;


import View.Framework.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class UIDesigner {


    public static JButton addButtonWithImageWithStandardDesign(String filePath, DrawingPanel activePanel,
                                                               Point location, Point size, String text){
        JButton jButton = new JButton(new ImageIcon(filePath));

        //Setting up the JButton
        jButton.setVisible(true);
        jButton.setBounds(location.x, location.y, size.x, size.y);
        jButton.setText(text);
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        //Adding the Object to the active DrawingPanel(that extends from JPanel)
        activePanel.add(jButton);
        activePanel.validate();

        return jButton;
    }

    public static JButton addButtonWithImageWithStandardDesign(String filePath,
                                                               Point location, Point size, String text){
        JButton jButton = new JButton(new ImageIcon(filePath));

        //Setting up the JButton
        jButton.setVisible(true);
        jButton.setBounds(location.x, location.y, size.x, size.y);
        jButton.setText(text);
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        return jButton;
    }

    public static JButton addButtonWithImageWithCustomDesign(String filePath, DrawingPanel activePanel,
                                                             Point location, String text){
        JButton jButton = new JButton(new ImageIcon(filePath));

        ////////////UPDATE////////

        //Setting up the JButton

        return jButton;
    }

    public static JButton addButtonWithStandardDesign(DrawingPanel activePanel,
                                                      Point location, String text){
        JButton jButton = new JButton();


        //////////UPDATEEE//////

        //Setting up the JButton

        return jButton;
    }



}
