package groupg;

/*
  Created by WilsonWong on 3/19/2017.
 */

import java.sql.*;

public class JavaDBExample
{
    public static void main(String[] args)
    {
        System.out.println("-------Embedded Java DB Connection Testing --------");
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Java DB Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the Java JDK is installed");
            System.out.println("Select the folder java/jdk1.8.xxx/db/lib where xxx is the version.");
            System.out.println("Click OK, compile the code and run it.");
            e.printStackTrace();
            return;
        }

        System.out.println("Java DB driver registered!");
        Connection connection = null;

        try
        {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            Statement stmt = connection.createStatement();

            //DROP TABLES
            stmt.execute("DROP TABLE ROOM");
            stmt.execute("DROP TABLE PERSONELLE");
            stmt.execute("DROP TABLE BUILDING");
            stmt.execute("DROP TABLE FLOOR");


            //CREATE TABLES
            stmt.execute("CREATE TABLE ROOM (ROOM_ID char(20) NOT NULL Primary Key, FLOOR_NUM int default 1, FLOOR_X_CORD int default 0, FLOOR_Y_CORD int default 0, ROOM_TYPE varchar(20), BUILDING_NUM int default 0)");
            stmt.execute("CREATE TABLE PERSONELLE (PERSONELLE_ID int NOT NULL Primary Key, DOCTOR_NAME varchar(20) default NULL, OFFICE_NUMBER char(20) references ROOM.ROOM_ID");
            stmt.execute("CREATE TABLE BUILDING (BUILDING_ID char(20) NOT NULL Primary Key, BUILDING_NAME varchar(20), FLOOR_COUNT int)");
            stmt.execute("CREATE TABLE FLOOR(FLOOR_NUMBER int NOT NULL Primary Key, BUILDING_ID char(20) references BUILDING.BUILDING_ID)");


            //INSERT INTO TABLES
            stmt.execute("INSERT INTO ROOM VALUES " +
                    "('A11', 0, 2, 2, 'WAITING_ROOM', 0), " +
                    "('A21', 0, 2, 3, 'OFFICE', 0), ");
            stmt.execute("INSERT INTO PERSONELLE VALUES " +
                    "(0123, 'Dr. Hunter Peterson', 'A21'), " +
                    "(0124, 'Nurse Bella', 'A21'), ");
            stmt.execute("INSERT INTO BUILDING VALUES " +
                    "('B0', 'Residential Services', 2), " +
                    "('B1', 'Morgan Hall', 4), ");
            stmt.execute("INSERT INTO FLOOR VALUES " +
                    "(1, 'Residential Services'), " +
                    "(1, 'Morgan Hall'), " +
                    "(2, 'Morgan Hall'), " +
                    "(3, 'Morgan Hall'), " +
                    "(4, 'Morgan Hall'), ");



            //Select everything from Personelle
            ResultSet res = stmt.executeQuery("SELECT * FROM PERSONELLE");
            ResultSetMetaData resultSetMetaData = res.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++){
                System.out.format("%20s", resultSetMetaData.getColumnName(i) +'|');
            }
            System.out.println(res);
            while (res.next()) {
                System.out.println(" ");
                for (int j = 1; j <= columnCount; j++) {
                    System.out.format("%20s", res.getString(j) + '|');
                }
            }

            // substitute your database name for myDB
            //connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }
        System.out.println("\nJava DB connection established!");
    }
}
