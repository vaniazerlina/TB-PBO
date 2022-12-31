import java.sql.*;

//pemakaian interface class
public interface CRUD {
    void view() throws SQLException;

    void insert() throws SQLException;

    void update() throws SQLException;

    void save() throws SQLException;

    void delete() throws SQLException;

}
