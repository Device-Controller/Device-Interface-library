package vislab.no.ntnu.providers;

public interface Device {

    int powerOn();

    int powerOff();

    String getMake();

    String getModel();

    String getDeviceName();

    String getHostAddress();

    int getPortNumber();

    boolean setIpAddress(String ip);

    void setPort(int port);

    int getPowerState();

    boolean initialize();
}
