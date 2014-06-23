package vn.fynngamadeyo.adengo.graphics;

public class Screen {
	
	private int width, height;
	public int[] pixels;
	
	int time =0;
	int counter=0;
	
	public Screen(int width, int heiht){
		this.width=width;
		this.height=heiht;
		pixels = new int[width*heiht];
	}
	
	public void clear(){
		for(int i=0;i<pixels.length;i++){
			pixels[i]=0;
		}
	}
	
	public void render(){
	
		for(int y=0;y<height;y++){
			for(int x=0; x<width;x++){
				pixels[x + y * width] = 0xff0ff;
			}
		}
	}
	
}
