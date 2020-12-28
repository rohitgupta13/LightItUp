package com.blipthirteen.lightitup;

import com.badlogic.gdx.Game;

import com.blipthirteen.lightitup.adhandler.AdHandler;
import com.blipthirteen.lightitup.adhandler.MockAdHandler;
import com.blipthirteen.lightitup.screens.SplashScreen;


public class LightItUp extends Game{

	com.blipthirteen.lightitup.adhandler.AdHandler handler;

	public LightItUp(AdHandler handler)
	{
		if(handler == null){
			handler = new MockAdHandler();
		}
		this.handler = handler;
	}

	@Override
	public void create () {
		handler.showAds(false);
		this.setScreen(new SplashScreen(handler));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}
}
