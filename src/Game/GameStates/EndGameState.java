package Game.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Handler;

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
	    public void render(Graphics g) {//Shows you your high score and Game Over in the center
	    	g.setColor(Color.WHITE);
	    	g.setFont(new Font("Monospace", Font.BOLD, 64));
	    	FontMetrics fm = g.getFontMetrics();
	    	g.drawString("GAME OVER", (handler.getWidth() - fm.stringWidth("GAME OVER"))/2, handler.getHeight()/2);
	    	g.setFont(new Font("Monospace", Font.BOLD, 32));
	    	fm = g.getFontMetrics();
	    	g.drawString("HIGH-SCORE: "+ handler.getScoreManager().getPacmanHighScore(), (handler.getWidth() - 
	    					fm.stringWidth("HIGH-SCORE: " + handler.getScoreManager().getPacmanHighScore()))/2, handler.getHeight()/2+64);
	    }
	    //*Font metrics code provided by http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Centertext.htm
	    
	    @Override
	    public void refresh() {
	    }
	}