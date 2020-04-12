package Game.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Display.UI.UIManager;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Cherry;
import Game.PacMan.entities.Statics.Dot;
import Main.Handler;
import Resources.Images;

public class PacManState extends State {

    private UIManager uiManager;
    public String Mode = "Intro";
    public boolean canEatGhost = false; //Added a variable if Pac-Man can eat ghosts
    public int health = 3, eatGhostCooldown = 15*60, startCooldown = 60*4;//seven seconds for the music to finish
    //Added health to PacManState so the map reset doesn't reset it and cooldown for Pac-Man eating ghosts
    
    public PacManState(Handler handler){
        super(handler);
        handler.setMap(MapBuilder.createMap(Images.map1, handler));
    }

    @Override
    public void tick() {
        if (Mode.equals("Stage")){
            if (startCooldown<=0) {
            	if(handler.getMap().getBlocksOnMap().isEmpty()) {
            		handler.getMap().reset();
            	}
                for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
                	entity.tick();
////                	if(entity instanceof Ghost) { //If Pac-Man eats a ghost during the power up, he will get 500 points 
////                		if ((entity.getBounds().intersects(handler.getPacman().getBounds()) || handler.getPacman().getBounds().intersects(entity.getBounds())) && canEatGhost){
////                            ((Ghost) entity).onPacManCollision();
////                        }
//                	}
                }
                ArrayList<BaseStatic> toREmove = new ArrayList<>();
                for (BaseStatic blocks: handler.getMap().getBlocksOnMap()){
                    if (blocks instanceof Dot){
                        if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
                            handler.getMusicHandler().playEffect("pacman_chomp.wav");
                            toREmove.add(blocks);
                            handler.getScoreManager().addPacmanCurrentScore(10);
                        }
                    }else if (blocks instanceof BigDot){
                        if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
                            handler.getMusicHandler().playEffect("pacman_chomp.wav");
                            toREmove.add(blocks);
                            handler.getScoreManager().addPacmanCurrentScore(100);
                            
                            //Activates BigDot power up
                            canEatGhost = true;
                            eatGhostCooldown = 15*60;
                        }
                    }else if(blocks instanceof Cherry) {
                    	if (blocks.getBounds().intersects(handler.getPacman().getBounds())) {
                    		handler.getMusicHandler().playEffect("pacman_chomp.wav");
                            toREmove.add(blocks);
                            handler.getScoreManager().addPacmanCurrentScore(120);
                    	}
                    }
                }
                for (BaseStatic removing: toREmove){
                    handler.getMap().getBlocksOnMap().remove(removing);
                }
                
                if(canEatGhost && eatGhostCooldown >= 0) { //Cooldown for Pac-Man BigDot power up
                	eatGhostCooldown--;
                }else{
                	canEatGhost = false;
                }
                
                if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))){
            		handler.getGhostSpawner().Spawn();
        		}
            }else{
                startCooldown--;
            }
        }else if (Mode.equals("Menu")){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                Mode = "Stage";
                handler.getMusicHandler().playEffect("pacman_beginning.wav");
            }
        }else{
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                Mode = "Menu";
            }
        }
    }

    @Override
    public void render(Graphics g) {

        if (Mode.equals("Stage")){
            Graphics2D g2 = (Graphics2D) g.create();
            handler.getMap().drawMap(g2);
        	handler.getGhostSpawner().Spawn();
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString("Score: " + handler.getScoreManager().getPacmanCurrentScore(),(handler.getWidth()/2) + handler.getWidth()/6, 25);
            g.drawString("High-Score: " + handler.getScoreManager().getPacmanHighScore(),(handler.getWidth()/2) + handler.getWidth()/6, 75);
        }else if (Mode.equals("Menu")){
            g.drawImage(Images.start,0,0,handler.getWidth()/2,handler.getHeight(),null);
        }else{
            g.drawImage(Images.intro,0,0,handler.getWidth()/2,handler.getHeight(),null);
        }
    }

    @Override
    public void refresh() {
    }
    
    public int getHealth(){return health;}					//Added health getter
    public void setHealth(int health){this.health = health;}//Added health setter
}
