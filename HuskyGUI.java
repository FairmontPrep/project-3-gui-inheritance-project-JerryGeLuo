import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Abstract Parent Class: HuskyBase
abstract class HuskyBase extends JPanel {
    private BufferedImage baseImage;
    protected String description = "Base: Husky Outline ";

    public HuskyBase() {
        loadImage();
        setPreferredSize(new Dimension(400, 400)); // Ensures correct panel size
    }

    protected abstract void loadImage();

    protected void setBaseImage(String filePath) {
        System.out.println("Attempting to load: " + filePath); // Debugging print
        try {
            baseImage = ImageIO.read(new File(filePath));
            if (baseImage == null) {
                System.out.println("Error: Image file is invalid -> " + filePath);
            } else {
                System.out.println("Successfully loaded: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("IOException: Can't read file -> " + filePath);
            e.printStackTrace();
        }
    }

    protected BufferedImage getBaseImage() {
        return baseImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (baseImage != null) {
            g.drawImage(baseImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public String getDescription() {
        return description;
    }
}

// First Child Class: Adds Details to the Husky
class HuskyWithDetails extends HuskyBase {
    private BufferedImage detailsImage;

    public HuskyWithDetails() {
        super();
        description += "-> Added Facial Details ";
    }

    @Override
    protected void loadImage() {
        setBaseImage("C:/path/to/your/images/husky_outline.png"); // UPDATE PATH
        try {
            String filePath = "C:/path/to/your/images/husky_details.png"; // UPDATE PATH
            System.out.println("Loading image: " + filePath);
            detailsImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error loading details image!");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (detailsImage != null) {
            g.drawImage(detailsImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

// Second Child Class: Adds Fur Color
class HuskyWithColor extends HuskyWithDetails {
    private BufferedImage colorImage;

    public HuskyWithColor() {
        super();
        description += "-> Added Fur Colors ";
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            String filePath = "C:/path/to/your/images/husky_color.png"; // UPDATE PATH
            System.out.println("Loading image: " + filePath);
            colorImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error loading fur color image!");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (colorImage != null) {
            g.drawImage(colorImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

// Third Child Class: Adds Background
class HuskyWithBackground extends HuskyWithColor {
    private BufferedImage bgImage;

    public HuskyWithBackground() {
        super();
        description += "-> Added Background ";
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            String filePath = "C:/path/to/your/images/husky_background.png"; // UPDATE PATH
            System.out.println("Loading background image: " + filePath);
            bgImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error loading background image! Check file path.");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

// Fourth Child Class: Adds Random Eye Color
class HuskyWithRandomFeature extends HuskyWithBackground {
    private BufferedImage randomFeatureImage;
    private boolean variant; // Randomly chooses between two options

    public HuskyWithRandomFeature() {
        super();
        variant = Math.random() > 0.5;
        description += variant ? "-> Variant A (Blue Eyes) " : "-> Variant B (Brown Eyes) ";
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            String filePath;
            if (variant) {
                filePath = "C:/path/to/your/images/husky_blue_eyes.png"; // UPDATE PATH
            } else {
                filePath = "C:/path/to/your/images/husky_brown_eyes.png"; // UPDATE PATH
            }
            System.out.println("Loading eye color image: " + filePath);
            randomFeatureImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error loading eye color image!");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (randomFeatureImage != null) {
            g.drawImage(randomFeatureImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

// Main Class to Display GUI
public class HuskyGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Husky Logo GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            HuskyBase husky = new HuskyWithRandomFeature();
            frame.add(husky);

            frame.setVisible(true);

            // Print description
            System.out.println(husky.getDescription());
        });
    }
}

