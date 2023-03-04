import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureLoader {
	public String filename;

	public TextureLoader(String send_filename) {
		filename = send_filename;
	}
	
	public BufferedImage loadTexture() {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }
}
