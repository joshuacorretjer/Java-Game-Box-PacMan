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
	public boolean spawner = false;
	public int count = 0;
	public int i = 0;
	public int j = 1;
	public GhostSpawner(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, sprite);
		// TODO Auto-generated constructor stub
	}
		public void Spawn(){
			if(handler.getPacManState().spawn) {
				Ghost ghosts = new Ghost(x,y,18,18, handler, Images.ghost[i]);
				handler.getMap().addEnemy(ghosts);
				ghosts.setSpeed(j);
				i++;
				j++;
				if(i>3 && j>4) {
				handler.getPacManState().spawn=false;
				}

			}else {
				double ghostID = (Math.random()*10)%4;
				Ghost randGhost = new Ghost(x,y,18,18, handler, Images.ghost[(int)ghostID]);
				handler.getMap().addEnemy(randGhost);
				switch((int)ghostID) {
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
