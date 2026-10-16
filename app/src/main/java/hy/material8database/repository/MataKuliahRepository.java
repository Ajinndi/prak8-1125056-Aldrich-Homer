package hy.material8database.repository;

import hy.material8database.database.Database;
import hy.material8database.models.MataKuliah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MataKuliahRepository {

    private static final Connection conn = Database.connect();

    public static List<MataKuliah> getAllMataKuliah() {
        List<MataKuliah> result = new ArrayList<>();

        if (conn == null) {
            return result;
        }

        String sql = "SELECT kode_matakuliah, nama, sks, kode_jurusan FROM matakuliah ORDER BY kode_matakuliah";

        try (
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                result.add(new MataKuliah(
                    rs.getString("kode_matakuliah"),
                    rs.getString("nama"),
                    rs.getInt("sks"),
                    rs.getString("kode_jurusan")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static void addMataKuliah(MataKuliah mk) {
        if (conn == null) {
            return;
        }

        String sql = "INSERT INTO matakuliah (kode_matakuliah, nama, sks, kode_jurusan) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mk.kodeMatakuliah);
            stmt.setString(2, mk.nama);
            stmt.setInt(3, mk.sks);
            stmt.setString(4, mk.kodeJurusan);
            stmt.executeUpdate();
            System.out.println("Mata kuliah berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMataKuliah(MataKuliah mk) {
        if (conn == null) {
            return;
        }

        String sql = "UPDATE matakuliah SET nama = ?, sks = ?, kode_jurusan = ? WHERE kode_matakuliah = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mk.nama);
            stmt.setInt(2, mk.sks);
            stmt.setString(3, mk.kodeJurusan);
            stmt.setString(4, mk.kodeMatakuliah);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Mata kuliah berhasil diubah.");
            } else {
                System.out.println("Mata kuliah tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMataKuliah(String kodeMatakuliah) {
        if (conn == null) {
            return;
        }

        String sql = "DELETE FROM matakuliah WHERE kode_matakuliah = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kodeMatakuliah);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Mata kuliah berhasil dihapus.");
            } else {
                System.out.println("Mata kuliah tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}