import javax.swing.JFrame;

public class Program {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minha janela");

        Level_1 level_1 = new Level_1();
        frame.add(level_1);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Inicia a thread do Canvas
        Thread thread = new Thread(level_1);
        thread.start();
    }
}
