package Game.PacMan.entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import Game.PacMan.World.Map;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Main.Handler;
import Resources.Images;


public class GhostSpawner extends BaseStatic{

	private static BufferedImage sprite;
	public boolean gameStart = false;
	public boolean spawn = false;
	public GhostSpawner(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, sprite);
		// TODO Auto-generated constructor stub
	}
		public void Spawn(){

//				Ghost blinky = new Ghost(x,y,18,18, handler, Images.ghost[0]);
//				blinky.setSpeed(1);
//				handler.getMap().addEnemy(blinky);
			
			
//			Ghost blinky = new Ghost(x,y,18,18, handler, Images.ghost[0]);
//			blinky.setSpeed(1);
//			
//			Ghost pinky = new Ghost(x,y,18,18, handler, Images.ghost[1]);
//			pinky.setSpeed(2);
//			
//			Ghost inky = new Ghost(x,y,18,18, handler, Images.ghost[2]);
//			inky.setSpeed(2.5);
//			
//			Ghost clyde = new Ghost(x,y,18,18, handler, Images.ghost[3]);
//			clyde.setSpeed(3);
			if(gameStart=true) {
			Random random = new Random();
			ArrayList<BaseDynamic> ghosty = handler.getMap().getEnemiesOnMap();
			for(int i = 0;ghosty.size()!=5;i++) {
				Ghost gs = new Ghost(x,y,18,18, handler, Images.ghost[i]);
				handler.getMap().addEnemy(gs);
				switch(i) {
				case 0:
					gs.setSpeed(1);
					break;
				case 1:
					gs.setSpeed(1.5);
					break;
				case 2:
					gs.setSpeed(2);
					break;
				case 3:
					gs.setSpeed(2.5);
					break;
				}}
//			return ghosty[];
			}
		}
}
		
			

		

//		@Override
//		public void tick() {
//			
//			
//		}


