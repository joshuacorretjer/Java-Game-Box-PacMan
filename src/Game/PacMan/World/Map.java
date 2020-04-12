package Game.PacMan.World;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Main.Handler;
import Resources.Images;

public class Map {

    ArrayList<BaseStatic> blocksOnMap;
    ArrayList<BaseDynamic> enemiesOnMap;
    Handler handler;
    private double bottomBorder;
    private Random rand;
    private int mapBackground;
    private int blink = 0; //Variable for BigDot blink

    public Map(Handler handler) {
        this.handler=handler;
        this.rand = new Random();
        this.blocksOnMap = new ArrayList<>();
        this.enemiesOnMap = new ArrayList<>();
        bottomBorder=handler.getHeight();
        this.mapBackground = this.rand.nextInt(6);
    }

    public void addBlock(BaseStatic block){
        blocksOnMap.add(block);
    }

    public void addEnemy(BaseDynamic entity){
        enemiesOnMap.add(entity);
    }

    public void drawMap(Graphics2D g2) {
        for (BaseStatic block:blocksOnMap) {
        	if(block instanceof BigDot && blink%60 <= 29){} //Animation for BigDot and it blinks each second
        	else {
        		g2.drawImage(block.sprite, block.x, block.y, block.width, block.height, null);
        	}
        }
        for (BaseDynamic entity:enemiesOnMap) {
            if (entity instanceof PacMan) {
                switch (((PacMan) entity).facing){
                    case "Right":
                        g2.drawImage(((PacMan) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                    case "Left":
                        g2.drawImage(((PacMan) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                    case "Up":
                        g2.drawImage(((PacMan) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                    case "Down":
                        g2.drawImage(((PacMan) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                }
            }else if(entity instanceof Ghost && handler.getPacManState().canEatGhost && ((Ghost)entity).stayInSpawnerCooldown<0) {//Added blue ghost animation when Pac-Man eats a BigDot
            		g2.drawImage(((Ghost) entity).ghostBlueAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
            }else if(entity instanceof Ghost && (((Ghost) entity).stayInSpawnerCooldown >= 0)) {
            		g2.drawImage(Images.ghostEyes, entity.x, entity.y, entity.width, entity.height, null);
            }
            else {
                g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
            }
        }
        for (int i = 0; i< handler.getPacManState().health;i++) {//Draws the current remaining lives of Pac-Man
            g2.drawImage(Images.pacmanRight[0], (3*handler.getWidth()/4)+i*64, handler.getHeight()-handler.getHeight()/4, 48, 48, null);
        }
        
        if(blink > 10000000) {blink = 0;} //Resets blink if it gets too big
        else {blink++;}
    }

    public ArrayList<BaseStatic> getBlocksOnMap() {
        return blocksOnMap;
    }

    public ArrayList<BaseDynamic> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public void reset() {//Implemented the map reset function
    	handler.setMap(MapBuilder.createMap(Images.map1, handler));
    }
}
