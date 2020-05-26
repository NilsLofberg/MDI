import javax.swing.*;

public class CustomPanel extends JPanel {
    CustomButton[] buttons = null;
    CustomPanel parent = null;

    public CustomPanel(int n){
        super();
        buttons = new CustomButton[n];
    }

    public CustomPanel(CustomPanel parent){
        super();
        this.parent = parent;
    }

    public void resetBoarders(){
        for(CustomButton b : buttons){
            b.resetBoarder();
        }
    }
}
