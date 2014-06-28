package vn.fynngamadeyo.adengo.graphics;

import java.util.Random;

public class Screen {
	private int width, height;
	public int[] pixels;
	
	public int[] tiles = new int[64*64];
	
	private Random random = new Random();

	public Screen(int width, int heiht){
		this.width=width;
		this.height=heiht;
		pixels = new int[width*heiht];
		
		for(int i=0;i< 64*64;i++){
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear(){
		for(int i=0;i<pixels.length;i++){
			pixels[i]=0;
		}
	}

	public void render(){
		for(int y=0;y<height;y++){
			if(y <0 || y >=height) break;
			for(int x=0; x<width;x++){
				if(x <0 || x >= width ) break;
				// x / 16 equal x >> 4
				int tileIndex = (x >> 4) + (y >> 4) * 64;
				
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}
	
}
