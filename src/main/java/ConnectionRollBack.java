import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class creates a connection that rolls the transaction back when closed.
 */
public class ConnectionRollBack {

    /**
     * Creates a connection that rolls the transaction back when closed.
     *
     * @param connection connection.
     * @return a connection that rolls the transaction back when closed.
     * @throws SQLException if problems with connection.
     */
    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollBack.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if (method.getName().equals("close")) {
                        connection.rollback();
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }
}