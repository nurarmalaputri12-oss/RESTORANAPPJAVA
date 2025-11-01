import java.util.Scanner;

class Menu {
    String nama;
    int harga;
    String kategori;

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Main {
    public static void main(String[] args) {
        // Inisialisasi array menu
        Menu[] menus = {
            new Menu("Nasi Padang", 25000, "makanan"),
            new Menu("Ayam Goreng", 20000, "makanan"),
            new Menu("Sate", 30000, "makanan"),
            new Menu("Bakso", 15000, "makanan"),
            new Menu("Teh Manis", 5000, "minuman"),
            new Menu("Kopi", 10000, "minuman"),
            new Menu("Jus Jeruk", 8000, "minuman"),
            new Menu("Air Mineral", 3000, "minuman")
        };

        // Tampilkan menu
        tampilkanMenu(menus);

        // Input pesanan
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan pesanan 1 (format: nama = jumlah):");
        String input1 = sc.nextLine();
        System.out.println("Masukkan pesanan 2 (format: nama = jumlah):");
        String input2 = sc.nextLine();
        System.out.println("Masukkan pesanan 3 (format: nama = jumlah):");
        String input3 = sc.nextLine();
        System.out.println("Masukkan pesanan 4 (format: nama = jumlah):");
        String input4 = sc.nextLine();

        // Parse pesanan
        String[] pesanan1 = parsePesanan(input1);
        String[] pesanan2 = parsePesanan(input2);
        String[] pesanan3 = parsePesanan(input3);
        String[] pesanan4 = parsePesanan(input4);

        // Hitung total
        int[] hasil = hitungTotal(menus, pesanan1, pesanan2, pesanan3, pesanan4);

        // Cetak struk
        cetakStruk(menus, pesanan1, pesanan2, pesanan3, pesanan4, hasil);
    }

    public static void tampilkanMenu(Menu[] menus) {
        System.out.println("Menu Makanan:");
        System.out.println(menus[0].nama + " - Rp " + menus[0].harga);
        System.out.println(menus[1].nama + " - Rp " + menus[1].harga);
        System.out.println(menus[2].nama + " - Rp " + menus[2].harga);
        System.out.println(menus[3].nama + " - Rp " + menus[3].harga);
        System.out.println("Menu Minuman:");
        System.out.println(menus[4].nama + " - Rp " + menus[4].harga);
        System.out.println(menus[5].nama + " - Rp " + menus[5].harga);
        System.out.println(menus[6].nama + " - Rp " + menus[6].harga);
        System.out.println(menus[7].nama + " - Rp " + menus[7].harga);
    }

    public static String[] parsePesanan(String input) {
        String[] parts = input.split(" = ");
        return parts;
    }

    public static int[] hitungTotal(Menu[] menus, String[] p1, String[] p2, String[] p3, String[] p4) {
        int totalItem = 0;
        int totalMinuman = 0;
        int jumlahMinuman = 0;

        // Hitung untuk pesanan 1
        if (p1.length == 2) {
            int harga = cariHarga(menus, p1[0]);
            int jumlah = Integer.parseInt(p1[1]);
            totalItem += harga * jumlah;
            if (cariKategori(menus, p1[0]).equals("minuman")) {
                totalMinuman += harga * jumlah;
                jumlahMinuman += jumlah;
            }
        }

        // Hitung untuk pesanan 2
        if (p2.length == 2) {
            int harga = cariHarga(menus, p2[0]);
            int jumlah = Integer.parseInt(p2[1]);
            totalItem += harga * jumlah;
            if (cariKategori(menus, p2[0]).equals("minuman")) {
                totalMinuman += harga * jumlah;
                jumlahMinuman += jumlah;
            }
        }

        // Hitung untuk pesanan 3
        if (p3.length == 2) {
            int harga = cariHarga(menus, p3[0]);
            int jumlah = Integer.parseInt(p3[1]);
            totalItem += harga * jumlah;
            if (cariKategori(menus, p3[0]).equals("minuman")) {
                totalMinuman += harga * jumlah;
                jumlahMinuman += jumlah;
            }
        }

        // Hitung untuk pesanan 4
        if (p4.length == 2) {
            int harga = cariHarga(menus, p4[0]);
            int jumlah = Integer.parseInt(p4[1]);
            totalItem += harga * jumlah;
            if (cariKategori(menus, p4[0]).equals("minuman")) {
                totalMinuman += harga * jumlah;
                jumlahMinuman += jumlah;
            }
        }

        // Terapkan penawaran minuman jika totalItem > 50000
        int totalSetelahPenawaran = totalItem;
        if (totalItem > 50000 && jumlahMinuman >= 2) {
            // Beli satu gratis satu: jumlah yang dibayar = (jumlahMinuman / 2) + (jumlahMinuman % 2)
            int hargaPerMinuman = totalMinuman / jumlahMinuman;
            int jumlahBayar = (jumlahMinuman / 2) + (jumlahMinuman % 2);
            totalSetelahPenawaran = totalItem - totalMinuman + (hargaPerMinuman * jumlahBayar);
        }

        // Terapkan diskon 10% jika totalSetelahPenawaran > 100000
        int diskon = 0;
        if (totalSetelahPenawaran > 100000) {
            diskon = totalSetelahPenawaran / 10;
            totalSetelahPenawaran -= diskon;
        }

        // Pajak 10% dari totalSetelahPenawaran
        int pajak = totalSetelahPenawaran / 10;

        // Biaya pelayanan
        int pelayanan = 20000;

        // Total akhir
        int totalAkhir = totalSetelahPenawaran + pajak + pelayanan;

        return new int[]{totalItem, totalSetelahPenawaran, diskon, pajak, pelayanan, totalAkhir};
    }

    public static int cariHarga(Menu[] menus, String nama) {
        if (nama.equals(menus[0].nama)) return menus[0].harga;
        if (nama.equals(menus[1].nama)) return menus[1].harga;
        if (nama.equals(menus[2].nama)) return menus[2].harga;
        if (nama.equals(menus[3].nama)) return menus[3].harga;
        if (nama.equals(menus[4].nama)) return menus[4].harga;
        if (nama.equals(menus[5].nama)) return menus[5].harga;
        if (nama.equals(menus[6].nama)) return menus[6].harga;
        if (nama.equals(menus[7].nama)) return menus[7].harga;
        return 0;
    }

    public static String cariKategori(Menu[] menus, String nama) {
        if (nama.equals(menus[0].nama)) return menus[0].kategori;
        if (nama.equals(menus[1].nama)) return menus[1].kategori;
        if (nama.equals(menus[2].nama)) return menus[2].kategori;
        if (nama.equals(menus[3].nama)) return menus[3].kategori;
        if (nama.equals(menus[4].nama)) return menus[4].kategori;
        if (nama.equals(menus[5].nama)) return menus[5].kategori;
        if (nama.equals(menus[6].nama)) return menus[6].kategori;
        if (nama.equals(menus[7].nama)) return menus[7].kategori;
        return "";
    }

    public static void cetakStruk(Menu[] menus, String[] p1, String[] p2, String[] p3, String[] p4, int[] hasil) {
        System.out.println("\n=== STRUK PESANAN ===");
        System.out.println("Item yang dipesan:");

        // Cetak pesanan 1
        if (p1.length == 2) {
            int harga = cariHarga(menus, p1[0]);
            int jumlah = Integer.parseInt(p1[1]);
            int totalPerItem = harga * jumlah;
            System.out.println(p1[0] + " x " + jumlah + " = Rp " + totalPerItem);
        }

        // Cetak pesanan 2
        if (p2.length == 2) {
            int harga = cariHarga(menus, p2[0]);
            int jumlah = Integer.parseInt(p2[1]);
            int totalPerItem = harga * jumlah;
            System.out.println(p2[0] + " x " + jumlah + " = Rp " + totalPerItem);
        }

        // Cetak pesanan 3
        if (p3.length == 2) {
            int harga = cariHarga(menus, p3[0]);
            int jumlah = Integer.parseInt(p3[1]);
            int totalPerItem = harga * jumlah;
            System.out.println(p3[0] + " x " + jumlah + " = Rp " + totalPerItem);
        }

        // Cetak pesanan 4
        if (p4.length == 2) {
            int harga = cariHarga(menus, p4[0]);
            int jumlah = Integer.parseInt(p4[1]);
            int totalPerItem = harga * jumlah;
            System.out.println(p4[0] + " x " + jumlah + " = Rp " + totalPerItem);
        }

        System.out.println("Total biaya pemesanan: Rp " + hasil[0]);
        if (hasil[2] > 0) {
            System.out.println("Diskon 10%: Rp " + hasil[2]);
            System.out.println("Total setelah diskon: Rp " + hasil[1]);
        }
        System.out.println("Pajak 10%: Rp " + hasil[3]);
        System.out.println("Biaya pelayanan: Rp " + hasil[4]);
        System.out.println("Total akhir: Rp " + hasil[5]);
    }
}
