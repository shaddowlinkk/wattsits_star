import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class DataImporting {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException {
        DBC conn= new DBC();
        conn.tryConnect("gameAdmin","admin");
        Scanner scan = new Scanner(new File("101 Ships.csv"));
        System.out.println(scan.nextLine());
        while(scan.hasNext()){
            String[] line = scan.nextLine().split(",");
            System.out.println(line[0]+":"+line[4]+":"+line[5]);
            conn.addShipInio(line[0],line[3],Integer.parseInt(line[4]));
            System.out.println("Data has ben entered");
        }


    }
}
