package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.dal.RMIServerService;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerServiceImpl extends UnicastRemoteObject implements RMIServerService {

    private MainServerDAOListener listener;
    private DynamicGameModel currentModel;

    public RMIServerServiceImpl(MainServerDAOListener listener) throws RemoteException {
        this.listener = listener;
    }

    @Override
    public StartGameInfo connect(String playerName) throws RemoteException {
        return listener.onReceivePlayerJoined(playerName);
    }

    @Override
    public DynamicGameModel getModel() throws RemoteException {
        return currentModel;
    }

    @Override
    public void moveRight(int playerID) throws RemoteException {
        listener.onReceiveMoveRight(playerID);
    }

    @Override
    public void moveLeft(int playerID) throws RemoteException {
        listener.onReceiveMoveLeft(playerID);
    }

    @Override
    public void moveUp(int playerID) throws RemoteException {
        listener.onReceiveMoveUp(playerID);
    }

    @Override
    public void moveDown(int playerID) throws RemoteException {
        listener.onReceiveMoveDown(playerID);
    }

    @Override
    public void fire(int playerID) throws RemoteException {
        listener.onReceiveReleaseBomb(playerID);
    }

    public void setCurrentModel(DynamicGameModel model) {
        this.currentModel = model;
    }
}
