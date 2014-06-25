package vn.fynngamadeyo.adengo.graphics;

public class Screen {
	private int width, height;
	public int[] pixels;
	
	int xtime,ytime;
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
		counter++;
		if(counter % 100==0) xtime++;
		if(counter % 80==0) ytime++;
		
		for(int y=0;y<height;y++){
			if(ytime <0 || ytime >=height) break;
			for(int x=0; x<width;x++){
				if(xtime <0 || xtime >= width ) break;
				pixels[x + y * width] = 0xff0ff;
			}
		}
	}
	
}
