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
import vislab.no.ntnu.providers.Projector;


public class ProjectorFactory {
    private final ServiceLoader<Projector> loader;
    private static ProjectorFactory service;

    private ProjectorFactory(){
        loader = ServiceLoader.load(Projector.class);
    }

    public static synchronized ProjectorFactory getInstance(){
        if(service == null){
            service = new ProjectorFactory();
        }
        return service;
    }

    public synchronized Projector getProjector(String make, String model){
        Projector projector = null;
        loader.reload();
        try{
            Iterator<Projector> projectors = loader.iterator();
            while(projector == null && projectors.hasNext()){
                Projector tempProjector = projectors.next();
                if(tempProjector.getMake().toLowerCase().equals(make.toLowerCase())
                        && tempProjector.getModel().toLowerCase().equals(model.toLowerCase())){
                    projector = tempProjector;
                }
            }
        } catch (ServiceConfigurationError serviceError){
            projector = null;
            System.out.println("ERROR");
            serviceError.printStackTrace();
        }
        return projector;
    }
    public List<Projector> getAllProjectors(){
        List<Projector> Projectors = new ArrayList<>();
        loader.reload();
        try{
            Iterator<Projector> ProjectorsIt = loader.iterator();
            while(ProjectorsIt.hasNext()){
                Projectors.add(ProjectorsIt.next());
            }
        } catch (ServiceConfigurationError serviceError){
        }
        return Projectors;
    }
}
