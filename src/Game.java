//Mitchell Bylak 2/15/2022 Homework 3

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	Brick brick;
	Link link;
	

	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A4 - Link Collision");
		this.setSize(512, 535);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
	} 

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
	
	

	public void run()
	{
		while(true) {
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paint
			Toolkit.getDefaultToolkit().sync(); // Updates screen
	
			// Go to sleep for 5
			try {
				Thread.sleep(25);
			} 
			catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
}
