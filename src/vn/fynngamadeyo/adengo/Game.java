package vn.fynngamadeyo.adengo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = 35324;
	
	static int width = 300;
	static int height = width/ 16 * 9;
	static int scale = 3;
	
	
	private Thread thread;
	private boolean running = false;
	
	public JFrame frame;
	
	private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
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

	public void run() {
		Manager manager = new Manager();
		while(running){
			manager.update();
			render();
		}
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();
	}
	
}
