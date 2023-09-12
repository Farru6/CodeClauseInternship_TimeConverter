package Package1;
import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;
import java.util.TimeZone;

public class TimeConverter extends JFrame
{
	private JLabel heading, heading2, clockLabel1, clockLabel2; 
	private JComboBox<String>countryComboBox;
	Font font=new Font("",Font.BOLD, 30);
	
	public TimeConverter()
	{
		setTitle("Time Converter");
		setSize(800,500);
		setLocationRelativeTo(null);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon bgImage=new ImageIcon("Image/clockbg.jpg");
		Image img= bgImage.getImage();
		Image tempImg= img.getScaledInstance(800, 500, Image.SCALE_DEFAULT);
		bgImage= new ImageIcon(tempImg);
		JLabel bglabel=new JLabel("", bgImage, JLabel.CENTER);
		bglabel.setBounds(0, 0, 800, 500);
		add(bglabel);
		
		
		this.createGUI();
		this.startClock();
		
		countryComboBox=new JComboBox<>(TimeZone.getAvailableIDs());
		countryComboBox.setFont(new Font("", Font.ITALIC, 20));
		countryComboBox.setBounds(300, 175, 300, 50);
		countryComboBox.setBackground(Color.gray);
		countryComboBox.setForeground(Color.BLACK);
		clockLabel2=new JLabel();
		clockLabel2.setBounds(240, 335, 600, 70);
		clockLabel2.setFont(new Font("", Font.BOLD, 20));
		clockLabel2.setForeground(Color.DARK_GRAY);
		
		JButton showTimeButton=new JButton("Converted Time");
		showTimeButton.setFont(new Font("", Font.BOLD, 20));
		showTimeButton.setBackground(Color.gray);
		showTimeButton.setForeground(Color.DARK_GRAY);
		showTimeButton.setBounds(35, 340, 200, 50);
		showTimeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				showCurrentTime();
			}
		});
		
		bglabel.add(heading);
		bglabel.add(clockLabel1);
		bglabel.add(heading2);
		bglabel.add(countryComboBox);
		bglabel.add(showTimeButton);
		bglabel.add(clockLabel2);
		
		setLayout(null);
		setVisible(true);
	}	
		private void createGUI() 
		{
			heading=new JLabel("India Time");
			heading.setBounds(35, 75, 200, 50);
			heading.setForeground(Color.DARK_GRAY);
			clockLabel1=new JLabel("clock");
			clockLabel1.setBounds(300, 75, 400, 50);
			clockLabel1.setForeground(Color.DARK_GRAY);
			heading.setFont(font);
			clockLabel1.setFont(font);
			heading2=new JLabel("Select Country");
			heading2.setBounds(35, 175, 300, 50);
			heading2.setFont(font);
			heading2.setForeground(Color.DARK_GRAY);
	    }

		private void startClock()
		{
			Timer timer=new Timer(1000, new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Date d=new Date();
					
					SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
					
					String dateTime=sfd.format(d);
					
					clockLabel1.setText(dateTime);
				}
			});
			timer.start();
	    }

		private void showCurrentTime()
		{
			String selectedCountry=(String) countryComboBox.getSelectedItem();
			TimeZone timeZone=TimeZone.getTimeZone(selectedCountry);
			
			SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
			timeFormat.setTimeZone(timeZone);
			
			Date currentTime=new Date();
			String formattedTime=timeFormat.format(currentTime);
			
			clockLabel2.setText("Current Time("+selectedCountry+"):"+formattedTime);	
		}	
		
		public static void main(String[] args) 
		{
			new TimeConverter();	
		}
}
