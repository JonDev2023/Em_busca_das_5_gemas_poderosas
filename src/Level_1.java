import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Level_1 extends Canvas implements Runnable {
	
	public String root = "../../";
	public int fps = 60;
	public int width = 800;
	public int height = 600;
	public int game_seconds = 1000 * (60 * 25) * 60;
	public int level_seconds = 1000 * (60 * 5) * 60;
	public int updates = 1;
	public TextureLoader texture_loaderP = new TextureLoader(root + 
			"Assets/Texturas/Player/Walk/anim1.png");
	public BufferedImage player = texture_loaderP.loadTexture();
	public int player_x = width - 96 / 2;
	public int player_y = height - 96 / 2;
	public String anim;
	public String anim1 = root + 
			"Assets/Texturas/Player/Walk/anim1.png";
	
	public Level_1() {
		Dimension dimension = new Dimension(width, height);
		this.setPreferredSize(dimension);
		this.player = player;
	}
	
	public int second(int time) {
		return 1000 * time;
	}
	
	public void update() {
		// vai ter um if
		anim = anim1;
		game_seconds--;
		level_seconds--;
		if(game_seconds <= 0) {
			// Game Over
			System.out.println("Game Over");
			game_seconds = 0;
		}
		if(level_seconds <= 0) {
			// Game Over
			System.out.println("Waring!");
			level_seconds = 0;
		}
		texture_loaderP = new TextureLoader(anim);
		player = texture_loaderP.loadTexture();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 23));
		g.drawString("Segundos Opcionais do Level: " + (level_seconds / 60), width - 600, 40);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 23));
		g.drawString("Segundos Necessários do Jogo: " + (game_seconds / 60), width - 600, 90);
		
		g.drawImage(player, player_x, player_y, null);
				
		bs.show();
	}

	@Override
	public void run() {
		
		while(true) {
			update();
			render();
			try {
				Thread.sleep(1000 / fps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
