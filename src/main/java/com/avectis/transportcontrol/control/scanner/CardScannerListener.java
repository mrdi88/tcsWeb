package com.avectis.transportcontrol.control.scanner;

/**
 *
 * @author Ivan
 */
public interface CardScannerListener {
    public void onCardLogined(String CardNumberHEX,String CardNumberDEC);
    public void onCardRemoved();
}
