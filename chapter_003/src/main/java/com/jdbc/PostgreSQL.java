/**

 // PostgreSQL

 // Importing JDBC

 import java.sql.*;


 // 1 - Connecting to the Database -----------------------------------------------------------------

 String url = "jdbc:postgresql://localhost/test";
 Properties props = new Properties();
 props.setProperty("user","fred");
 props.setProperty("password","secret");
 props.setProperty("ssl","true");
 Connection conn = DriverManager.getConnection(url, props);


 // 2

 String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
 Connection conn = DriverManager.getConnection(url);


 // 2.1

 String url = "jdbc:postgresql://localhost/test";
 String usr = "usrname";
 String password = "password";
 Connection conn = DriverManager.getConnection(url, usr, password);



 // 3 - Processing a Simple Query in JDBC ----------------------------------------------------------

 Statement st = conn.createStatement();
 ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
 while (rs.next()) {
 System.out.print("Column 1 returned ");
 System.out.println(rs.getString(1));
 }
 rs.close();
 st.close();


 // 4

 int foovalue = 500;
 PreparedStatement st = conn.prepareStatement("SELECT * FROM mytable WHERE columnfoo = ?");
 st.setInt(1, foovalue);
 ResultSet rs = st.executeQuery();
 while (rs.next()) {
 System.out.print("Column 1 returned ");
 System.out.println(rs.getString(1));
 }
 rs.close();
 st.close();


 // 5 - Getting results based on a cursor ----------------------------------------------------------

 // make sure autocommit is off
 conn.setAutoCommit(false);
 Statement st = conn.createStatement();

 // Turn use of the cursor on.
 st.setFetchSize(50);
 ResultSet rs = st.executeQuery("SELECT * FROM mytable");
 while (rs.next()) {
 System.out.print("a row was returned.");
 }
 rs.close();

 // Turn the cursor off.
 st.setFetchSize(0);
 rs = st.executeQuery("SELECT * FROM mytable");
 while (rs.next()) {
 System.out.print("many rows were returned.");
 }
 rs.close();

 // Close the statement.
 st.close();



 // 6 - Performing Updates -------------------------------------------------------------------------

 int foovalue = 500;
 PreparedStatement st = conn.prepareStatement("DELETE FROM mytable WHERE columnfoo = ?");
 st.setInt(1, foovalue);
 int rowsDeleted = st.executeUpdate();
 System.out.println(rowsDeleted + " rows deleted");
 st.close();


 // getGeneratedKeys()
 PreparedStatement st = conn.prepareStatement("INSERT INTO ...", Statement.RETURN_GENERATED_KEYS);
 st.executeUpdate();
 ResultSet genKeys = st.getGeneratedKeys();
 if (genKeys.next()) {
 System.out.print(genKeys.getInt(1));
 }

*/
