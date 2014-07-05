package vn.fynngamadeyo.adengo.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x,y;
	public int pixels[];
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16,SpriteSheet.tiles,0,0);
	
	
	public Sprite(int size,SpriteSheet sheet , int x, int y){
		this.SIZE=size;
		this.pixels = new int[SIZE*SIZE];
		this.sheet=sheet;
		this.x=x*size;
		this.y=y*size;
		load();
	}
	
	private void load(){
		
		for(int y = 0; y < SIZE;y++){
			for(int x=0; x< SIZE;x++){
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x)+ (y+this.y) * sheet.SIZE];
			}
		}
		
	}

}
