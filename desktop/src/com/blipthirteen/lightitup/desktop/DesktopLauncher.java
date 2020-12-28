package com.blipthirteen.lightitup.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blipthirteen.lightitup.LightItUp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new LightItUp(null), config);

		config.height = 1920/4;
		config.width = 1080/4;
	}
}
