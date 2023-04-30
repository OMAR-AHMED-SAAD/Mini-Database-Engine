package fonts;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fonts {
	public Font myfont;
	public Fonts(String type) {
		try {
			if(type.equals("d"))
			myfont=Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Cyberverse Condensed.otf")).deriveFont(20f);
			else if(type.equals("i"))
				myfont=Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Roboto-Italic.ttf")).deriveFont(20f);
			else if(type.equals("welcome"))
				myfont=Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Metropolis-Medium.otf")).deriveFont(20f);
			else 
				myfont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Oswald-Medium.ttf")).deriveFont(20f);
			if(myfont==null) 
				myfont=new Font("Roboto",Font.PLAIN, 20);
			
			GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(myfont);
		}
		catch(IOException | FontFormatException e ) {
			
		}
	}

}
