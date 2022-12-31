import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*class apart
merupakan kelas yang akan menjadi kontrol dari semua kelas lain nantinya.
*/
public class apart {
    // menghubungkan ke database
    static Connection hub;
    static String link = "jdbc:mysql://localhost:3306/apartemen";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("==================================");
        System.out.println("    APARTEMEN STUDIO HAPPINESS    ");
        System.out.println("==================================");
        String kata = "Selamat Datang :";
        /*
         * String
         * cara mengganti string 'menu:' dengan kalimat lain
         */
        String kataGanti = kata.replace("Selamat Datang :", "Selamat Menggunakan Sistem :)");
        // mengubah ke LowerCase
        System.out.println(kataGanti.toLowerCase());

        /*
         * date
         * cara untuk membuat deklarasi tanggal serta waktu
         */
        DateFormat tanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat jam = new SimpleDateFormat("HH:mm:ss");
        Date tanggal1 = new Date();
        // memanggil date
        System.out.println("Keterangan Akses Program : ");
        System.out.println("Tanggal : " + tanggal.format(tanggal1));
        System.out.println("Waktu   : " + jam.format(tanggal1));

        admin();
        menu();

    }

    // codingan untuk menu()
    private static void menu() throws Exception {
        boolean kembali = true;
        boolean tanya = true;
        int pilihan;
        String pertanyaan;

        // while
        while (kembali) {
            System.out.println("==================================");
            System.out.println("          PILIHAN MENU          ");
            System.out.println("==================================");
            System.out.println("1. Lihat Kamar yang Dipunya ");
            System.out.println("2. Masukkan Kamar Baru");
            System.out.println("3. Ubah Data Kamar");
            System.out.println("4. Penyewaan Kamar");
            System.out.println("5. Pembatalan Penyewaan Kamar");
            System.out.println("6. Keluar Program");
            System.out.println("Masukkan Pilihan (1/2/3/4/5/6) : ");

            pilihan = input.nextInt();
            input.nextLine();
            Kamar kamar = new Kamar();
            Penyewaan penyewaan = new Penyewaan();

            // percabangan dengan switch case
            switch (pilihan) {
                case 1:
                    kamar.view();
                    tanya = true;
                    break;

                case 2:
                    kamar.insert();
                    tanya = true;
                    break;

                case 3:
                    kamar.update();
                    tanya = true;
                    break;

                case 4:
                    penyewaan.save();
                    tanya = true;
                    break;

                case 5:
                    penyewaan.delete();
                    tanya = true;
                    break;

                case 6:
                    tanya = false;
                    kembali = false;
                    break;

                default:
                    System.out.println("\nPeriksa kembali angka inputan");
                    break;
            }

            // perulangan while
            while (tanya) {
                System.out.println("\nKembali ke menu utama? (y/n)");
                pertanyaan = input.nextLine();
                // percabangan if else
                // equals ignore case untuk mengabaikan data yang diinputkan nanti Upper atau
                // Lower case
                if (pertanyaan.equalsIgnoreCase("n")) {
                    kembali = false;
                    tanya = false;
                } else if (pertanyaan.equalsIgnoreCase("y")) {
                    kembali = true;
                    tanya = false;
                } else {
                    System.out.println("Inputkan y atau n!");
                }
            }
        }
        System.out.println("\n===========Selesai=============");
    }

    // codingan untuk admin()
    private static void admin() throws SQLException {
        /*
         * HashMap
         * membuat objek hashmap
         */
        Map<String, String> Login = new HashMap<String, String>();

        // penghubungan bagian login ke database
        String unameInput, passInput;
        String sql = "SELECT * FROM login";
        boolean relogin = true;
        hub = DriverManager.getConnection(link, "root", "");
        Statement statement = hub.createStatement();
        ResultSet hasil = statement.executeQuery(sql);

        // penggunaan while
        while (hasil.next()) {

            // pengambilan nilai dari table login
            String username = hasil.getString("username");
            String password = hasil.getString("password");

            // key dan value pada hashmap
            Login.put(username, password);
        }
        System.out.println("-----------------------------------");

        // while
        // jika terjadi kesalahan dalam input username dan password
        while (relogin) {
            System.out.println("Username : ");
            unameInput = input.nextLine();
            System.out.println("Password : ");
            passInput = input.nextLine();

            if (Login.get(unameInput).equals(passInput) == true) {
                System.out.println("=======================================");
                System.out.println("    Selamat Menggunakan Sistem         ");
                relogin = false; // karena user sudah berhasil login
            }
            if (Login.get(unameInput).equals(passInput) == false) {
                System.out.println("\nPeriksa Kembali Username Atau Password Anda\n");
                relogin = true; // karena user gagal maka diminta untuk input kembali
            }

        }
        statement.close();
    }

}
