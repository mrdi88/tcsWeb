package com.avectis.transportcontrol.control.scanner;

/**
 *
 * @author Ivan
 */
public interface CardScannerAdapter {
    public void addDataListener(DataListener listener);
    public void removeDataListener();
}
