package hy.material8database;

import hy.material8database.models.Mahasiswa;
import hy.material8database.repository.MahasiswaRepository;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner scanner = new Scanner(System.in);

    public App() {
        printAllMahasiswa();

        while (true) {
            System.out.print("\nTambahkan Mahasiswa? (y/n): ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("y")) {
                addNewMahasiswa();
            } else {
                break;
            }

            printAllMahasiswa();
        }
    }

    private void addNewMahasiswa() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama);
        MahasiswaRepository.addMahasiswa(mhs);
    }

    private void printAllMahasiswa() {
        List<Mahasiswa> allMahasiswa = MahasiswaRepository.getAllMahasiswa();
        System.out.println("\n📋 Daftar Mahasiswa:");
        for (Mahasiswa m : allMahasiswa) {
            System.out.println(m.nim + " - " + m.nama);
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
