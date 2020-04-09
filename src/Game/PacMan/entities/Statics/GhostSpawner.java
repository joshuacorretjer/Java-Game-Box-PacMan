package Game.PacMan.entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

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
			
				Ghost gs = new Ghost(x,y,18,18, handler, Images.ghost[0]);
				handler.getMap().addEnemy(gs);
//				Map mapInCreation = new Map(handler);
//				for (int i = 0; i < mapImage.getWidth(); i++) {
//					for (int j = 0; j < mapImage.getHeight(); j++) {
//						int currentPixel = mapImage.getRGB(i, j);
//						int xPos = i*MapBuilder.pixelMultiplier;
//						int yPos = j*MapBuilder.pixelMultiplier;
//						if(currentPixel == Ghost){
//							BaseDynamic ghosty = new Ghost(xPos,yPos,MapBuilder.pixelMultiplier,MapBuilder.pixelMultiplier,handler,getSprite(mapImage,i,j));
//							mapInCreation.addEnemy(ghosty);
//				
//				handler.getMap().getEnemiesOnMap().add(gs);
//				return gs;
				

}
//		@Override
//		public void tick() {
//			
//			
//		}
}

