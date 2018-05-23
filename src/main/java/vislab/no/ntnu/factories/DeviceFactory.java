package vislab.no.ntnu.factories;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import vislab.no.ntnu.providers.Device;


public class DeviceFactory {
    private final ServiceLoader<Device> loader;
    private static DeviceFactory service;

    private DeviceFactory(){
        loader = ServiceLoader.load(Device.class);
    }

    public static synchronized DeviceFactory getInstance(){
        if(service == null){
            service = new DeviceFactory();
        }
        return service;
    }

    public Device getDevice(String make, String model){
        Device device = null;
        loader.reload();
        try{
            Iterator<Device> devices = loader.iterator();
            while(device == null && devices.hasNext()){
                Device tempDevice = devices.next();
                if(tempDevice.getMake().toLowerCase().equals(make.toLowerCase())
                        && tempDevice.getModel().toLowerCase().equals(model.toLowerCase())){
                    device = tempDevice;
                }
            }
        } catch (ServiceConfigurationError serviceError){
            device = null;
            System.out.println("ERROR");
            serviceError.printStackTrace();
        }
        return device;
    }
    public List<Device> getAllDevices(){
        List<Device> devices = new ArrayList<>();
        loader.reload();
        try{
            Iterator<Device> devicesIt = loader.iterator();
            while(devicesIt.hasNext()){
                devices.add(devicesIt.next());
                }
        } catch (ServiceConfigurationError serviceError){
        }
        return devices;
    }
}
