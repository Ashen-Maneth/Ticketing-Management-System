import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    private final String DB_URL = "jdbc:mysql://localhost:3306/configue";
    private final String USER = "root";
    private final String PASSWORD = ""; 



    public synchronized void setVariable(String variableName, int value) {
        String query = """
            INSERT INTO system_variables (variable_name, variable_value)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE variable_value = VALUES(variable_value);
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, variableName);
            pstmt.setInt(2, value);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    public synchronized int getVariable(String variableName) {
        String query = "SELECT variable_value FROM system_variables WHERE variable_name = ?";
        int value = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, variableName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    value = rs.getInt("variable_value");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


    
    public synchronized void reset() {
        String query = "UPDATE system_variables SET variable_value = 0";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
