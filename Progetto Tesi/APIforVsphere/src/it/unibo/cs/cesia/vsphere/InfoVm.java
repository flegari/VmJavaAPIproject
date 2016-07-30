package it.unibo.cs.cesia.vsphere;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.VirtualMachine;

/**
 * Classe contenente il metodo "getVMinfo" che stampa alcune info delle vm, come nome, networks, resourceConfig(da vedere bene cosa fa) ecc(ancora da provare),
 * il metodo "setVmName" che prende un nome e va a modificare il nome della macchina virtuale scelta,
 * il metodo "setVmCPU" che prende un intero e va a modificare il numero di CPU della macchina virtuale scelta,
 * il metodo "setVmRAM" che prende un Long e va a modificare i MB di RAM assegnati alla macchina virtuale scelta.
 * TODO: provare altri metodi "set".
 * Created by Francesca.
 */
public class InfoVm {

    private VirtualMachine vm;
    private GuestNicInfo[] nics = null;

    public InfoVm(Folder rootFolder, String name) throws Exception {

        vm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", name);
        //TODO: controllare vm.getdatastore
    }

    /**
     * Metodo per reperire info sulle vm, quali nome, network, resource ecc.
     * Nota: ResourceConfig dovrebbe restituire anche cpuAllocation e memoryAllocation, da provare!
     * TODO: altre info sulle vm, da provare per vedere quali servono. vm.getStorage().getDynamicProperty()
     * @throws Exception di vmware
     */
    public void getVMinfo() throws Exception{
        System.out.println("name: " + vm.getName());
        System.out.println("memory mb: "+ vm.getConfig().getHardware().getMemoryMB());
        System.out.println("Num cpu: "+ vm.getConfig().getHardware().getNumCPU());
        System.out.println("Max cpu usage: "+ vm.getRuntime().getMaxCpuUsage());
        System.out.println("Max Memory usage: "+ vm.getRuntime().getMaxMemoryUsage());
        System.out.println("Num core per socket: "+ vm.getConfig().getHardware().getNumCoresPerSocket());
        System.out.println("PowerState: "+ vm.getRuntime().getPowerState());
        System.out.println("IP: " + vm.getGuest().getIpAddress());
        System.out.println("guest full name: " + vm.getGuest().getGuestFullName());
        System.out.println("host name: " + vm.getGuest().getHostName());
        nics = vm.getGuest().getNet();
        //System.out.println(nics[0].getMacAddress());
       // VirtualEthernetCard  vec
        System.out.println("Net Lenght " + vm.getNetworks().length);
        //System.out.println("Net 0 " + vm.getConfig().getHardware().getDevice());
        VirtualDevice[] vdarr = vm.getConfig().getHardware().getDevice();
        for (VirtualDevice vd : vdarr) {
            if ( vd instanceof VirtualEthernetCard ) {
                VirtualEthernetCard ve = (VirtualEthernetCard) vd;
                System.out.println("MAC " + ve.getMacAddress());
            }
            if (vd instanceof VirtualDisk) {
                VirtualDisk vdk = (VirtualDisk) vd;
                System.out.println("Disk capacity " + vdk.getCapacityInKB());
                DynamicProperty[] dpa = vdk.getDynamicProperty();
                if (dpa != null) {
                    for (DynamicProperty dp : dpa) {
                        System.out.println(dp.getName() + " = " + dp.getVal());
                    }
                }
            }
        }


       // VirtualMachineConfigInfo vmci = new VirtualMachineConfigInfo();

        /*for (int i=0; i<nics.length; i++) {
            System.out.println("nel for");
            System.out.println("nics: " + nics[i]);
        }*/

       /* System.out.println("config status: " + vm.getConfigStatus());
        System.out.println("storage: " + vm.getStorage());
        System.out.println("cpu alloc limit: " + vm.getResourceConfig().getCpuAllocation().limit);
        System.out.println("memory alloc: " + vm.getResourceConfig().getMemoryAllocation());

        GuestDiskInfo disk_list1 = new GuestDiskInfo();
        GuestDiskInfo disk_list2 = new GuestDiskInfo();
        disk_list1 = vm.getGuest().disk[0];
        disk_list2 = vm.getGuest().disk[0];
        System.out.println("disk1: " + disk_list1.toString());
        System.out.println("disk2: " + disk_list2.toString());*/
    }

}
