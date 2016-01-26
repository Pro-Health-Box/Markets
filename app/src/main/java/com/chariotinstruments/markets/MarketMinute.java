package com.chariotinstruments.markets;

/**
 * Created by user on 1/25/16.
 */

import java.math.BigDecimal;

public class MarketMinute {

    private int _minute;
    private BigDecimal  _open;
    private BigDecimal _high;
    private BigDecimal _low;
    private BigDecimal _close;
    private Long _volume;

    //Constructor
    public MarketMinute(){

    }

    //Getters/setters
    public int getMinute(){
        return _minute;
    }

    public void setMinute(int minuteIn){
        _minute = minuteIn;
    }

    public BigDecimal getOpen(){
        return _open;
    }

    public void setOpen(BigDecimal openIn){
        _open = openIn;
    }

    public BigDecimal getHigh(){
        return _high;
    }

    public void setHigh(BigDecimal highIn){
        _high = highIn;
    }

    public BigDecimal getLow(){
        return _low;
    }

    public void setLow(BigDecimal lowIn){
        _low = lowIn;
    }

    public BigDecimal getClose(){
        return _close;
    }

    public void setClose(BigDecimal closeIn){
        _close = closeIn;
    }

    public Long getVolume(){
        return _volume;
    }

    public void setVolume(Long volumeIn){
        _volume = volumeIn;
    }



}