import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ImageViewer {
	
	public void imageViewer () {
		SwingUtilities.invokeLater(new Runnable() {
			
			// making the frame for the picture to go into it
			public void run() {
				JFrame iconFrame = new JFrame("Weather Icon");
				iconFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				BufferedImage img = null;
				
				// trying to make the image/can catch the error
				try {
					// img = ImageIO.read(getClass().getResource(")
				}
				
			}
		});
	}
}
