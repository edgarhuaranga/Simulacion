import java.util.Random;

/**
 * Created by edgar on 05/09/17.
 */
public class Scenario {
    public static void main(String[] args){
        MM1Queue mm1Queue = new MM1Queue();
        mm1Queue.run();

        ClosedNetworks closedNetworks = new ClosedNetworks();
        closedNetworks.run();
    }
}
