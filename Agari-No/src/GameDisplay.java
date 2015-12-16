import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameDisplay
{
	public GameCanvas canvas;
	public GameStats stats;
	public SettingsPanel settings;
	private List<Drone> drones;
	private Player player;

	GameDisplay()
	{
		canvas = new GameCanvas();
		stats = new GameStats();
		settings = new SettingsPanel();
		drones = new ArrayList<Drone>();
	}

	public void updateDisplay(Player player, List<Drone> drones)
	{
		this.player = player;
		this.drones = drones;
		canvas.repaint();
		stats.repaint();
	}

	@SuppressWarnings("serial")
	class GameCanvas extends JPanel
	{
		public GameCanvas()
		{
			super();
			setPreferredSize(
					new Dimension((int) Settings.CANVAS_WIDTH, (int) Settings.CANVAS_HEIGHT));
		}

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			setBackground(Color.BLACK);
			for (Drone d : drones)
			{
				d.draw(g);
			}
			player.draw(g);
		}
	}

	@SuppressWarnings("serial")
	class GameStats extends JPanel
	{
		JLabel time = new JLabel();
		JLabel size = new JLabel();
		JLabel spacing = new JLabel("      ");

		GameStats()
		{
			super();
			setLayout(new FlowLayout());
			this.setBackground(new Color(82, 87, 150));

			this.add(time);
			this.add(spacing);
			this.add(size);
			time.setForeground(Color.WHITE);
			size.setForeground(Color.WHITE);
		}

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			GameLoop.timePassed = (System.currentTimeMillis() - GameLoop.startTime);
			GameLoop.score = (int) (player.getSize() - Settings.PLAYER_START_SIZE);

			time.setText("TIME: "
					+ new DecimalFormat("0.00").format(GameLoop.timePassed / (double) 1000));
			size.setText("SCORE: " + String.valueOf(GameLoop.score));
		}
	}
}
