import java.awt.Color;
import java.util.Random;

public class Drone extends Dot
{
	private static Random rand = new Random();
	private int lastDirection; // 0(up), 1(right), 2(down), 3(left)
	private int age;

	public Drone()
	{
		super(randColor());
		lastDirection = rand.nextInt(4);
		speed = Settings.DRONE_START_SPEED;
		size = Settings.DRONE_START_SIZE;
		age = 0;
	}

	public void increaseAge()
	{
		age++;
		if (age % ((int) (1 / Settings.DRONE_GROWTH_FREQUENCY)) == 0)
		{
			size += Settings.DRONE_SIZE_INCREASE;
			speed += Settings.DRONE_SPEED_INCREASE;
		}
	}

	public void moveRand()
	{
		switch (chooseDirection())
		{
			case 0:
				moveUp();
				break;
			case 1:
				moveRight();
				break;
			case 2:
				moveDown();
				break;
			case 3:
				moveLeft();
				break;
		}
	}

	private int chooseDirection()
	{
		double next = rand.nextDouble();
		if (next < Settings.DRONE_STRAIGHT_LINE_TENDENCY)
		{
			lastDirection = lastDirection + 0;
		}
		else
		{
			next = rand.nextDouble();
			if (next < 0.333)
			{
				lastDirection = lastDirection + 1;
			}
			else if (next < 0.667)
			{
				lastDirection = lastDirection + 2;
			}
			else
			{
				lastDirection = lastDirection + 3;
			}
		}
		return lastDirection % 4;
	}

	private static Color randColor()
	{
		return new Color(rand.nextInt(190) + 55, rand.nextInt(190) + 55, rand.nextInt(190) + 55);
	}
}
