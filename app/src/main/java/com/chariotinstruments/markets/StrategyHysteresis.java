package com.chariotinstruments.markets;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by user on 5/10/16.
 */
public class StrategyHysteresis implements StrategyInterface{

    private Activity uiActivity;
    private PhaseOneIndicatorControl indicators;
    private boolean isUp;

    public boolean preTradeFavorableConditionsFound;
    public boolean tradeableConditionsFound;

    public StrategyHysteresis(Activity activity, PhaseOneIndicatorControl indicatorsIn){
        uiActivity = activity;
        indicators = indicatorsIn;
    }

    public void setIndicatorControl(PhaseOneIndicatorControl indicatorControlIn){
        indicators = indicatorControlIn;
    }

    public void checkConditions() {
        preTradeFavorableConditionsFound();

        if (preTradeFavorableConditionsFound) {
            tradeableConditionsFound();
        }
    }

    public boolean preTradeFavorableConditionsFound(){
        boolean emaDiffBool;

        if(indicators.getRSI() > 71){
            isUp = false;
            preTradeFavorableConditionsFound = true;
        }else if(indicators.getRSI() < 29){
            isUp = true;
            preTradeFavorableConditionsFound = true;
        }

        return false;

    }

    private boolean preTradeEMADiff(){
        ArrayList<MarketCandle> marketCandles = new ArrayList<MarketCandle>();
        marketCandles = indicators.getMarketDay().getMarketCandles();

        for(int i = indicators.getEma50List().size()-9; i < indicators.getEma50List().size(); i++){
            double diff = 0.0;
            diff = Math.abs(indicators.getEma50List().get(i) - marketCandles.get(i).getClose());
            if (diff < .10){
                //found, return true;
                return true;
            }
        }
        //Did not find a diff of >45 in the last 10 minutes since we crossed the 71/29 RSI threshold
        //reset the pretrade to false. this means the clock starts ticking after we cross
        preTradeFavorableConditionsFound = false;
        return false;
    }

    public boolean tradeableConditionsFound(){
        tradeableConditionsFound = false;

        if(preTradeFavorableConditionsFound) { //first checked that we've crossed the first hysteresis threshold.
            if (isUp) { //if we're trading up (IE RSI was below 29)
                if (indicators.getRSI() > 35 && preTradeEMADiff()) { //if we've crossed the buying hysteresis mark;
                    tradeableConditionsFound = true;
                }
            } else { //Otherwise RSI was above 71
                if (indicators.getRSI() < 65 &&preTradeEMADiff()) { //if we've crossed the selling hysteresis mark;
                    tradeableConditionsFound = true;
                }
            }
        }
        //interface wants boolean, returning false for now.
        return false;
    }

    public boolean getTradeableConditionsFound(){
        return tradeableConditionsFound;
    }

    public boolean getIsUp(){ return isUp; }

}
