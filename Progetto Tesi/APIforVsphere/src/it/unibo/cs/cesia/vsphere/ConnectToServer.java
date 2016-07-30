package it.unibo.cs.cesia.vsphere;

import com.vmware.vim25.DatastoreSummary;
import com.vmware.vim25.mo.*;

import javax.management.Query;
import java.net.URL;

/**
 * Classe contenente il metodo "connect" per instaurare la connessione col server
 * e il metodo "getRootFolder" che restituisce la rootfolder.
 * Created by Francesca.
 */
public class ConnectToServer {

    private Folder rootFolder;
    private ServiceInstance si;
    private Datacenter dc;
    private ServerConnection sc;

    public ConnectToServer() {

    }


    /**
     * Il metodo connect viene usato per instaurare la connessione con vsphere server
     *
     * @param ip   ip di vcenter server
     * @param user nome utente
     * @param pass password
     * @throws Exception di vmware
     */
    public void connect(String ip, String user, String pass) throws Exception {

        si = new ServiceInstance(
                new URL(ip), user, pass, true);
        rootFolder = si.getRootFolder();
        System.out.println("host prof manag" + si.getHostProfileManager());
        //System.out.println("ip pool" + si.getIpPoolManager().getServerConnection().getUrl());

        for (ManagedEntity me : rootFolder.getChildEntity()) {
            if (me instanceof Datacenter) {
                System.out.println("DATACENTER FOUND");
                Datacenter dc = (Datacenter) me;
                Datastore[] datastores = dc.getDatastores();
                if (datastores != null) {
                    for (Datastore datastore : datastores) {
                        DatastoreSummary s = datastore.getSummary();
                        System.out.println(s.getName() + ": " + s.getFreeSpace() + "/" + s.getCapacity() + " Byte");
                    }
                }
            }
        }
    }


    /**
     * Metodo per avere la rootFolder (utile poi per la VmList ecc)
     * @return rootFolder
     */
    public Folder getRootFolder(){
        return rootFolder;
    }
    public void logout(){
        si.getServerConnection().logout();
        System.out.println("Logout eseguito!");
    }

}


