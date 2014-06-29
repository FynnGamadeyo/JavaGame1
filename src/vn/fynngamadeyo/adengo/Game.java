package vn.fynngamadeyo.adengo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import vn.fynngamadeyo.adengo.graphics.Screen;

public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = 35324;
	
	public static int width = 300;
	public static int height = width/ 16 * 9;
	static int scale = 3;
	
	int x = 0;
	int y=0;
	
	private Thread thread;
	private boolean running = false;
	
	public JFrame frame;
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		screen = new Screen(this.width,this.height);
		
		frame = new JFrame();
	}
	
	public synchronized void start(){
		running=true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running=false;
		try {
		thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void update(){
		y++;
		x++;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer =  System.currentTimeMillis();
		
		final double ns = 1000000000.0 / 60.0;
		double delta=0;
		int frames=0;
		int updates=0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime=now;
			while(delta >=1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if((System.currentTimeMillis() - timer) > 1000 ){
				timer +=1000;
				System.out.println(updates + " ups, "+ frames + " fps");
				frame.setTitle(updates + " ups, "+ frames + " fps");
				frames=0;
				updates=0;
			}
		}
		stop();
	}

	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		screen.render(x,y);
		
		for(int i=0;i<pixels.length;i++){
			pixels[i]=screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
	
}
