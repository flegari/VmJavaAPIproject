package it.unibo.cs.cesia.vsphere;

import com.vmware.vim25.GuestDiskInfo;
import com.vmware.vim25.mo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 * Classe contenente il metodo "getVMList" che restituisce un array, sotto forma di lista, delle macchine virtuali contenute nell'inventory.
 * Created by Francesca.
 */
public class VmList {

    private static  ManagedEntity[] mngEarray;
    private static  ManagedEntity mngEarrayDc;
    private static ArrayList<VirtualMachine> vm_list = new ArrayList<VirtualMachine>();
    private static int i=0;

    public VmList(Folder rootFolder) throws Exception {

        mngEarray = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
        //mngEarrayDc = new InventoryNavigator(rootFolder).searchManagedEntity("Datacenter", "name");
        System.out.println("Lunghezza: " + mngEarray.length);
      //  System.out.println("Nome DC: " + mngEarrayDc.getName());
        //mngEarrayDc.get
    }

    /**
     * Metodo per restituire una lista di vm dall'inventory, inoltre stampa il num di vm presenti in vSphere.
     * @return vmArray (un'array, convertito a lista, di vm)
     */

    public static ArrayList<VirtualMachine> getVmList() throws RemoteException, InterruptedException {

        while(i<mngEarray.length){
            vm_list.add((VirtualMachine)mngEarray[i]);
            i++;
        }
        for (VirtualMachine vm : vm_list) {
            System.out.println("Name: " + vm.getName());
        }
        return vm_list;
    }

}
