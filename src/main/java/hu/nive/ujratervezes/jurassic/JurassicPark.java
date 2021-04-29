package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private Connection conn;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        try {
            this.conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> checkOverpopulation(){
        List<String> returnStringList = new ArrayList<>();

        String query = "SELECT breed FROM dinosaur WHERE actual > expected ORDER BY breed ASC";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                returnStringList.add(rs.getString("breed"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return returnStringList;
    }
}
