import javax.swing.JOptionPane;

public class Launch
{
	public static void main(String[] args)
	{
		String[] options = new String[2];
		options[0] = new String("Play");
		options[1] = new String("Quit");

		int response = -1;
		while (response != 1)
		{
			response = JOptionPane.showOptionDialog(null,
					"You are a yellow thing. Try to gobble other smaller things. Do not let bigger things gobble you."
							+ "\nArrow keys to move. 'P' to pause. 'R' to restart. 'Q' to quit.",
					"It's time to play a game", 0, JOptionPane.INFORMATION_MESSAGE, null, options,
					null);

			switch (response)
			{
				case 0:
				{
					String[] results = new GameLoop().run();
					JOptionPane.showMessageDialog(null,
							"You have been eaten.\nYou survived for " + results[0]
									+ " seconds\nand scored " + results[1] + " points.",
							"Game over", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				case 1:
				{
					continue;
				}
			}
		}
		System.exit(0);
	}
}
