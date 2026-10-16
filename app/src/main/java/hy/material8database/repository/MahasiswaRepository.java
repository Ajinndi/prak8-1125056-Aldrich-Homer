package hy.material8database.repository;

import hy.material8database.database.Database;
import hy.material8database.models.Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaRepository {

    private static final Connection conn = Database.connect();

    public static List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> result = new ArrayList<>();

        if (conn == null) {
            return result;
        }

        String sql = "SELECT nim, nama, kode_jurusan FROM mahasiswa ORDER BY nim";

        try (
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                result.add(new Mahasiswa(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("kode_jurusan")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static void addMahasiswa(Mahasiswa mhs) {
        if (conn == null) {
            return;
        }

        String sql = "INSERT INTO mahasiswa (nim, nama, kode_jurusan) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mhs.nim);
            stmt.setString(2, mhs.nama);
            stmt.setString(3, mhs.kodeJurusan);
            stmt.executeUpdate();
            System.out.println("Mahasiswa berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMahasiswa(Mahasiswa mhs) {
        if (conn == null) {
            return;
        }

        String sql = "UPDATE mahasiswa SET nama = ?, kode_jurusan = ? WHERE nim = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mhs.nama);
            stmt.setString(2, mhs.kodeJurusan);
            stmt.setString(3, mhs.nim);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Mahasiswa berhasil diubah.");
            } else {
                System.out.println("Mahasiswa tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMahasiswa(String nim) {
        if (conn == null) {
            return;
        }

        String sql = "DELETE FROM mahasiswa WHERE nim = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Mahasiswa berhasil dihapus.");
            } else {
                System.out.println("Mahasiswa tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}