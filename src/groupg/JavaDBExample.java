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

                String drop0 = "DROP TABLE ROOM";
               /* String drop1 = "DROP TABLE DOCTOR";
                String drop2 = "DROP TABLE CONNECTION";
                String drop3 = "DROP TABLE FLOOR";
                String drop4 = "DROP TABLE BUILDING";
                String drop5 = "DROP TABLE LOCATION";
                //stmt.execute(str0);
*/
                /*String create0 = "CREATE TABLE ROOM (roomID CHAR(20) NOT NULL Primary Key)";
                //stmt.execute(str1);

                String str1 = "INSERT INTO ROOM VALUES (11, 'Jim Bye', 'Buffalo', 34)";
                stmt.execute(str1);
                stmt.execute(str1);
                stmt.execute(str1);

*/

                connection.createStatement().execute("CREATE TABLE ROOM (roomID varchar(20) NOT NULL Primary Key)");
               // connection.createStatement().execute("INSERT INTO ROOM VALUES (11, 'Jim Bye', 'Buffalo', 34)");
                connection.createStatement().execute("INSERT INTO ROOM VALUES ('Jim Bye')");
                //connection.createStatement().execute("DROP TABLE ROOM");
                connection.createStatement().execute("CREATE TABLE CUSTOMER (SALARY varchar(20), NAME varchar(20), LOCATION varchar(20), AGE varchar(20))");
                connection.createStatement().execute("INSERT INTO CUSTOMER VALUES ('111','yoo', 'Room 30', '20')");
               // connection.createStatement().execute("DROP TABLE CUSTOMER");


            //String str3 = "SELECT * FROM CUSTOMER";
                Statement statement = connection.createStatement();
                String SQLStatement = "SELECT * FROM CUSTOMER";
                ResultSet res = stmt.executeQuery(SQLStatement);
                ResultSetMetaData resultSetMetaData = res.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++){
                System.out.format("%20s", resultSetMetaData.getColumnName(i) +'|');
                 }


                System.out.println(res);
                while (res.next()) {
                    System.out.println("");
                    for (int j = 1; j <= columnCount; j++){
                       System.out.format("%20s", res.getString(j) +'|');
                    }
                    System.out.println(
                            "  "+res.getInt("SALARY")
                                    + ", "+res.getString("NAME")
                                    + ", "+res.getString("LOCATION")
                                    + ", "+res.getInt("AGE"));
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
        System.out.println("Java DB connection established!");
    }
}
