package application;

import bd.DB;
import bd.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "delete from department "
                    + "where "
                    + "Id = ?");

//            st.setInt(1, 5);//deve permitir excluir
            st.setInt(1, 2);//não deve permitir excluir e deve lançar uma DbIntegrityException

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException ex) {
            throw new DbIntegrityException(ex.getMessage());

        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
