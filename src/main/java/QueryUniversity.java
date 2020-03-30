import java.sql.*;
import java.util.Scanner;

public class QueryUniversity {
    private static String sqlQuery;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Edit the variables below to match a specific setup
        String host = "localhost";
        String port = "3306";
        String database = "uni_given";
        String username = "SilasRindorf";
        String password = "trappeSumpTun";

        //Edit only if needed
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://"
                + host + ":" + port + "/" + database + "?characterEncoding=latin1&serverTimezone=UTC";
        do {
            try {
                Class.forName(driver);
                System.out.println("Type sql query: ");
                 sqlQuery = scan.nextLine();
                //A query statement like "SELECT * FROM instructor;" or "SHOW TABLES;"

                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                int[] columnWidths = new int[columnCount + 1]; //columnWidths[0] to be ignored
                int valueLength;

                //Find maximum width for each column and store in columnWidths[]
                for (int i = 1; i <= columnCount; i++) {
                    columnWidths[i] = resultSetMetaData.getColumnName(i).length();
                }
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        valueLength = resultSet.getString(i).length();
                        if (valueLength > columnWidths[i]) {
                            columnWidths[i] = valueLength;
                        }
                    }
                }

                //Print all attribute names
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rightPad(resultSetMetaData.getColumnName(i), columnWidths[i]));
                }
                System.out.println();

                //Print all table rows
                resultSet.beforeFirst(); //Set pointer for resultSet.next()
                while (resultSet.next()) {
                    //Print all values in a row
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rightPad(resultSet.getString(i), columnWidths[i]));
                    }
                    System.out.println();
                }
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!sqlQuery.equalsIgnoreCase("Q"));
    }


	//rightPad method pads short columnnames and short columnvalues to same width
    public static String rightPad(String stringToPad, int width){
        StringBuilder stringBuilder = new StringBuilder(stringToPad);
        while(stringBuilder.length() <= width){
            stringBuilder.append(" ");
        }
        stringToPad = stringBuilder.toString();
        return stringToPad;
    }
} 