import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	public String root = "../../";
	public int fps = 60;
	public int width = 800;
	public int height = 600;
	public int game_seconds = 1000 * (60 * 25) * 60;
	public int level_seconds = 1000 * (60 * 5) * 60;
	public int updates = 1;
	public TextureLoader texture_loaderP = new TextureLoader(root + 
			"Assets/Texturas/Player/anim1_walk.png");
	public BufferedImage player = texture_loaderP.loadTexture();
	
	public Game() {
		Dimension dimension = new Dimension(width, height);
		this.setPreferredSize(dimension);
		this.player = player;
	}
	
	public int second(int time) {
		return 1000 * time;
	}
	
	public void update() {
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
		
		g.drawImage(player, width / 2, height / 2, null);
				
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame jframe = new JFrame("Em busca das 5 gemas poderosas");
		jframe.add(game);
		jframe.setLocationRelativeTo(null);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.setVisible(true);
		
		new Thread(game).start();
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
