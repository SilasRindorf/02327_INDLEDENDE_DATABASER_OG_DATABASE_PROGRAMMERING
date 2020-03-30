import java.sql.*;

public class TableSchemasUniversity {
    public static void main(String[] args) {
      	//Edit the variables below to match a specific setup
        String host = "localhost";
        String port = "3306";
		String database = "university";
        String username = "Schmidt";
        String password = "7984";

        //Edit only if needed
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" 
			+ host + ":" + port + "/" + database + "?characterEncoding=latin1"; 

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet resultSetTables = 
				databaseMetaData.getTables(null, null, null, types);
			
			// For each table
            while (resultSetTables.next()) {
                String tableName = resultSetTables.getString(3);
                System.out.println("\n=== TABLE: " + tableName);
                ResultSet resultSetColumns = 
					databaseMetaData.getColumns(null, null, tableName, null);
                ResultSet resultSetPrimaryKeys = 
					databaseMetaData.getPrimaryKeys(null, null, tableName);
				// For each attribute in the table
                while (resultSetColumns.next()) {
                    String columnName = resultSetColumns.getString("COLUMN_NAME");
                    String columnType = resultSetColumns.getString("TYPE_NAME");
                    int columnSize = resultSetColumns.getInt("COLUMN_SIZE");
                    System.out.println("\t" + columnName + " - " + columnType + 
						"(" + columnSize + ")");
                }
				// For each primary key in the table
                while (resultSetPrimaryKeys.next()) {
                    String primaryKeyColumn = 
					resultSetPrimaryKeys.getString("COLUMN_NAME");
                    System.out.println("\tPrimary Key Column: " + primaryKeyColumn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}