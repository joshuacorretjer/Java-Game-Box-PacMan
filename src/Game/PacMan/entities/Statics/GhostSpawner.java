package Game.PacMan.entities.Statics;

import java.awt.event.KeyEvent;
import java.util.Random;

import Game.PacMan.entities.Dynamics.Ghost;
import Main.Handler;
import Resources.Images;


public class GhostSpawner extends BaseStatic{//Removed BufferedImage import and variable as it wasn't necessary

	public Random random = new Random();
	public GhostSpawner(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, null);
	}
		public void Spawn(){
			//Spawns the first 4 ghosts at the start of the game
			if(handler.getPacManState().startSpawn) {
				for(int i = 0; i < 4; i++) {
					Ghost ghosts = new Ghost(x,y,width,height,handler, Images.ghost[i]);
					ghosts.setSpeed(i+1);
					handler.getMap().addEnemy(ghosts);
				}
				handler.getPacManState().startSpawn=false;

			}else {
				//For spawning one random ghost
				int id = (int) (Math.random()*10)%4;
				Ghost randGhost = new Ghost(x,y,18,18, handler, Images.ghost[id]);
				handler.getMap().addEnemy(randGhost);
				switch(id) {
				case 0:
					randGhost.setSpeed(1);
					break;
				case 1:
					randGhost.setSpeed(2);
					break;
				case 2:
					randGhost.setSpeed(3);
					break;
				case 3:
					randGhost.setSpeed(4);
					}
			}	
		}
}

