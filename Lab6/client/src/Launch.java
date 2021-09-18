import utils.UserHandler;

import java.io.BufferedInputStream;

public class Launch {
    public static void main(String[] args){
       Client client = new Client( "localhost",28910,new UserHandler( new BufferedInputStream( System.in ) ) );
        client.run();

    }
}
