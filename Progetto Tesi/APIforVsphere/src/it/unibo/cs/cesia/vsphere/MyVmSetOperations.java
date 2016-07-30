package it.unibo.cs.cesia.vsphere;

import com.vmware.vim25.VirtualMachineConfigSpec;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.VirtualMachine;

/**
 * Created by Francesca on 20/07/2016.
 */
public class MyVmSetOperations {
    private VirtualMachine vm;

    public MyVmSetOperations(Folder rootFolder, String name) throws Exception {

        vm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", name);

    }
    /**
     * Metodo per cambiare nome ad una vm
     * Nota: non so se usare vm.reconfigVM_Task oppure vm.customizeVM_Task, da provare!
     * @param name nome della vm
     * @throws Exception di vmware
     */
    public void setVmName(String name) throws Exception{
        VirtualMachineConfigSpec spec = new VirtualMachineConfigSpec();
        spec.setName(name);
        vm.reconfigVM_Task(spec);
        System.out.println("Nuovo nome assegnato");
        System.out.println("Nuovo nome vm: " + vm.getName());
    }

    /**
     * Metodo per cambiare num di CPU di una vm
     * Nota: non so se usare vm.reconfigVM_Task oppure vm.customizeVM_Task, da provare!
     * @param num numero di CPU
     * @throws Exception di vmware
     */
    public void setVmCPU(Integer num) throws Exception{
        VirtualMachineConfigSpec spec = new VirtualMachineConfigSpec();
        spec.setNumCPUs(num);
        vm.reconfigVM_Task(spec);
        System.out.println("Nuova cpu vm: " + vm.getConfig().getHardware().getNumCPU());
    }

    /**
     * Metodo per cambiare la quantità (in MB) di RAM di una vm
     * Nota: non so se usare vm.reconfigVM_Task oppure vm.customizeVM_Task, da provare!
     * @param ram quantità di RAM in MB
     * @throws Exception di vmware
     */
    public void setVmRAM(Long ram) throws Exception{
        VirtualMachineConfigSpec spec = new VirtualMachineConfigSpec();
        spec.setMemoryMB(ram);
        vm.reconfigVM_Task(spec);
        System.out.println("Nuova ram vm: " + vm.getConfig().getHardware().getMemoryMB());
    }

    public void setIpAddress(String ip) throws Exception{
        vm.getGuest().setIpAddress(ip);
        System.out.println("Nuovo ip vm: " + vm.getGuest().getIpAddress());
    }
}
