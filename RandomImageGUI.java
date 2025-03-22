import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Abstract class for the image panel
abstract class RandomImage extends JPanel {
    protected BufferedImage selectedImage;
    protected String description;

    public RandomImage() {
        loadImage();
    }

    protected abstract void loadImage();

    protected void setImage(String filePath) {
        try {
            selectedImage = ImageIO.read(new File(filePath));
            if (selectedImage == null) {
                System.out.println("ImageIO.read returned null for: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + filePath);
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selectedImage != null) {
            g.drawImage(selectedImage, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.setColor(Color.RED);
            g.drawString("Image failed to load.", 20, 20);
        }
    }

    public String getDescription() {
        return description;
    }
}

// Subclass that implements image randomization
class RandomLogo extends RandomImage {
    private boolean isHusky;

    public RandomLogo() {
        super(); // this triggers loadImage()
    }

    @Override
    protected void loadImage() {
        isHusky = Math.random() > 0.5;

        if (isHusky) {
            description = "Displaying: Husky Logo";
            setImage("c:\\Users\\16572\\Desktop\\Image_20250321164035.jpg");  // << Update path
        } else {
            description = "Displaying: Fairmont F Logo";
            setImage("c:\\Users\\16572\\Desktop\\Image_20250321164046.jpg");   // << Update path
        }
    }
}

// Main GUI application
public class RandomImageGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Random Logo Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            RandomImage logo = new RandomLogo();
            frame.add(logo);
            frame.setVisible(true);

            // Print which image was displayed
            System.out.println(logo.getDescription());
        });
    }
}
