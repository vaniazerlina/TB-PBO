import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Kamar implements CRUD {// merupakan parents class bagi penyewaan class
    // megimplementasikan method yang terdapat di kelas interface database

    // menghubungkan ke database
    Connection hub;
    String link = "jdbc:mysql://localhost:3306/apartemen";
    Scanner input = new Scanner(System.in);

    String id_kamar;
    String nomor_kamar;
    int harga_kamar;

    public void method() {

    }

    // implementasi database

    @Override
    public void insert() throws SQLException {
        System.out.println("\n=========Tambah Kamar========");
        System.out.println("\nID Kamar : ");
        this.id_kamar = input.nextLine();
        System.out.println("Nomor Kamar : ");
        this.nomor_kamar = input.nextLine();
        System.out.println("Harga Kamar : ");
        this.harga_kamar = input.nextInt();

        String sql = "INSERT INTO kamar (id_kamar, nomor_kamar, harga_kamar) VALUES ('" + id_kamar + "', '"
                + nomor_kamar + "', '" + harga_kamar + "')";
        hub = DriverManager.getConnection(link, "root", "");
        Statement statement = hub.createStatement();
        statement.execute(sql);
        System.out.println("Penginputan Berhasil");

        statement.close();
    }

    @Override
    public void view() throws SQLException {

        // akses ke database
        String sql = "SELECT * FROM kamar";
        hub = DriverManager.getConnection(link, "root", "");
        Statement statement = hub.createStatement();
        ResultSet hasil = statement.executeQuery(sql);

        // perulangan while
        while (hasil.next()) {
            System.out.println("\nID Kamar    : " + hasil.getString("id_kamar"));
            System.out.println("Nomor Kamar : " + hasil.getString("nomor_kamar"));
            System.out.println("Harga Kamar : " + hasil.getInt("harga_kamar"));
        }
        statement.close();

    }

    @Override
    public void update() throws SQLException {
        // penggunaan try and catch
        try {
            view();
            int pilihan1;
            System.out.println("\n==========Ubah Data Kamar===========");
            System.out.println("\nID Kamar :");
            String ubah = input.nextLine();

            // mengakses database table kamar
            String sql = "SELECT * FROM kamar WHERE id_kamar = '" + ubah + "'";
            Statement statement = hub.createStatement();
            ResultSet hasil = statement.executeQuery(sql);

            // percabangan if
            if (hasil.next()) {
                System.out.println("\nData yang akan diubah : \n1.Nomor Kamar \n2.Harga Kamar");
                System.out.println("Masukkan Pilihan :");
                pilihan1 = input.nextInt();
                input.nextLine();

                // percabangan dengan switch case
                switch (pilihan1) {
                    case 1:
                        System.out.println("\nNomor Kamar = " + hasil.getString("nomor_kamar") + "");
                        String ubah1 = input.nextLine();
                        // update database tabel kamar
                        sql = "UPDATE kamar SET nomor_kamar = '" + ubah1 + "' WHERE id_kamar = '" + ubah + "'";
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil Update Nomor Kamar (ID Kamar '" + ubah + "')");
                        }
                        break;

                    case 2:
                        System.out.println("\nHarga Kamar = " + hasil.getString("harga_kamar") + "");
                        int ubah2 = input.nextInt();
                        // update database tabel kamar
                        sql = "UPDATE kamar SET harga_kamar = '" + ubah2 + "' WHERE id_kamar = '" + ubah + "'";
                        input.nextLine();
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil Update Harga Kamar (ID Kamar '" + ubah + "')");
                        }
                        break;

                    default:
                        System.out.println("\nERROR\n Inputkan angka 1 atau 2!");
                        break;
                }
            } else {
                System.out.println("\nERROR");
            }

        }
        // untuk SQL
        catch (SQLException e) {
            System.err.println("Update Data Gagal");
        }

        // untuk input yang tidak sesuai jenis data
        catch (InputMismatchException e) {
            System.err.println("Data yang Dimasukkan Salah!");
        }

    }

    public void save() throws SQLException {

    }

    public void delete() throws SQLException {

    }

}
