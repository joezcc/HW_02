import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;

public class MyAvatarClock extends MyAvatar implements Runnable{
	protected Thread mainThread;
	protected int delay;

	public void init(){
		mainThread = null;
		delay = 1000;
	}


	public void start(){
		if(mainThread==null){
			mainThread = new Thread(this);
			mainThread.start();
		}
	}
	
	public void run(){
		while(Thread.currentThread() == mainThread){
			repaint();
			try{
				Thread.currentThread();
				Thread.sleep(delay);
			}catch(InterruptedException e){}
		}
	}

	public void paint(Graphics g){
		super.paint(g);
		Date newTime = new Date();
		int second =  newTime.getSeconds();
		paintClock(g);
		
		//fix pupils
		g.setColor(Color.WHITE);
		g.fillRect(160,215,75,40);
		g.fillRect(330,215,75,40);

		//moving eyeballs
		g.setColor(Color.BLACK);
		double buffer = Math.PI*second/30.0;
		int posX = (int) (195+15*Math.cos(buffer-Math.PI/2));
		int posY = (int) (230-15*Math.sin(buffer+Math.PI/2));
		g.fillOval(posX-2, posY-2, 20, 20);
		posX = (int) (360+15*Math.cos(buffer-Math.PI/2));
		posY = (int) (230-15*Math.sin(buffer+Math.PI/2));
		g.fillOval(posX-2, posY-2, 20, 20);

		//active ticking
		for(int i=0 ;i<60 ;i++){
			double b = Math.PI*i/30.0;
			posX = (int) (270+210*Math.cos(b-Math.PI/2));
			posY = (int) (235-210*Math.sin(b+Math.PI/2));
			if(i==second) {
				g.setColor(Color.white);
				if((i%5)!=0){
					g.fillOval(posX-2, posY-2, 4, 4);
					g.setColor(Color.RED);
					g.fillOval(posX-2, posY-2, 5, 5);
				}
				else{
					posX = (int) (270+210*Math.cos(buffer-Math.PI/2));
					posY = (int) (235-210*Math.sin(buffer+Math.PI/2));

					g.drawString(""+i, posX-2, posY-2);
					g.setColor(Color.RED);
					g.drawString(""+i, posX-2, posY-2);
				}
			}
		}
	}

	private void paintClock(Graphics g) {
		for(int i=0 ; i<60 ;i++){
			double buffer = Math.PI*i/30.0;

			int posX = (int) (270+210*Math.cos(buffer));
			int posY = (int) (235-210*Math.sin(buffer));
			g.setColor(Color.BLUE);

			if((i%5)!=0){
				g.fillOval(posX-2, posY-2, 4, 4);
			}
			else{
				posX = (int) (270+210*Math.cos(buffer-Math.PI/2));
				posY = (int) (235-210*Math.sin(buffer+Math.PI/2));
				g.drawString(""+i, posX-2, posY-2);
			}	
		}
	}
}	
	