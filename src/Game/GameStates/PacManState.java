package Game.GameStates;

import Display.UI.UIManager;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Cherry;
import Game.PacMan.entities.Statics.Dot;
import Game.PacMan.entities.Statics.GhostSpawner;
import Game.PacMan.entities.Statics.Orange;
import Game.PacMan.entities.Statics.Strawberry;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PacManState extends State {

    private UIManager uiManager;
    public String Mode = "Intro";
    public int startCooldown = 60*4;//seven seconds for the music to finish
    public boolean spawn = true;
    int counter=4;

    public PacManState(Handler handler){
        super(handler);
        handler.setMap(MapBuilder.createMap(Images.map1, handler));

    }


    @Override
    public void tick() {
    	if(spawn) {
    		handler.getGhostSpawner().Spawn();
    	}else {
        	if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))){
        		handler.getGhostSpawner().Spawn();
    		}
    	}
                	
        if (Mode.equals("Stage")){
            if (startCooldown<=0) {
                for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
                    entity.tick();
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
                        }
                    }else if(blocks instanceof Cherry || blocks instanceof Strawberry || blocks instanceof Orange) {
                    	if (blocks.getBounds().intersects(handler.getPacman().getBounds())) {
                    		handler.getMusicHandler().playEffect("pacman_eatfruit.wav");
                            toREmove.add(blocks);
                            handler.getScoreManager().addPacmanCurrentScore(120);
                    	}
                    }
                }
                for (BaseStatic removing: toREmove){
                    handler.getMap().getBlocksOnMap().remove(removing);
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


}
