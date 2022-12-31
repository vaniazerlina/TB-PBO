public class Penyewa {
    String id_penyewa;
    String nama_penyewa;

    // constructor
    public Penyewa() {

    }

    // constructutor
    public Penyewa(String id_penyewa, String nama_penyewa) {
        this.id_penyewa = id_penyewa;
        this.nama_penyewa = nama_penyewa;
        System.out.println("\n");
        System.out.println("=========Data Penyewa=========");
        System.out.println("ID_Penyewa : " + this.id_penyewa);
        System.out.println("Nama       : " + this.nama_penyewa);
    }

    public void method() {

    }
}
