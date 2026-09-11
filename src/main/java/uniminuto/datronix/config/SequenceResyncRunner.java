package uniminuto.datronix.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Al arrancar la aplicación, resincroniza las secuencias de PostgreSQL
 * con el valor máximo actual de cada tabla. Evita errores de "duplicate key"
 * cuando se insertan datos manualmente (migraciones, pruebas, importaciones).
 */
@Component
public class SequenceResyncRunner implements CommandLineRunner {

    private final DataSource dataSource;

    // Tablas con ID autoincremental y el nombre de su columna ID
    private static final List<String[]> TABLAS = List.of(
        new String[]{"producto", "id_producto"},
        new String[]{"compra", "id_compra"},
        new String[]{"detalle_compra", "id_detalle_compra"},
        new String[]{"venta", "id_venta"},
        new String[]{"detalle_venta", "id_detalle_venta"},
        new String[]{"remision", "id_remision"},
        new String[]{"devolucion", "id_devolucion"},
        new String[]{"nomina", "id_nomina"},
        new String[]{"soporte", "id_soporte"}
    );

    public SequenceResyncRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) {
        try (Connection conn = dataSource.getConnection()) {
            for (String[] tabla : TABLAS) {
                resincronizarSecuencia(conn, tabla[0], tabla[1]);
            }
            System.out.println("✅ Secuencias resincronizadas correctamente.");
        } catch (Exception e) {
            System.err.println("❌ Error al resincronizar secuencias: " + e.getMessage());
        }
    }

    private void resincronizarSecuencia(Connection conn, String tabla, String columna) {
        String sqlMax = "SELECT COALESCE(MAX(" + columna + "), 1) FROM " + tabla;
        String sqlSetval = "SELECT setval(pg_get_serial_sequence(?, ?), ?, true)";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sqlMax);
             PreparedStatement ps = conn.prepareStatement(sqlSetval)) {

            long maxId = 1;
            if (rs.next()) maxId = rs.getLong(1);

            ps.setString(1, tabla);
            ps.setString(2, columna);
            ps.setLong(3, maxId);
            ps.execute();

        } catch (Exception e) {
            System.err.println("⚠️ No se pudo resincronizar " + tabla + ": " + e.getMessage());
        }
    }
}