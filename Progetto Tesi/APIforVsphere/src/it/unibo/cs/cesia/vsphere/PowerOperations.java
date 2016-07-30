package it.unibo.cs.cesia.vsphere;

import com.vmware.vim25.mo.*;

import java.net.URL;

/**
 * Classe contenente il metodo "getPowerOp" che, in base al quinto argomento passato da linea di comando (cioè l'operazione da eseguire), invoca sulla vm i metodi reboot, poweron, poweroff, reset, standby, suspend, shutdown, clone.
 * TODO: aggiungere, al metodo clone, le operazioni per il cambio nome, cambio RAM, cambio IP ecc..
 * Created by Francesca.
 */
public class PowerOperations {

    private static String vmname;
    private static String op;
    private static Folder rootfolder;
    private static  VirtualMachine vm;

    public PowerOperations(Folder rootFolder, String name, String operation) throws Exception {

        op=operation;
        vmname=name;
        rootfolder=rootFolder;
        vm = (VirtualMachine) new InventoryNavigator(
                rootFolder).searchManagedEntity("VirtualMachine", vmname);

    }

    /**
     * Metodo per eseguire un'operazione specifica sulle vm.
     * @throws Exception di vmware
     */
    public void getPowerOp() throws Exception
    {

        if(op.equalsIgnoreCase("reboot"))
        {
            vm.rebootGuest();
            System.out.println(vmname + " rebooted");
        }
        else if(op.equalsIgnoreCase("poweron"))
        {
            //TODO: controllo ram
            Task task = vm.powerOnVM_Task(null);
            if(task.waitForTask()==Task.SUCCESS)
            {
                System.out.println(vmname + " powered on");
            }
        }
        else if(op.equalsIgnoreCase("poweroff"))
        {
            Task task = vm.powerOffVM_Task();
            if(task.waitForTask()==Task.SUCCESS)
            {
                System.out.println(vmname + " powered off");
            }
        }
        else if(op.equalsIgnoreCase("reset"))
        {
            Task task = vm.resetVM_Task();
            if(task.waitForTask()==Task.SUCCESS)
            {
                System.out.println(vmname + " reset");
            }
        }
        else if(op.equalsIgnoreCase("standby"))
        {
            vm.standbyGuest();
            System.out.println(vmname + " stoodby");
        }
        else if(op.equalsIgnoreCase("suspend"))
        {
            Task task = vm.suspendVM_Task();
            if(task.waitForTask()==Task.SUCCESS)
            {
                System.out.println(vmname + " suspended");
            }
        }
        else if(op.equalsIgnoreCase("shutdown"))
        {
            Task task = vm.suspendVM_Task();
            if(task.waitForTask()==Task.SUCCESS)
            {
                System.out.println(vmname + " suspended");
            }
        }
        else if(op.equalsIgnoreCase("clone"))
        {
            //TODO: controllo spazio disco
            Task task = vm.cloneVM_Task(rootfolder, vmname, null);
            if(task.waitForTask()==Task.SUCCESS)
            {
                System.out.println(vmname + " cloned");
            }
        }
        else
        {
            System.out.println("Invalid operation");
        }
    }
}
