import java.awt.Toolkit;

public class Settings
{
	// screen size in pixels
	public static double CANVAS_WIDTH =
			Toolkit.getDefaultToolkit().getScreenSize().getWidth() * .8;
	public static double CANVAS_HEIGHT =
			Toolkit.getDefaultToolkit().getScreenSize().getHeight() * .8;

	// player size in pixels
	public static double PLAYER_START_SIZE = 10;
	// player speed per move in pixels
	public static double PLAYER_SPEED = 10;
	// percentage of opponent size added to player size when ate
	public static double EATING_EFFICIENCY = 0.25;

	// how frequent new opponents appear, higher number means more frequent
	// recommended between 0.02 and 0.10
	public static double SPAWN_FREQUENCY = 0.06;

	// opponent starting size in pixels, should be positive
	public static double DRONE_START_SIZE = 1;
	// opponent starting speed per move in pixels, should be non-negative
	public static double DRONE_START_SPEED = 0;

	// how frequent opponents increase in size and speed, higher number means
	// more frequent
	// recommended between 0.04 and 0.20
	public static double DRONE_GROWTH_FREQUENCY = 0.06;
	// how much opponent's speed increases each growth
	// recommended between 0.05 and 0.20
	public static double DRONE_SPEED_INCREASE = 0.07;
	// how much opponent's size increases each growth
	// recommended between 0.4 and 1.0
	public static double DRONE_SIZE_INCREASE = 1.0;

	// probability opponent will go in straight line each move
	// recommend between 0.4 and 0.9
	public static double DRONE_STRAIGHT_LINE_TENDENCY = 0.90;
}
