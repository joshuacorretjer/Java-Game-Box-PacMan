package Game.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Handler;
import Resources.Images;

public class EndGameState extends State{//EndGameState created
	   public EndGameState(Handler handler){
	        super(handler);
	    }

	    @Override
	    public void tick() {
	    	//When game over, you can restart
	    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
	    		handler.getPacManState().restartGame();
	    		handler.getMusicHandler().stopMusic();
	    		State.setState(handler.getPacManState());
	    		
	    	}
	    }

	    @Override
	    public void render(Graphics g) {//Shows you your high score
	    	g.setColor(Color.WHITE);
	    	g.setFont(new Font("Monospace", Font.BOLD, 64));
	    	g.drawString("GAME OVER", handler.getWidth()/4+handler.getHeight()/10, handler.getHeight()/2);
	    	g.setFont(new Font("Monospace", Font.BOLD, 32));
	    	g.drawString("HIGH-SCORE:  "+ handler.getScoreManager().getPacmanHighScore(), handler.getWidth()/5+handler.getWidth()/10, handler.getHeight()/2+64);
	    }
	    
	    @Override
	    public void refresh() {
	    }
	}