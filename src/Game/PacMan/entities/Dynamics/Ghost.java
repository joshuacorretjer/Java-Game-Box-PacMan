package Game.PacMan.entities.Dynamics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import Game.PacMan.World.MapBuilder;

import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Ghost extends BaseDynamic{

	Random random = new Random();
    protected double velX,velY,speed;
    public String facing = "Up";//Changed the direction of the ghost to "Up" so that they can leave the spawner.
    public boolean moving = true;
    public Animation ghostBlueAnim; //Removed variables belonging to only Pac-Man
    public int stayInSpawnerCooldown = -1; //Cooldown so ghosts stay in spawner
    
    public Ghost(int x, int y, int width, int height, Handler handler, BufferedImage ghost) {
        super(x, y, width, height, handler,ghost);
        ghostBlueAnim = new Animation(256,Images.ghostBlue); //Animation for blue ghost during power-up
    }

    @Override
    public void tick(){
    	if(handler.getPacManState().canEatGhost) {
    		ghostBlueAnim.tick();
    	}
        switch (facing){ //Removed anything regarding Pac-Man only
            case "Right":
                x+=velX;
                break;
            case "Left":
                x-=velX;
                break;
            case "Up":
                y-=velY;
                break;
            case "Down":
                y+=velY;
                break;
        }
        
        if (facing.equals("Right") || facing.equals("Left")){
            checkHorizontalCollision();
        }else{
            checkVerticalCollisions();
        }
    }

    public void checkVerticalCollisions() {
        Ghost ghost = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

        boolean ghostDies = false;
        boolean toUp = moving && facing.equals("Up");

        Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();
        if(stayInSpawnerCooldown >= 0) { //Makes sure that ghost stays in spawn after being eaten
        	velY=0;
        	stayInSpawnerCooldown--;
        }else {
            velY = speed;
        }
        for (BaseStatic brick : bricks) {
            if (brick instanceof BoundBlock) {
                Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
                if (ghostBounds.intersects(brickBounds)) {
                    velY = 0;
                  //Added switch case for when the ghost collides with a wall, it randomly picks direction to move.
        			switch(random.nextInt(2)){
        			case 0:
        				facing = "Left";
        				velX -= speed;
        				break;
        			case 1:
        				facing = "Right";
        				velX += speed;
        				break;
        			}
                    if (toUp)
                        ghost.setY(brick.getY() + ghost.getDimension().height);
                    else
                        ghost.setY(brick.getY() - brick.getDimension().height);
                }
            }
        }

        for(BaseDynamic enemy : enemies){
            Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
            if(enemy instanceof PacMan && ghostBounds.intersects(enemyBounds)) {//Ensuring that enemy is Pac-Man
        		ghostDies = true;
        		break;
            }
        }
        if(ghostDies) {
        	this.onPacManCollision();//Function for what happens if it collides with Pac-Man
        }
    }

    public void checkHorizontalCollision(){
        Ghost ghost = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
        velX = speed;
        boolean ghostDies = false;
        boolean toRight = moving && facing.equals("Right");
        Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();
        for(BaseDynamic enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if(ghostBounds.intersects(enemyBounds) && (enemy instanceof PacMan)) {//Ensuring that enemy is Pac-Man
            	ghostDies = true;
            	break;
            }
        }
        if(ghostDies) {
           this.onPacManCollision();//Function for what happens if it collides with Pac-Man
        }else {
            for (BaseStatic brick : bricks) {
                if (brick instanceof BoundBlock) {
                    Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
                    if (ghostBounds.intersects(brickBounds)) {
                        velX = 0;
                      //Added switch case for when the ghost collides with a wall, it randomly picks direction to move.
            			switch(random.nextInt(2)){
	            			case 0:
	            				facing = "Down";
	            				velY -= speed;
	            				break;
	            			case 1:
	            				facing = "Up";
	            				velY += speed;
	            				break;
	            			}
                        if (toRight)
                            ghost.setX(brick.getX() - ghost.getDimension().width);
                        else
                            ghost.setX(brick.getX() + brick.getDimension().width);
                    }
                }
            }
        }
    }

    public void onPacManCollision() {//Function for ghost collision with Pac-Man
    	if(handler.getPacManState().canEatGhost) {//If ghost can be eaten, it is sent back to and stays in spawner, points are added, and sound plays
            stayInSpawnerCooldown = (random.nextInt(5)+1)*60;
    		this.x = handler.getGhostSpawner().x;    
    		this.y = handler.getGhostSpawner().y;
    		facing = "Up";
    		
            handler.getScoreManager().addPacmanCurrentScore(500);
            handler.getMusicHandler().playEffect("pacman_eatghost.wav");
    	}
    	else {//If ghost can't be eaten, Pac-Man dies
    		handler.getPacManState().health--;
    		handler.getPacManState().resetPacMan();//Pac-Man is now the one that gets reset
    	}
    }

    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }

	public double getSpeed() {//Ghost speed getter
		return speed;
	}

	public void setSpeed(double speed) {//Ghost speed setter
		this.speed = speed;
	}

}
