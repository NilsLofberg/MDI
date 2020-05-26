import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class CustomButton extends JButton {
    Main main;
    CustomPanel parent;
    int index;
    JLabel img;
    JLabel buffImg;
    boolean recomended;
    boolean available;

    public CustomButton(Main main, CustomPanel parent, int index, JLabel img, boolean recomended, boolean available){
        super();
        this.main = main;
        this.parent = parent;
        this.index = index;
        this.img = img;
        this.recomended = recomended;
        this.available = available;
        if (!available){
            //this.setEnabled(false);
        }
        setLayout(new BorderLayout());
        bufferImage();
        add(img);
        addActionListener(AL);

        resetBoarder();

    }

    private void bufferImage(){
        JLabel tempLabel = new JLabel();
        Icon tempImage = img.getIcon();
        tempLabel.setPreferredSize(new Dimension( 220, 220));
        tempLabel.setIcon(tempImage);
        tempLabel.setHorizontalAlignment(JLabel.CENTER);

        buffImg = tempLabel;
    }

    public void resetBoarder(){

        if (recomended){
            setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 4));
        } else {
            setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 2));
        }
    }

    ActionListener AL = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            main.selectedClothingLabels[index-1] = buffImg;
            parent.parent.resetBoarders();
            setBorder(BorderFactory.createLineBorder(new Color(0,255,0), 4));
            main.updateMainPanel();
            bufferImage();

        }
    };

}
