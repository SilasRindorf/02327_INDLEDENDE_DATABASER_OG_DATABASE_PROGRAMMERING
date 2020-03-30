import java.sql.*;        //Package for accessing and processing sql database data 
import java.util.Scanner; //Scanner class found in java.util package for console input

public class ManipulateUniversity {
    public static void main(String[] args) {
        //Edit the variables below to match a specific setup
        Scanner scanner = new Scanner(System.in);
        String host = "localhost";
        String port = "3306";
		String database = "uni_given";
        String username = "SilasRindorf";
        String password = "trappeSumpTun";

        //Edit only if needed
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://" 
			+ host + ":" + port + "/" + database + "?characterEncoding=latin1&serverTimezone=UTC";

		//"try" defines a block to be tested for errors while it is being executed
        try {
            Class.forName(driver);
            String sqlManipulation;

            System.out.println("Type sql manipulation: ");
            //A manipulation statement with INSERT, UPDATE, DELETE, CREATE or DROP
            sqlManipulation = scanner.nextLine();

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();
        } 
		//"catch" defines a block to be executed, if an error occurs in the "try" block
		catch (Exception e) {
            e.printStackTrace();
        }
    }
}