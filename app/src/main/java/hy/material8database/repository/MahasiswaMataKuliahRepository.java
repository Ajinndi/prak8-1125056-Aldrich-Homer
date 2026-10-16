package hy.material8database.repository;

import hy.material8database.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MahasiswaMataKuliahRepository {

    private static final Connection conn = Database.connect();

    public static void ambilMataKuliah(String nim, String kodeMatakuliah) {
        if (conn == null) {
            return;
        }

        String sql = "INSERT INTO mahasiswa_matakuliah (nim, kode_matakuliah) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);
            stmt.setString(2, kodeMatakuliah);
            stmt.executeUpdate();
            System.out.println("Mata kuliah berhasil diambil.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}