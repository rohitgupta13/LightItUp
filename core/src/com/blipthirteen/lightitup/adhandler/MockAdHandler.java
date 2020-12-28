package com.blipthirteen.lightitup.adhandler;

public class MockAdHandler implements AdHandler{

    @Override
    public void showAds(boolean show) {
        System.out.println("Showing ads: " + show);
    }
}
