package MYF;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputManager {

    public void keyTyped(KeyEvent keyEvent);

    public void keyPressed(KeyEvent keyEvent);

    public void keyReleased(KeyEvent keyEvent);

    public void mouseClicked(MouseEvent mouseEvent);

    public void mousePressed(MouseEvent mouseEvent);

    public void mouseReleased(MouseEvent mouseEvent);

    public void mouseEntered(MouseEvent mouseEvent);

    public void mouseExited(MouseEvent mouseEvent);

    public void mouseDragged(MouseEvent mouseEvent);

    public void mouseMoved(MouseEvent mouseEvent);

    public void actionPerformed(ActionEvent actionEvent);
}
