package hy.material8database;

import hy.material8database.database.Database;
import hy.material8database.models.Mahasiswa;
import hy.material8database.models.Jurusan;
import hy.material8database.models.MataKuliah;
import hy.material8database.repository.MahasiswaRepository;
import hy.material8database.repository.JurusanRepository;
import hy.material8database.repository.MataKuliahRepository;
import hy.material8database.repository.MahasiswaMataKuliahRepository;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (Database.connect() == null) {
            return;
        }

        new App().menuUtama();
    }

    private void menuUtama() {
        while (true) {
            System.out.println("\nMenu utama");
            System.out.println("1. Mahasiswa");
            System.out.println("2. Jurusan");
            System.out.println("3. Mata Kuliah");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                menuMahasiswa();
            } else if (pilihan.equals("2")) {
                menuJurusan();
            } else if (pilihan.equals("3")) {
                menuMataKuliah();
            } else if (pilihan.equals("0")) {
                System.out.println("Program selesai.");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void menuMahasiswa() {
        while (true) {
            System.out.println("\nMenu mahasiswa");
            System.out.println("1. View All");
            System.out.println("2. Create");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            System.out.println("5. Ambil Matkul");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                viewMahasiswa();
            } else if (pilihan.equals("2")) {
                createMahasiswa();
            } else if (pilihan.equals("3")) {
                editMahasiswa();
            } else if (pilihan.equals("4")) {
                deleteMahasiswa();
            } else if (pilihan.equals("5")) {
                ambilMatkul();
            } else if (pilihan.equals("0")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void viewMahasiswa() {
        List<Mahasiswa> data = MahasiswaRepository.getAllMahasiswa();

        System.out.println("\nDaftar mahasiswa");

        if (data.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
        }

        for (Mahasiswa m : data) {
            System.out.println(m.nim + " - " + m.nama + " - " + m.kodeJurusan);
        }
    }

    private void createMahasiswa() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        viewJurusan();

        System.out.print("Masukkan Kode Jurusan: ");
        String kodeJurusan = scanner.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama, kodeJurusan);
        MahasiswaRepository.addMahasiswa(mhs);
    }

    private void editMahasiswa() {
        System.out.print("Masukkan NIM mahasiswa yang ingin diedit: ");
        String nim = scanner.nextLine();

        System.out.print("Masukkan Nama Baru: ");
        String nama = scanner.nextLine();

        viewJurusan();

        System.out.print("Masukkan Kode Jurusan Baru: ");
        String kodeJurusan = scanner.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama, kodeJurusan);
        MahasiswaRepository.updateMahasiswa(mhs);
    }

    private void deleteMahasiswa() {
        System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
        String nim = scanner.nextLine();

        MahasiswaRepository.deleteMahasiswa(nim);
    }

    private void ambilMatkul() {
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();

        viewMataKuliah();

        System.out.print("Masukkan Kode Mata Kuliah: ");
        String kodeMatakuliah = scanner.nextLine();

        MahasiswaMataKuliahRepository.ambilMataKuliah(nim, kodeMatakuliah);
    }

    private void menuJurusan() {
        while (true) {
            System.out.println("\n=== MENU JURUSAN ===");
            System.out.println("1. View All");
            System.out.println("2. Create");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                viewJurusan();
            } else if (pilihan.equals("2")) {
                createJurusan();
            } else if (pilihan.equals("3")) {
                editJurusan();
            } else if (pilihan.equals("4")) {
                deleteJurusan();
            } else if (pilihan.equals("0")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void viewJurusan() {
        List<Jurusan> data = JurusanRepository.getAllJurusan();

        System.out.println("\nDaftar jurusan");

        if (data.isEmpty()) {
            System.out.println("Belum ada data jurusan.");
        }

        for (Jurusan j : data) {
            System.out.println(j.kodeJurusan + " - " + j.nama);
        }
    }

    private void createJurusan() {
        System.out.print("Masukkan Kode Jurusan: ");
        String kodeJurusan = scanner.nextLine();

        System.out.print("Masukkan Nama Jurusan: ");
        String nama = scanner.nextLine();

        Jurusan jurusan = new Jurusan(kodeJurusan, nama);
        JurusanRepository.addJurusan(jurusan);
    }

    private void editJurusan() {
        System.out.print("Masukkan Kode Jurusan yang ingin diedit: ");
        String kodeJurusan = scanner.nextLine();

        System.out.print("Masukkan Nama Jurusan Baru: ");
        String nama = scanner.nextLine();

        Jurusan jurusan = new Jurusan(kodeJurusan, nama);
        JurusanRepository.updateJurusan(jurusan);
    }

    private void deleteJurusan() {
        System.out.print("Masukkan Kode Jurusan yang ingin dihapus: ");
        String kodeJurusan = scanner.nextLine();

        JurusanRepository.deleteJurusan(kodeJurusan);
    }

    private void menuMataKuliah() {
        while (true) {
            System.out.println("\n=== MENU MATA KULIAH ===");
            System.out.println("1. View All");
            System.out.println("2. Create");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                viewMataKuliah();
            } else if (pilihan.equals("2")) {
                createMataKuliah();
            } else if (pilihan.equals("3")) {
                editMataKuliah();
            } else if (pilihan.equals("4")) {
                deleteMataKuliah();
            } else if (pilihan.equals("0")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void viewMataKuliah() {
        List<MataKuliah> data =
            MataKuliahRepository.getAllMataKuliah();

        System.out.println("\nDaftar mata kuliah");

        if (data.isEmpty()) {
            System.out.println("Belum ada data mata kuliah.");
        }

        for (MataKuliah mk : data) {
            System.out.println(mk.kodeMatakuliah + " - " + mk.nama + " - " + mk.sks + " SKS - " + mk.kodeJurusan);
        }
    }

    private void createMataKuliah() {
        System.out.print("Masukkan Kode Mata Kuliah: ");
        String kode = scanner.nextLine();

        System.out.print("Masukkan Nama Mata Kuliah: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Jumlah SKS: ");
        int sks = Integer.parseInt(scanner.nextLine());

        viewJurusan();

        System.out.print("Masukkan Kode Jurusan: ");
        String kodeJurusan = scanner.nextLine();

        MataKuliah mk = new MataKuliah(kode, nama, sks, kodeJurusan);

        MataKuliahRepository.addMataKuliah(mk);
    }

    private void editMataKuliah() {
        System.out.print("Masukkan Kode Mata Kuliah yang ingin diedit: ");
        String kode = scanner.nextLine();

        System.out.print("Masukkan Nama Mata Kuliah Baru: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Jumlah SKS Baru: ");
        int sks = Integer.parseInt(scanner.nextLine());

        viewJurusan();

        System.out.print("Masukkan Kode Jurusan Baru: ");
        String kodeJurusan = scanner.nextLine();

        MataKuliah mk = new MataKuliah(kode, nama, sks, kodeJurusan);

        MataKuliahRepository.updateMataKuliah(mk);
    }

    private void deleteMataKuliah() {
        System.out.print("Masukkan Kode Mata Kuliah yang ingin dihapus: ");
        String kode = scanner.nextLine();

        MataKuliahRepository.deleteMataKuliah(kode);
    }
}