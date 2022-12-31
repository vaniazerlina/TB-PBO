import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//inheritance (child class)
/*penyewaan class mengambil method yang terdapat pada 
kamar class yang merupakan parent class */
public class Penyewaan extends Kamar {

    // koneksi ke database
    Connection hub;
    String link = "jdbc:mysql://localhost:3306/apartemen";
    Scanner input = new Scanner(System.in);

    String id_sewa, checkin, checkout, nama_penyewa, id_penyewa;
    int total_harga, bulan;

    public void id_sewa() {
        System.out.println("\nID Sewa : ");
        this.id_sewa = input.nextLine();
    }

    public void id_penyewa() {
        System.out.println("ID Penyewa : ");
        this.id_penyewa = input.nextLine();
    }

    public void nama_penyewa() {
        System.out.println("Nama Penyewa : ");
        this.nama_penyewa = input.nextLine();
    }

    public void id_kamar() {
        System.out.println("ID Kamar: ");
        this.id_kamar = input.nextLine();
    }

    public void checkin() {
        System.out.println("Tanggal CheckIn : ");
        this.checkin = input.nextLine();
    }

    public void checkout() {
        System.out.println("Tanggal CheckOut : ");
        this.checkout = input.nextLine();
    }

    public void bulan() {
        System.out.println("Lama Sewa (bulan) : ");
        this.bulan = input.nextInt();
    }

    public void harga_kamar() {
        System.out.println("Harga : ");
        this.harga_kamar = input.nextInt();
    }

    public void total_harga() {
        // total harga yang harus dibayar
        this.total_harga = this.harga_kamar * this.bulan;
        System.out.println("Total Pembayaran : Rp." + this.total_harga);
    }

    // dari kelas penyewa
    public void penyewa() throws SQLException {
        Penyewa penyewa = new Penyewa(this.id_penyewa, this.nama_penyewa);
        penyewa.method();
    }

    @Override
    public void save() throws SQLException {
        try {
            view();
            System.out.println("\n==========Booking Kamar===========");
            id_sewa();
            id_penyewa();
            nama_penyewa();
            id_kamar();
            checkin();
            checkout();
            bulan();
            harga_kamar();
            total_harga();

            penyewa();

            hub = DriverManager.getConnection(link, "root", "");
            Statement statement = hub.createStatement();
            String sql = "INSERT INTO penyewaan (id_sewa, id_penyewa, nama_penyewa, id_kamar, checkin, checkout,  bulan, total_harga) VALUES ('"
                    + id_sewa + "', '" + id_penyewa + "',  '" + nama_penyewa + "','" + id_kamar + "', ,'"
                    + checkin + "', '" + checkout + "' , '" + bulan + "', '" + total_harga + "')";
            statement.execute(sql);
            System.out.println("Pemesanan Berhasil");
        }
        // exception SQL
        catch (SQLException e) {
            System.err.println("Pemesanan Berhasil");
        }

        // exception input tidak sesuai data
        catch (InputMismatchException e) {
            System.err.println("Inputan Data tidak Sesuai");
        }

    }

    @Override
    public void delete() throws SQLException {
        try {
            System.out.println("---------Pembatalan Penyewaan---------");
            System.out.println("ID Penyewa : ");
            this.id_penyewa = input.next();

            String sql = "DELETE FROM penyewaan WHERE id_penyewa = " + id_penyewa;
            hub = DriverManager.getConnection(link, "root", "");
            Statement statement = hub.createStatement();

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil Menghapus Penyewaan");
            }
        }

        catch (SQLException e) {
            System.err.println("Terjadi Kesalahan Dalam Penghapusan Data");
        } catch (Exception e) {
            System.err.println("Masukkan ID Penyewa Dengan Benar");
        }
    }
}
