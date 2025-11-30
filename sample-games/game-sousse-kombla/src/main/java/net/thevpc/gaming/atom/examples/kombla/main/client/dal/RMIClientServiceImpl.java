package net.thevpc.gaming.atom.examples.kombla.main.client.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.dal.RMIClientService;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientServiceImpl extends UnicastRemoteObject implements RMIClientService {

    private MainClientDAOListener listener;

    public RMIClientServiceImpl(MainClientDAOListener listener) throws RemoteException {
        super(0);
        this.listener = listener;

        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
    }

    @Override
    public void modelChanged(DynamicGameModel dynamicGameModel) throws RemoteException {

        listener.onModelChanged(dynamicGameModel);
    }
}
