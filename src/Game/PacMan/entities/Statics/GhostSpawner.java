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
	public GhostSpawner(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, sprite);
		// TODO Auto-generated constructor stub
	}
		public void Spawn(){
			Random random = new Random();
			ArrayList<BaseDynamic> ghosts = handler.getMap().getEnemiesOnMap();
			for(int i = 0;ghosts.size()<5;i++) {
				Ghost ghosty = new Ghost(x,y,18,18, handler, Images.ghost[i]);
				handler.getMap().addEnemy(ghosty);
				switch(i) {
				case 0:
					ghosty.setSpeed(1);
					break;
				case 1:
					ghosty.setSpeed(1.5);
					break;
				case 2:
					ghosty.setSpeed(2);
					break;
				case 3:
					ghosty.setSpeed(2.5);
				}
			}

			if((handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))) {
			int id = random.nextInt(4);
			Ghost randGhost = new Ghost(x,y,18,18, handler, Images.ghost[id]);
			handler.getMap().addEnemy(randGhost);
			switch(id) {
			case 0:
				randGhost.setSpeed(1);
				break;
			case 1:
				randGhost.setSpeed(1.5);
				break;
			case 2:
				randGhost.setSpeed(2);
				break;
			case 3:
				randGhost.setSpeed(2.5);
				}
			}
		}
}
		
			

		

//		@Override
//		public void tick() {
//			
//			
//		}


