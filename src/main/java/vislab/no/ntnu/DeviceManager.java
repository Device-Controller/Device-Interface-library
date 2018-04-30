package vislab.no.ntnu;



import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vislab.no.ntnu.factories.DeviceFactory;
import vislab.no.ntnu.factories.ProjectorFactory;
import vislab.no.ntnu.providers.Device;
import vislab.no.ntnu.providers.Projector;

public abstract class DeviceManager {
    private static Map<Integer, Device> activeDevices;
    @Autowired
    private List<DeviceManager> controllers;

    private void checkActiveDevices() {
        if (activeDevices == null) {
            activeDevices = new HashMap<>();
        }
    }

    public Map<Integer, Device> getActiveDevices(){
        checkActiveDevices();
        return activeDevices;
    }

    public Device getDevice(int id){
        Device device = getActiveDevices().get(id);
        return device;
    }
    public Device createNewDevice(int id, String manufacturer, String model){
        DeviceFactory df = DeviceFactory.getInstance();
        Device device = df.getDevice(manufacturer, model);
        if(device != null){
            getActiveDevices().put(id,device);
        }
        return device;
    }
    
    public Projector createNewProjector(int id, String manufacturer, String model){
        ProjectorFactory df = ProjectorFactory.getInstance();
        Projector projector = df.getProjector(manufacturer, model);
        if(projector != null){
            getActiveDevices().put(id,projector);
        }
        return projector;
    }
    public String locateDevicePage(int id){
        String deviceControllerPageLink = "";
        for(DeviceManager mc : controllers){
            if(deviceControllerPageLink.isEmpty()){
                deviceControllerPageLink = mc.getDevicePage(id);
                if(deviceControllerPageLink == null){
                    deviceControllerPageLink = "";
                }
            }
        }
        return deviceControllerPageLink;
    }

    /**
     *  Returns the implementing classes path to their html page. Default is empty string, causing a page reload.
     * @param id The id of the device in question.
     * @return the string containg the path to the devices html page
     */
    public String getDevicePage(int id){
        return "";
    }
}
