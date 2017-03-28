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

               /* String drop1 = "DROP TABLE DOCTOR";
                String drop2 = "DROP TABLE CONNECTION";
                String drop3 = "DROP TABLE FLOOR";
                String drop4 = "DROP TABLE BUILDING";
                String drop5 = "DROP TABLE LOCATION";
                */
                //stmt.execute(str0);

                /*String create0 = "CREATE TABLE ROOM (roomID CHAR(20) NOT NULL Primary Key)";
                //stmt.execute(str1);

                String str1 = "INSERT INTO ROOM VALUES (11, 'Jim Bye', 'Buffalo', 34)";
                stmt.execute(str1);
                stmt.execute(str1);
                stmt.execute(str1);

*/
                connection.createStatement().execute("DROP TABLE ROOM");
                connection.createStatement().execute("DROP TABLE CUSTOMER");
                connection.createStatement().execute("DROP TABLE DOCTOR");
                //connection.createStatement().execute("DROP TABLE BUILDING");
                connection.createStatement().execute("CREATE TABLE ROOM (ROOM_ID int NOT NULL Primary Key, OCCUPIED varchar(20), PATIENT_FIRST_NAME varchar(20) default NULL, FLOOR_NUM INT default 0)");
                connection.createStatement().execute("INSERT INTO ROOM VALUES " +
                        "(111,'Y','Jerry', 1), " +
                        "(112,'Y', 'Harry',1), " +
                        "(113,'Y', 'Larry', 1), " +
                        "(114,'Y', 'Mary', 1), " +
                        "(115,'Y', 'Kary', 1), " +
                        "(116,'Y', 'Barry', 1), " +
                        "(117,'Y', 'Sam', 1), " +
                        "(118,'Y', 'Alazar', 1), " +
                        "(119,'Y', 'Will', 1), " +
                        "(120,'Y', 'Saul', 1), " +
                        "(121,'Y', 'Brain', 1), " +
                        "(123,'Y', 'Ryan', 1), " +
                        "(124,'Y', 'Hunter', 1), " +
                        "(125,'Y', 'Eric', 1), " +
                        "(126,'Y', 'Dylan', 1), " +
                        "(127,'Y', 'Andrew', 1), " +
                        "(128,'Y', 'Wilson', 1), " +
                        "(129,'Y', 'Wong', 1), " +
                        "(130,'Y', 'Paul', 1), " +
                        "(131,'Y', 'Raymond', 1), " +
                        "(132,'Y', 'Chris', 1)");
                connection.createStatement().execute("CREATE TABLE DOCTOR (DOCTOR_ID int NOT NULL Primary Key, BUSY varchar(20), DOCTOR_FIRST_NAME varchar(20) default NULL, OFFICE_FLOOR INT)");
                connection.createStatement().execute("INSERT INTO DOCTOR VALUES " +
                        "(111,'Y','Jerry', 1), " +
                        "(112,'N', 'Harry',1), " +
                        "(113,'N', 'Larry', 1), " +
                        "(114,'Y', 'Mary', 1), " +
                        "(115,'Y', 'Kary', 1), " +
                        "(116,'N', 'Barry', 1), " +
                        "(117,'Y', 'Sam', 1), " +
                        "(118,'N', 'Alazar', 1), " +
                        "(119,'Y', 'Will', 1), " +
                        "(120,'N', 'Saul', 1), " +
                        "(121,'N', 'Brain', 1), " +
                        "(123,'N', 'Ryan', 1), " +
                        "(124,'Y', 'Hunter', 1), " +
                        "(125,'Y', 'Eric', 1), " +
                        "(126,'Y', 'Dylan', 1), " +
                        "(127,'N', 'Andrew', 1), " +
                        "(128,'N', 'Wilson', 1), " +
                        "(129,'Y', 'Wong', 1), " +
                        "(130,'Y', 'Paul', 1), " +
                        "(131,'N', 'Raymond', 1), " +
                        "(132,'Y', 'Chris', 1)");
                //connection.createStatement().execute("CREATE TABLE BUILDING (BUILDING_NAME varchar(20) NOT NULL Primary Key, FLOOR_LIST INT ARRAY[7])");
            // connection.createStatement().execute("INSERT INTO ROOM VALUES (11, 'Jim Bye', 'Buffalo', 34)");
                connection.createStatement().execute("CREATE TABLE CUSTOMER (SALARY int NOT NULL, NAME varchar(20), LOCATION varchar(20), AGE varchar(20))");
                connection.createStatement().execute("INSERT INTO CUSTOMER VALUES (111,'yoo', 'Room 30', '20')");



            //String str3 = "SELECT * FROM CUSTOMER";
                Statement statement = connection.createStatement();
                String SQLStatement = "SELECT * FROM DOCTOR";
                ResultSet res = stmt.executeQuery(SQLStatement);
                ResultSetMetaData resultSetMetaData = res.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++){
                System.out.format("%20s", resultSetMetaData.getColumnName(i) +'|');
                 }


                System.out.println(res);
                while (res.next()) {
                    System.out.println(" ");
                    for (int j = 1; j <= columnCount; j++){
                       System.out.format("%20s", res.getString(j) +'|');
                    }
                    /*System.out.println(
                            "  "+res.getInt("SALARY")
                                    + ", "+res.getString("NAME")
                                    + ", "+res.getString("LOCATION")
                                    + ", "+res.getInt("AGE"));
                                    */
                }

            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");
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
