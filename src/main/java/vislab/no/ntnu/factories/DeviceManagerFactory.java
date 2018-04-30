package vislab.no.ntnu.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import vislab.no.ntnu.DeviceManager;

public class DeviceManagerFactory {
    private final ServiceLoader<DeviceManager> loader;
    private static DeviceManagerFactory service;

    private DeviceManagerFactory(){
        loader = ServiceLoader.load(DeviceManager.class);
    }

    public static synchronized DeviceManagerFactory getInstance(){
        if(service == null){
            service = new DeviceManagerFactory();
        }
        return service;
    }

    public List<DeviceManager> getManagers(){
        loader.reload();
        List<DeviceManager> managers = new ArrayList<>();
        try{
            Iterator<DeviceManager> devices = loader.iterator();
            while(devices.hasNext()){
                DeviceManager tempManager = devices.next();
                managers.add(tempManager);
            }
        } catch (ServiceConfigurationError serviceError){
            System.out.println("ERROR");
            serviceError.printStackTrace();
        }
        return managers;
    }
}
