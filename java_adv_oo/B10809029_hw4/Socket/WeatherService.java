import java.rmi.*;
import java.util.*;

public interface WeatherService extends Remote {   
   String getWeatherInformation(int id) throws RemoteException;      
}
