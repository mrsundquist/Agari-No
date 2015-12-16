import java.awt.Color;
import java.util.Hashtable;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel
{
	public SettingsPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(163, 166, 205));

		add(new JLabel("Player Start Size:"));
		add(new PlayerStartSizeSlider());
		add(new JLabel("  "));

		add(new JLabel("Player Speed:"));
		add(new PlayerSpeedSlider());
		add(new JLabel("  "));

		add(new JLabel("Gobble Efficiency:"));
		add(new EatingEfficiencySlider());
		add(new JLabel("  "));
		add(new JLabel("  "));

		add(new JLabel("Drone Spawn Frequency:"));
		add(new SpawnFrequencySlider());
		add(new JLabel("  "));

		add(new JLabel("Drone Start Size:"));
		add(new DroneStartSizeSlider());
		add(new JLabel("  "));

		add(new JLabel("Drone Start Speed:"));
		add(new DroneStartSpeedSlider());
		add(new JLabel("  "));

		add(new JLabel("Drone Growth Frequency:"));
		add(new DroneGrowthRateSlider());
		add(new JLabel("  "));

		add(new JLabel("Drone Acceleration:"));
		add(new DroneSpeedIncreaseSlider());
		add(new JLabel("  "));

		add(new JLabel("Drone Growth Size:"));
		add(new DroneGrowthSizeSlider());
		add(new JLabel("  "));

		add(new JLabel("Drone Spaz Factor:"));
		add(new DroneStraightLineTendencySlider());
	}

	class PlayerStartSizeSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 300; // 3
		static final int MAX = 5500; // 55
		static final String MIN_LABEL = "Small";
		static final String MAX_LABEL = "Large";
		static final double CONVERSION_FACTOR = 100;

		public PlayerStartSizeSlider()
		{
			super(MIN, MAX, (int) (Settings.PLAYER_START_SIZE * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.PLAYER_START_SIZE = newSetting;
			}
		}
	}

	class PlayerSpeedSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 100; // 1
		static final int MAX = 3000; // 30
		static final String MIN_LABEL = "Slow";
		static final String MAX_LABEL = "Fast";
		static final double CONVERSION_FACTOR = 100;

		public PlayerSpeedSlider()
		{
			super(MIN, MAX, (int) (Settings.PLAYER_SPEED * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.PLAYER_SPEED = newSetting;
			}
		}
	}

	class EatingEfficiencySlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 100; // .10
		static final int MAX = 500; // .50
		static final String MIN_LABEL = "Weak";
		static final String MAX_LABEL = "Strong";
		static final double CONVERSION_FACTOR = 1000;

		public EatingEfficiencySlider()
		{
			super(MIN, MAX, (int) (Settings.EATING_EFFICIENCY * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.EATING_EFFICIENCY = newSetting;
			}
		}
	}

	class SpawnFrequencySlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 200; // 0.02
		static final int MAX = 4000; // 0.40
		static final String MIN_LABEL = "Rare";
		static final String MAX_LABEL = "Frequent";
		static final double CONVERSION_FACTOR = 10000;

		public SpawnFrequencySlider()
		{
			super(MIN, MAX, (int) (Settings.SPAWN_FREQUENCY * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.SPAWN_FREQUENCY = newSetting;
			}
		}
	}

	class DroneStartSizeSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 100; // 1
		static final int MAX = 1500; // 15
		static final String MIN_LABEL = "Tiny";
		static final String MAX_LABEL = "Great";
		static final double CONVERSION_FACTOR = 100;

		public DroneStartSizeSlider()
		{
			super(MIN, MAX, (int) (Settings.DRONE_START_SIZE * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.DRONE_START_SIZE = newSetting;
			}
		}
	}

	class DroneStartSpeedSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 0; // 0
		static final int MAX = 1200; // 12
		static final String MIN_LABEL = "Slow";
		static final String MAX_LABEL = "OMG";
		static final double CONVERSION_FACTOR = 100;

		public DroneStartSpeedSlider()
		{
			super(MIN, MAX, (int) (Settings.DRONE_START_SPEED * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.DRONE_START_SPEED = newSetting;
			}
		}
	}

	class DroneGrowthRateSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 200; // 0.02
		static final int MAX = 2000; // 0.20
		static final String MIN_LABEL = "Timid";
		static final String MAX_LABEL = "Cancerous";
		static final double CONVERSION_FACTOR = 10000;

		public DroneGrowthRateSlider()
		{
			super(MIN, MAX, (int) (Settings.DRONE_GROWTH_FREQUENCY * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.DRONE_GROWTH_FREQUENCY = newSetting;
			}
		}
	}

	class DroneSpeedIncreaseSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 500; // 0.05
		static final int MAX = 2400; // 0.22
		static final String MIN_LABEL = "Meager";
		static final String MAX_LABEL = "Eager";
		static final double CONVERSION_FACTOR = 10000;

		public DroneSpeedIncreaseSlider()
		{
			super(MIN, MAX, (int) (Settings.DRONE_SPEED_INCREASE * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.DRONE_SPEED_INCREASE = newSetting;
			}
		}
	}

	class DroneGrowthSizeSlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 400; // 0.4
		static final int MAX = 1200; // 1.2
		static final String MIN_LABEL = "Small";
		static final String MAX_LABEL = "Considerable";
		static final double CONVERSION_FACTOR = 1000;

		public DroneGrowthSizeSlider()
		{
			super(MIN, MAX, (int) (Settings.DRONE_SIZE_INCREASE * CONVERSION_FACTOR), MIN_LABEL,
					MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.DRONE_SIZE_INCREASE = newSetting;
			}
		}
	}

	class DroneStraightLineTendencySlider extends CustomSettingsSlider implements ChangeListener
	{
		static final int MIN = 450; // 0.45
		static final int MAX = 970; // 0.97
		static final String MIN_LABEL = "Confused";
		static final String MAX_LABEL = "Determined";
		static final double CONVERSION_FACTOR = 1000;

		public DroneStraightLineTendencySlider()
		{
			super(MIN, MAX, (int) (Settings.DRONE_STRAIGHT_LINE_TENDENCY * CONVERSION_FACTOR),
					MIN_LABEL, MAX_LABEL);
			this.addChangeListener(this);
		}

		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting())
			{
				int val = source.getValue();
				double newSetting = (double) val / CONVERSION_FACTOR;
				Settings.DRONE_STRAIGHT_LINE_TENDENCY = newSetting;
			}
		}
	}

	abstract class CustomSettingsSlider extends JSlider
	{
		public CustomSettingsSlider(int min, int max, int initial, String minLabel, String maxLabel)
		{
			super(JSlider.HORIZONTAL, min, max, initial);

			Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
			labelTable.put(min, new JLabel(minLabel));
			labelTable.put(max, new JLabel(maxLabel));
			setLabelTable(labelTable);
			setMajorTickSpacing((int) ((max - min) / 100));
			setPaintLabels(true);

			setBackground(new Color(163, 166, 205));
		}
	}
}
