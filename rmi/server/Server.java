package rmi.server;

import java.rmi.*;
import java.rmi.registry.*;
import rmi.api.*;
/**
 * This is the main server program, this will open and register the server locations in the rmi registry,
 * and create a new ApiImpl that will try to connect to the database.
 * @author Aaron, using http://littletutorials.com/2008/07/14/the-10-minutes-getting-started-with-rmi-tutorial/
 */
public class Server {
    private static final int PORT = 1099;
    private static Registry registry;
    
    /**
     * To allow the server class to be run directly, parameters can be null.
     * @param 	args		Not necessary/used
     * @throws 	Exception	
     */
    public static void main(String[] args) throws Exception {
        new Server();
    }
    
    /**
     * Will attempt to star the rmi registry at the port specified within the Server class and will register an
     * instance of the ApiImpl in the rmi registry so that it's methods are called when the relevant port is accessed.
     * @throws 	AlreadyBoundException
     * @throws 	RemoteException
     */
    public Server() throws AlreadyBoundException, RemoteException{
        startRegistry();
        registerObject(Api.class.getSimpleName(), new ApiImpl());
    }
    
    
    /**
     * Will attempt to start the rmi registry at the port specified within the Server class.
     * @throws RemoteException
     */
    public static void startRegistry() throws RemoteException {
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }

    /**
     * Binds an an object that implements remote to the registry with the given access-string
     * @param 	String name - the name that will be uploaded to the rmi registry when trying to access this object
     * @param	Remote remoteObj - a class that implements the remote interface and hence able to be bound to rmi.
     * @throws 	AlreadyBoundException
     * @throws 	RemoteException
     */
    public static void registerObject(String name, Remote remoteObj)
        throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> " +
            remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }
}