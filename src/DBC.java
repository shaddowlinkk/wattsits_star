import java.sql.*;

public class DBC {
    private  boolean isConnected;
    private Connection con=null;
    private String IDList="";


    public DBC(){
    }
    public void tryConnect(String Username, String Password) throws ClassNotFoundException {
        isConnected = true;
        Class.forName("com.mysql.jdbc.Driver");
        try {
   ;
            con = DriverManager.getConnection("jdbc:mysql://localhost/star_manager",Username,Password);
        }catch (SQLException e){
            isConnected=false;
        }

    }
    public void addShip(String handle,String ship) throws SQLException {
        PreparedStatement ps=con.prepareStatement("INSERT INTO `ships` (`ShipID`,`invID`,`ship`,`weapInvID`) VALUES (?,?,?,?)");
        ps.setInt(1,getOpenID());
        ps.setInt(2,getShipInvID(handle));
        ps.setString(3,ship);
        ps.setInt(4,getShipInvID(handle));
        ps.executeUpdate();
    }

    public void addShipInio(String Ship,String Focus,int max) throws SQLException {
        PreparedStatement ps=con.prepareStatement("INSERT INTO `shipinfo` (`Ship`,`manu`,`focus`,`ideal_crew`,`max_crew`) VALUES (?,?,?,?,?)");
        ps.setString(1,Ship);
        ps.setString(2,"");
        ps.setString(3,Focus);
        ps.setInt(4,-1);
        ps.setInt(5,max);
        ps.executeUpdate();
    }


    public int getShipInvID(String handle) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("SELECT shipInvID FROM profile WHERE handle='"+handle+"'");
        rt.next();
        return rt.getInt("shipInvID");

    }
    public int getUserID(String handle) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("SELECT userID FROM profile WHERE handle='"+handle+"'");
        rt.next();
        return rt.getInt("userID");

    }
    public String getName(String handle) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("SELECT name FROM profile WHERE handle='"+handle+"'");
        rt.next();
        return rt.getString("name");

    }
    public String[] getShipsName() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("SELECT Ship FROM shipinfo" );
        String names="";
        while(rt.next()){
            names+=rt.getString("Ship")+",";
        }
        return names.split(",");

    }
    public void removeShip(int ShipID) throws SQLException {
        PreparedStatement ps=con.prepareStatement("DELETE FROM ships WHERE shipID= ?");
        ps.setInt(1,ShipID);
        ps.execute();
        /*Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("DELETE FROM ships WHERE shipID="+ShipID);*/
    }
    public String[] getIDList(){
        return IDList.split(",");
    }
    public String[] getShipsName(int invID) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("SELECT * FROM ships WHERE invID="+invID );
        String names="";
        while(rt.next()){
            names+=rt.getString("ship")+",";
            IDList+=rt.getInt("shipID")+",";
        }


        return names.split(",");

    }
    private int getOpenID()throws SQLException{
        int id=0;
        Statement stmt = con.createStatement();
        ResultSet rt = stmt.executeQuery("SELECT shipID FROM ships ORDER BY shipID");
        while(rt.next()){
            id=rt.getInt("shipID")+1;
        }
        return id;
    }

    public boolean getIsConnected(){
        return isConnected;
    }
    public  boolean test(){
        return false;
    }
}
