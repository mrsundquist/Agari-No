import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends Dot
{
	public KeyAdapter playerControls =
			new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent evt)
				{
					speed = Settings.PLAYER_SPEED;
					switch (evt.getKeyCode())
					{
						case KeyEvent.VK_LEFT:
							moveLeft();
							break;
						case KeyEvent.VK_RIGHT:
							moveRight();
							break;
						case KeyEvent.VK_UP:
							moveUp();
							break;
						case KeyEvent.VK_DOWN:
							moveDown();
							break;
					}
				}
			};

	public Player()
	{
		super(Color.YELLOW);
		speed = Settings.PLAYER_SPEED;
		size = Settings.PLAYER_START_SIZE;
	}
}
