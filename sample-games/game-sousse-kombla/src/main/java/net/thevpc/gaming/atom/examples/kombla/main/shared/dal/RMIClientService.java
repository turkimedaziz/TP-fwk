package net.thevpc.gaming.atom.examples.kombla.main.shared.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.Remote;

public interface RMIClientService extends Remote {
    void modelChanged(DynamicGameModel dynamicGameModel) throws java.rmi.RemoteException;
}
