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
				double id = (Math.random()*10)%4;
				Ghost randGhost = new Ghost(x,y,18,18, handler, Images.ghost[(int)id]);
				handler.getMap().addEnemy(randGhost);
				switch((int)id) {
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
//			Random random = new Random();
//			ArrayList<BaseDynamic> ghosts = handler.getMap().getEnemiesOnMap();
//			for(int i = 0;ghosts.size()<5;i++) {
//				Ghost ghosty = new Ghost(x,y,18,18, handler, Images.ghost[i]);
//				handler.getMap().addEnemy(ghosty);
//				switch(i) {
//				case 0:
//					ghosty.setSpeed(1);
//					break;
//				case 1:
//					ghosty.setSpeed(2);
//					break;
//				case 2:
//					ghosty.setSpeed(3);
//					break;
//				case 3:
//					ghosty.setSpeed(4);
//				}
//			}
//
//			
//			double id = (Math.random()*10)%4;
//			Ghost randGhost = new Ghost(x,y,18,18, handler, Images.ghost[(int)id]);
//			handler.getMap().addEnemy(randGhost);
//			switch((int)id) {
//			case 0:
//				randGhost.setSpeed(1);
//				break;
//			case 1:
//				randGhost.setSpeed(2);
//				break;
//			case 2:
//				randGhost.setSpeed(3);
//				break;
//			case 3:
//				randGhost.setSpeed(4);
//				}
			
			
			
			}
		}

		
			

		

//		@Override
//		public void tick() {
//			
//			
//		}


