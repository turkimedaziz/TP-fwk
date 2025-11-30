package net.thevpc.gaming.atom.examples.kombla.main.shared.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.Remote;

public interface RMIServerService extends Remote {
    StartGameInfo connect(String playerName) throws java.rmi.RemoteException;

    DynamicGameModel getModel() throws java.rmi.RemoteException;

    void moveRight(int playerID) throws java.rmi.RemoteException;

    void moveLeft(int playerID) throws java.rmi.RemoteException;

    void moveUp(int playerID) throws java.rmi.RemoteException;

    void moveDown(int playerID) throws java.rmi.RemoteException;

    void fire(int playerID) throws java.rmi.RemoteException;

}
