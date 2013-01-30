import java.awt.Graphics;
import java.awt.Image;
public class MyAvatarClockDB extends MyAvatarClock {

	public void paint(Graphics g){
		Image image = createImage(800,800);
		Graphics offscreen = image.getGraphics();
		g.drawImage(image,0,0,this);
		super.paint(offscreen);
	}
	public void update(Graphics g){
		super.paint(g);
	}
}