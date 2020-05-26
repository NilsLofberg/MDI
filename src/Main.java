import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;



public class Main extends JFrame{
    JPanel pane;
    JPanel[] panels;
    JPanel wardrobePanel, infoPanel, centerPanel;
    JLabel[] selectedClothingLabels;
    JButton[] wardrobeButtons;

    public Main(){
        setSize(1920,1080);

        initiatePanels();
        makeBackPanels();
        makeWardrobePanel();
        makeInfoPanel();
        makeCenterPanels();
        makeMainPanel();
        centerPanel.add(panels[0]);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void updateMainPanel(){
        panels[0].removeAll();
        panels[0].setBackground(new Color(255,255,255));
        panels[0].setLayout(new GridLayout(3,2));
        Dimension dim = panels[0].getPreferredSize();
        int w = dim.width;
        int h = dim.height;


        for(int i = 0; i < 6; i++){
            panels[0].add(selectedClothingLabels[i]);
        }
    }

    public void resetWardrobePanel(){
        for(JButton b : wardrobeButtons){
            b.setBackground(new Color(255,255,255));
        }
    }

    public void initiatePanels(){
        panels = new JPanel[7]; //main, shirts, pants, suits, shoes, accessoaries, ties
        for (int i = 0; i < panels.length; i++){
            panels[i] = new JPanel();
        }

        wardrobePanel = new JPanel();
        infoPanel = new JPanel();
        centerPanel = new JPanel();

        pane = new JPanel();
        pane.setBackground(new Color(0,0,0));
        pane.setLayout(new BorderLayout());
        add(pane);
    }

    public void makeBackPanels(){
        wardrobePanel.setPreferredSize(new Dimension(480, 1080));
        infoPanel.setPreferredSize(new Dimension(480, 1080));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));


        wardrobePanel.setBackground(new Color(255,255,255));
        infoPanel.setBackground(new Color(255,255,255));
        centerPanel.setBackground(new Color(0,0,0));


        pane.add(wardrobePanel, BorderLayout.LINE_START);
        pane.add(infoPanel, BorderLayout.LINE_END);
        pane.add(centerPanel, BorderLayout.CENTER);

    }

    public void makeWardrobePanel(){

        Dimension dim = wardrobePanel.getPreferredSize();
        int h = dim.height;
        int w = dim.width;


        wardrobeButtons = new JButton[6];
        String[] buttonLabels = {"Shirts", "Pants", "Suits", "Shoes", "Accessories", "Ties"};
        String[] locations = {
                "assets/Icons/shirt.png",
                "assets/Icons/pants.png",
                "assets/Icons/suit.png",
                "assets/Icons/shoes.png",
                "assets/Icons/accessories.png",
                "assets/Icons/tie.png"
        };

        for(int i = 0; i < wardrobeButtons.length; i++){
            int index = i;

            BufferedImage tempimg = null;

            try {
                String path = locations[i];
                tempimg = ImageIO.read(getClass().getResourceAsStream(path));
                tempimg = resize(tempimg, h/16);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JLabel tempLab  = new JLabel();

            tempLab.setSize(new Dimension(w,(int)(h/6.5)));
            tempLab.setPreferredSize(tempLab.getSize());
            tempLab.setIcon(new ImageIcon(tempimg));
            tempLab.setHorizontalAlignment(JLabel.RIGHT);
            tempLab.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));



            wardrobeButtons[i] = new JButton(buttonLabels[i]);
            wardrobeButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            wardrobeButtons[i].setLayout(null);
            wardrobeButtons[i].setSize(new Dimension(w,(int)(h/6.5)));
            wardrobeButtons[i].setPreferredSize(wardrobeButtons[i].getSize());
            wardrobeButtons[i].setBackground(new Color(255,255,255));
            wardrobeButtons[i].setBorderPainted(true);
            wardrobeButtons[i].setFocusPainted(true);
            wardrobeButtons[i].setContentAreaFilled(true);
            wardrobeButtons[i].add(tempLab);



            wardrobePanel.add(wardrobeButtons[i]);

            wardrobeButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    centerPanel.removeAll();
                    centerPanel.add(panels[index+1]);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                    resetWardrobePanel();
                    wardrobeButtons[index].setBackground(new Color( 230, 230, 230));
                    wardrobePanel.revalidate();
                    wardrobePanel.repaint();
                }
            });
        }






    }

    public void makeInfoPanel(){
        infoPanel.setLayout(new BorderLayout());
        Dimension dim = infoPanel.getPreferredSize();
        int w = dim.width;
        int h = dim.height;

        JLabel weatherLabel = new JLabel("dfdfs");
        BufferedImage weatherImage = null;
        try {
            weatherImage = ImageIO.read(getClass().getResourceAsStream("assets/InfoPanel/Weather.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        weatherLabel.setSize(2, h/3);
        weatherLabel.setIcon(new ImageIcon(weatherImage));
        infoPanel.add(weatherLabel, BorderLayout.PAGE_START);


    }

    public void makeMainPanel(){
        panels[0].removeAll();
        panels[0].setBackground(new Color(255,255,255));
        panels[0].setLayout(new GridLayout(3,2));
        Dimension dim = panels[0].getPreferredSize();
        int w = dim.width;
        int h = dim.height;

        selectedClothingLabels = new JLabel[6];
        String[] location = {
                "assets/Shirts/Shirt_0.jpg",
                "assets/Pants/Pants_0.jpg",
                "assets/Suits/Suit_0.jpg",
                "assets/Shoes/Shoes_0.jpg",
                "assets/Accessoaries/Accessoaries_0.jpg",
                "assets/Ties/Tie_0.jpg"
        };
        for(int i = 0; i < selectedClothingLabels.length; i++){
            selectedClothingLabels[i] = new JLabel();

            BufferedImage tempImage = null;

            try {
                tempImage = ImageIO.read(getClass().getResourceAsStream(location[i]));
                tempImage = resize(tempImage, 220);
            } catch (Exception e) {
                e.printStackTrace();
            }



            selectedClothingLabels[i].setPreferredSize(new Dimension(w/5, w/5));
            // selectedClothingLabels[i].setFont(UtilityClass.getFont(IconLabel.getHeight()/3));
            selectedClothingLabels[i].setIcon(new ImageIcon(tempImage));
            selectedClothingLabels[i].setHorizontalAlignment(JLabel.CENTER);
            //selectedClothingLabels[i].setOpaque(true);
            //selectedClothingLabels[i].setLocation(w/10 + (i%3)*3*w/10, w/10 + (int)Math.floor(i/3)*3*w/10);

            panels[0].add(selectedClothingLabels[i]);
        }

    }

    public void makeCenterPanels(){
        centerPanel.setLayout(new BorderLayout());
        Dimension dim = centerPanel.getPreferredSize();
        int h = 1080;
        int w = 954;

        String[] location = {
                "",
                "assets/Shirts/Shirt_",
                "assets/Pants/Pants_",
                "assets/Suits/Suit_",
                "assets/Shoes/Shoes_",
                "assets/Accessoaries/Accessoaries_",
                "assets/Ties/Tie_"
        };

        int[] nnumberOfClothing = {0, 10, 6, 7, 5, 5, 4};

        for(int i = 0; i < panels.length; i++){
            panels[i].setLayout(null);
            panels[i].setPreferredSize(new Dimension(w,h));
            panels[i].setSize(w,h);
            panels[i].setBackground(new Color(0, 0, 0));

            JPanel timePanel = new JPanel();
            timePanel.setLayout(null);
            timePanel.setBackground(new Color(255,255,255));
            timePanel.setSize(w,h/12);
            timePanel.setPreferredSize(timePanel.getSize());
            timePanel.setLocation(0,0);
            timePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));

            BufferedImage tempimg = null;

            try {
                tempimg = ImageIO.read(getClass().getResourceAsStream("assets/Icons/back.png"));
                tempimg = resize(tempimg, h/20);
            } catch (Exception e) {
                e.printStackTrace();

            }
            JLabel tempLab  = new JLabel();
            tempLab.setPreferredSize(new Dimension( h/12, h/12));
            tempLab.setIcon(new ImageIcon(tempimg));
            tempLab.setHorizontalAlignment(JLabel.CENTER);

            JButton backButton = new JButton();
            backButton.setSize(h/12, h/12);
            backButton.setPreferredSize(backButton.getSize());
            backButton.setBackground(new Color(4,4,244));
            backButton.setLocation(0,0);
            backButton.setBorderPainted(false);
            backButton.setFocusPainted(true);
            backButton.setContentAreaFilled(false);
            backButton.add(tempLab);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    centerPanel.removeAll();
                    centerPanel.add(panels[0]);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                    resetWardrobePanel();
                }
            });

            timePanel.add(backButton);


            CustomPanel contentPanel = new CustomPanel(nnumberOfClothing[i]);
            contentPanel.setBackground(new Color(255,255,255));
            contentPanel.setSize(w,11*h/12);
            contentPanel.setPreferredSize(timePanel.getSize());
            contentPanel.setLocation(0,h/12 + 3);
            contentPanel.setLayout(null);
           // contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));


            for (int j = 0; j < nnumberOfClothing[i]; j++){
                CustomPanel pane = new CustomPanel(contentPanel);
                pane.setLayout(null);
                pane.setBackground(new Color(255,255,255));
                pane.setSize(220,220);
                pane.setPreferredSize(timePanel.getSize());
                pane.setLocation(70 + (j%3)*295 ,70 + (int)Math.floor(j/3)*295);

                BufferedImage tempImage = null;
                String imgLocation = location[i] + j + ".jpg";
                try {
                    tempImage = ImageIO.read(getClass().getResourceAsStream(imgLocation));
                    tempImage = resize(tempImage, 220);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(imgLocation);
                }
                JLabel tempLabel  = new JLabel();
                tempLabel.setPreferredSize(new Dimension( 220, 220));
                tempLabel.setIcon(new ImageIcon(tempImage));
                tempLabel.setHorizontalAlignment(JLabel.CENTER);


                boolean r, a;
                if (0 <= j && j <= 1){
                    r = true; a = true;
                } else if (2 <= j && j <= 2){
                    r = false; a = true;
                } else {
                    r = false; a = false;
                }
                contentPanel.buttons[j] = new CustomButton(this, pane, i, tempLabel, r, a);
                contentPanel.buttons[j].setSize(220, 220);
                contentPanel.buttons[j].setPreferredSize(backButton.getSize());
                contentPanel.buttons[j].setBackground(new Color(0,255,255));
                contentPanel.buttons[j].setLocation(0,0);
                contentPanel.buttons[j].setBorderPainted(true);
                contentPanel.buttons[j].setFocusPainted(true);
                contentPanel.buttons[j].setContentAreaFilled(false);
                contentPanel.buttons[j].setOpaque(false);



                pane.add(contentPanel.buttons[j]);
                contentPanel.add(pane);
            }
            if (i != 0){
                contentPanel.buttons[0].setBorder(BorderFactory.createLineBorder(new Color(0,255,0), 4));
            }

            panels[i].add(timePanel);
            panels[i].add(contentPanel);

        }


    }

    private BufferedImage resize(BufferedImage src, int targetSize) {
        if (targetSize <= 0) {
            return src; //this can't be resized
        }
        int targetWidth = targetSize;
        int targetHeight = targetSize;
        float ratio = ((float) src.getHeight() / (float) src.getWidth());
        if (ratio <= 1) { //square or landscape-oriented image
            targetHeight = (int) Math.ceil((float) targetWidth * ratio);
        } else { //portrait image
            targetWidth = Math.round((float) targetHeight / ratio);
        }
        BufferedImage bi = new BufferedImage(targetWidth, targetHeight, src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //produces a balanced resizing (fast and decent quality)
        g2d.drawImage(src, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return bi;
    }

    public static void main(String[] args){
        new Main();
    }
}
