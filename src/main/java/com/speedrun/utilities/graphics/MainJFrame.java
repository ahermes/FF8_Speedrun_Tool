package com.speedrun.utilities.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;
import javax.swing.border.Border;

import com.speedrun.utilities.GlobalValues;
import com.speedrun.utilities.toolkit.UtilitiesToolkit;

public class MainJFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private int pX, pY;
	private String name;
	private int width;
	private int height;
	private ImagePanel mainFrame;
	private JScrollPane scrolledMainFrame;
	private Point position;
	private Image icon;
	private Image background;


	public MainJFrame(String name, int width, int height, Point position) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.mainFrame = new ImagePanel();
		this.position = position;
		this.icon = new ImageIcon(GlobalValues.ACCESSIBLE_RESSOURCES + "icon.png").getImage();
		this.background = new ImageIcon(this.getClass().getResource("/imageFenetre/bg.png")).getImage();
		this.getContentPane().setBackground(Color.BLACK);
	}

	public void addPanelWithScroll(Component panel) {
		this.mainFrame.removeAll();
//		this.removeAll();
		loadAll();
		JPanel contentPanel = new JPanel();

		if(this.background != null) {
			Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
			contentPanel.setBorder(padding);
		}

		contentPanel.add(panel);
		contentPanel.setOpaque(false);
		this.mainFrame.add(contentPanel);
		if(height > -1 && width > -1) {
			this.setSize(width, height);
		}else {
			this.pack();
			if(height > -1) {
				this.setSize(width, this.getHeight());
			}
			if(width > -1) {
				this.setSize(this.getWidth(), height);
			}
		}
		if(this.background != null) {
			this.setSize(this.getWidth() + 20, this.getHeight() + 20);
			this.mainFrame.setMinSize(this.getWidth()-20, this.getHeight()-40);
		}else {
			this.mainFrame.setMinSize(this.getWidth(), this.getHeight());
		}
		addBackground(true);
	}

	public void setNewBackground(Image background){
		this.background = background;
	}

	private JPanel addTopBarMenu() {
		JPanel closePane = new JPanel(new BorderLayout());

		JLabel label = new JLabel("  " + name);
		UtilitiesToolkit.setSizeOfComponent(closePane, new Dimension(this.getWidth(),GlobalValues.LINE_BASE_SIZE));

		JButton closeButton = new JButton(new ImageIcon(this.getClass().getResource("/imageFenetre/close.png")));
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		UtilitiesToolkit.setSizeOfComponent(closeButton, new Dimension(GlobalValues.LINE_BASE_SIZE,GlobalValues.LINE_BASE_SIZE));
		closePane.add(label, BorderLayout.WEST);
		closePane.add(closeButton, BorderLayout.EAST);


		closePane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				pX = me.getX();
				pY = me.getY();

			}
			public void mouseDragged(MouseEvent me) {

				setLocation(getLocation().x + me.getX() - pX,
						getLocation().y + me.getY() - pY);
			}
		});

		closePane.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				setLocation(getLocation().x + me.getX() - pX,
						getLocation().y + me.getY() - pY);
			}
		});
		return closePane;
	}

	private void addBackground(Boolean scroll) {
		if(this.background != null) {
			this.mainFrame.setOpaque(true);
			this.mainFrame.setBackground(this.background);
		}else {
			this.mainFrame.setOpaque(false);
		}
		this.mainFrame.updateUI();
	}

	private void loadAll(){
		scrolledMainFrame = UtilitiesToolkit.addJScrollPane(mainFrame);
		scrolledMainFrame.setOpaque(false);
		this.add(scrolledMainFrame);
		this.setTitle(name);
		this.setIconImage(this.icon);
		this.setLocationRelativeTo((Component)null);
		this.setDefaultCloseOperation(2);
		this.setResizable(true);
		this.setFocusable(true);
		if(position != null) {
			this.setLocation(position);
		}
		this.setVisible(true);
	}

	public void setPosition(Point position) {
		this.position = position;
	}
}
