package vn.fynngamadeyo.adengo.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	private final int SIZE;
	public int[] pixels;
	
	public SpriteSheet(String path, int size){
		this.path=path;
		this.SIZE=size;
		pixels = new int[this.SIZE*this.SIZE];
		load();
	}

	private void load(){
		
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(this.path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0,image.getWidth();,image.getHeight();,pixels,0,width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
