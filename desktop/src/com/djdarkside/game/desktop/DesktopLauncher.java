package com.djdarkside.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.djdarkside.gameApp.MainApplication;
import com.djdarkside.gameApp.utils.constants.Constants;

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MainApplication(), config);
		config.width = Constants.W_WIDTH;
		config.height = Constants.W_HEIGHT;
		//config.fullscreen = true;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.resizable = true;
		config.title = Constants.V_TITLE;	
		config.vSyncEnabled = true;
	}
}
