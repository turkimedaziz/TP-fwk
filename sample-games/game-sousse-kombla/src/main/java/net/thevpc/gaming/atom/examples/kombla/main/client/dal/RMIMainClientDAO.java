package net.thevpc.gaming.atom.examples.kombla.main.client.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.dal.RMIServerService;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIMainClientDAO implements MainClientDAO {

    private RMIServerService serverService;
    private int playerId;
    private AppConfig config;
    private MainClientDAOListener listener;

    @Override
    public void start(MainClientDAOListener listener, AppConfig properties) {
        this.config = properties;
        this.listener = listener;
    }

    @Override
    public StartGameInfo connect() {
        try {
            Registry registry = LocateRegistry.getRegistry(
                    config.getServerAddress(),
                    config.getServerPort());

            serverService = (RMIServerService) registry.lookup("KomblaGameServer");

            StartGameInfo startGameInfo = serverService.connect(config.getPlayerName());

            playerId = startGameInfo.getPlayerId();

            startPolling();

            return startGameInfo;

        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Failed to connect to RMI server", e);
        }
    }

    private void startPolling() {
        Thread pollingThread = new Thread(() -> {
            while (true) {
                try {
                    DynamicGameModel model = serverService.getModel();
                    if (model != null) {
                        listener.onModelChanged(model);
                    }
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                } catch (Exception e) {
                    break;
                }
            }
        });
        pollingThread.setDaemon(true);
        pollingThread.start();
    }

    @Override
    public void sendMoveLeft() {
        try {
            serverService.moveLeft(playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMoveRight() {
        try {
            serverService.moveRight(playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMoveUp() {
        try {
            serverService.moveUp(playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMoveDown() {
        try {
            serverService.moveDown(playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFire() {
        try {
            serverService.fire(playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
