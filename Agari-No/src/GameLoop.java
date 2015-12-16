import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GameLoop extends JFrame
{
	private Boolean gameRunning;
	private Boolean gamePaused;
	private Player player;
	private List<Drone> drones;
	private GameDisplay display;
	public static long startTime;
	public static long timePassed;
	public static long pausedTime;
	public static int score;

	public GameLoop()
	{
		display = new GameDisplay();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(display.canvas);
		getContentPane().add(display.stats, BorderLayout.SOUTH);
		getContentPane().add(new JScrollPane(display.settings), BorderLayout.EAST);

		restart();

		setTitle("Agari-No");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private KeyAdapter gameControls =
			new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent evt)
				{
					switch (evt.getKeyCode())
					{
						case KeyEvent.VK_R:
							restart();
							break;
						case KeyEvent.VK_Q:
							gameRunning = false;
							break;
						case KeyEvent.VK_P:
							togglePause();
							break;
					}
				}
			};

	public ComponentAdapter resizeControl = new ComponentAdapter()
	{
		@Override
		public void componentResized(ComponentEvent evt)
		{
			Settings.CANVAS_WIDTH = display.canvas.getWidth();
			Settings.CANVAS_HEIGHT = display.canvas.getHeight();
			player.putOnScreen();
			for (Drone d : drones)
			{
				d.putOnScreen();
			}
		}
	};

	public String[] run()
	{
		Settings.CANVAS_WIDTH = display.canvas.getWidth(); // may be smaller or
															// bigger
		// than requested
		Settings.CANVAS_HEIGHT = display.canvas.getHeight();
		while (gameRunning)
		{
			requestFocus();
			long lastLoopTime = System.nanoTime();
			if (!gamePaused)
			{
				display.updateDisplay(player, drones);
				doGameUpdates();
			}
			sleep(lastLoopTime);

		}
		String[] results = new String[2];
		results[0] = new DecimalFormat("0.00").format(timePassed / (double) 1000);
		results[1] = String.valueOf(score);
		this.dispose();
		return results;
	}

	private void doGameUpdates()
	{
		List<Drone> deactivatedDrones = new ArrayList<Drone>();
		for (Drone d : drones)
		{
			if (d.isActive())
			{
				d.moveRand();
				d.increaseAge();
				if (Dot.intersection(d, player))
				{
					if (d.getSize() > player.getSize())
					{
						gameRunning = false;
					}
					else
					{
						player.eatDot(d);
						deactivatedDrones.add(d);
					}
				}
				for (Drone e : drones)
				{
					if (Dot.intersection(d, e)) // room for optimization here
					{
						if (d.getSize() > e.getSize())
						{
							d.eatDot(e);
							deactivatedDrones.add(e);
						}
						else if (d.getSize() < e.getSize())
						{
							e.eatDot(d);
							deactivatedDrones.add(d);
						}
					}
				}
			}
		}

		for (Drone d : deactivatedDrones)
		{
			drones.remove(d);
		}

		if (System.nanoTime() % ((int) (1 / Settings.SPAWN_FREQUENCY)) == 0)
		{
			drones.add(new Drone());
		}
	}

	private void sleep(long lastLoopTime)
	{
		final int TARGET_FPS = 20;
		final long OPTIMAL_NANOSECONDS_EACH_FRAME = 1000000000 / TARGET_FPS;

		try
		{
			long timeToSleep =
					(OPTIMAL_NANOSECONDS_EACH_FRAME
							- (System.nanoTime() - lastLoopTime))
							/ 1000000;
			if (timeToSleep >= 0)
			{
				Thread.sleep(timeToSleep);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private void restart()
	{
		gameRunning = false;
		gamePaused = false;
		player = new Player();
		drones = new ArrayList<Drone>();
		display.updateDisplay(player, drones);
		addKeyListener(player.playerControls);
		removeKeyListener(this.gameControls); // needed for pause after restart
		addKeyListener(this.gameControls);
		this.addComponentListener(this.resizeControl);
		startTime = System.currentTimeMillis();
		gameRunning = true;
	}

	private void togglePause()
	{
		gamePaused = !gamePaused;
		if (gamePaused)
		{
			removeKeyListener(player.playerControls);
			pausedTime = System.currentTimeMillis();
		}
		else
		{
			addKeyListener(player.playerControls);
			startTime += (System.currentTimeMillis() - pausedTime);
		}
	}

}
