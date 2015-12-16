import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public abstract class Dot
{
	protected Point location;
	protected double size;
	protected double speed;
	protected Color color;
	private Random rand = new Random();
	private Boolean active;

	public Dot(Color startColor)
	{
		location = new Point(rand.nextInt((int) Settings.CANVAS_WIDTH - (int) size),
				rand.nextInt((int) Settings.CANVAS_HEIGHT - (int) size));
		color = startColor;
		active = true;
	}

	public int getSize()
	{
		return (int) size;
	}

	public void moveLeft()
	{
		if (location.x - speed > 0)
		{
			location.x -= speed;
		}
		else
		{
			moveRight();
		}
	}

	public void moveRight()
	{
		if (location.x + speed + size < Settings.CANVAS_WIDTH)
		{
			location.x += speed;
		}
		else
		{
			moveLeft();
		}
	}

	public void moveUp()
	{
		if (location.y - speed > 0)
		{
			location.y -= speed;
		}
		else
		{
			moveDown();
		}
	}

	public void moveDown()
	{
		if (location.y + speed + size < Settings.CANVAS_HEIGHT)
		{
			location.y += speed;
		}
		else
		{
			moveUp();
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(location.x, location.y, (int) size, (int) size);
	}

	public void putOnScreen()
	{
		if (location.x > Settings.CANVAS_WIDTH)
		{
			location.x = (int) (Settings.CANVAS_WIDTH - size);
		}
		if (location.y > Settings.CANVAS_HEIGHT)
		{
			location.y = (int) (Settings.CANVAS_HEIGHT - size);
		}
	}

	public void eatDot(Dot otherDot)
	{
		double sizeChange = otherDot.size * Settings.EATING_EFFICIENCY;
		size += sizeChange;
		location.x -= (sizeChange / 2);
		location.y -= (sizeChange / 2);
		otherDot.deactivate();
	}

	public Boolean isActive()
	{
		return active;
	}

	public void deactivate()
	{
		active = false;
		color = Color.BLACK;
		size = 0;
		location = new Point(-10000, -10000);
	}

	public static Boolean intersection(Dot d1, Dot d2)
	{
		double rad1 = d1.size / 2;
		double rad2 = d2.size / 2;
		Point cent1 = new Point((int) (d1.location.x + rad1), (int) (d1.location.y + rad1));
		Point cent2 = new Point((int) (d2.location.x + rad2), (int) (d2.location.y + rad2));
		double distBetween = Point.distance(cent1.x, cent1.y, cent2.x, cent2.y);
		return distBetween < (rad1 + rad2);
	}
}
