package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIMainServerDAO implements MainServerDAO {

    private RMIServerServiceImpl serverService;
    private Registry registry;

    @Override
    public void start(MainServerDAOListener listener, AppConfig properties) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");

            serverService = new RMIServerServiceImpl(listener);
            registry = LocateRegistry.createRegistry(properties.getServerPort());
            registry.rebind("KomblaGameServer", serverService);
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to start RMI server", e);
        }
    }

    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel) {
        serverService.setCurrentModel(dynamicGameModel);
    }
}
