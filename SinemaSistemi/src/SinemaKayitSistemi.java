import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BaseEntity {
    protected int id;
    protected String name;
    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void BilgiGoster() {
        System.out.println("ID: " + id + ", Ad: " + name);
    }
}

interface IKayit {
    void kayitEkle(Object obj);
    void listele();
}

class Film extends BaseEntity {
    private int sure;
    private String tur;
    public Film(int id, String name, int sure, String tur) {
        super(id, name);
        this.sure = sure;
        this.tur = tur;
    }
    @Override
    public void BilgiGoster() {
        super.BilgiGoster();
        System.out.println("Süre: " + sure + " dk, Tür: " + tur);
    }
    public int getSure() { return sure; }
    public String getTur() { return tur; }
}

class Musteri extends BaseEntity {
    private String salonAdi;
    public Musteri(int id, String name, String salonAdi) {
        super(id, name);
        this.salonAdi = salonAdi;
    }
    @Override
    public void BilgiGoster() {
        super.BilgiGoster();
        System.out.println("Salon Adı: " + salonAdi);
    }
}

class Salon extends BaseEntity implements IKayit {
    private Film film;
    private List<Musteri> musteriler = new ArrayList<>();
    public Salon(int id, String name, Film film) {
        super(id, name);
        this.film = film;
    }
    @Override
    public void kayitEkle(Object obj) {
        if (obj instanceof Musteri) musteriler.add((Musteri) obj);
    }
    @Override
    public void listele() {
        System.out.println("Salon: " + name);
        film.BilgiGoster();
        System.out.println("Kayıtlı Müşteriler:");
        for (Musteri musteri : musteriler) musteri.BilgiGoster();
    }
    public Film getFilm() { return film; }
    public List<Musteri> getMusteriler() { return musteriler; }
}

public class SinemaKayitSistemi {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Film> filmList = new ArrayList<>();
    private static List<Musteri> musteriList = new ArrayList<>();
    private static List<Salon> salonList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Film Ekle\n2. Müşteri Ekle\n3. Salon Ekle\n4. Verileri Listele\n5. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int secim = scanner.nextInt();
            scanner.nextLine();
            switch (secim) {
                case 1: filmEkle(); break;
                case 2: musteriEkle(); break;
                case 3: salonEkle(); break;
                case 4: verileriListele(); break;
                case 5: kayitlariKaydet(); return;
                default: System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }

    public static void filmEkle() {
        System.out.print("Film ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Film Adı: ");
        String name = scanner.nextLine();
        System.out.print("Süre (dakika): ");
        int sure = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tür: ");
        String tur = scanner.nextLine();
        filmList.add(new Film(id, name, sure, tur));
        System.out.println("Film eklendi.");
    }

    public static void musteriEkle() {
        System.out.print("Müşteri ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Müşteri Adı: ");
        String name = scanner.nextLine();
        System.out.print("Kayıtlı Olduğu Salon: ");
        String salonAdi = scanner.nextLine();
        musteriList.add(new Musteri(id, name, salonAdi));
        System.out.println("Müşteri eklendi.");
    }

    public static void salonEkle() {
        System.out.print("Salon ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Salon Adı: ");
        String name = scanner.nextLine();
        System.out.print("Gösterilecek Film ID: ");
        int filmId = scanner.nextInt();
        scanner.nextLine();
        Film film = filmList.stream().filter(f -> f.id == filmId).findFirst().orElse(null);
        if (film != null) {
            Salon salon = new Salon(id, name, film);
            salonList.add(salon);
            System.out.println("Salon eklendi.");
        } else {
            System.out.println("Film bulunamadı.");
        }
    }

    public static void verileriListele() {
        System.out.println("\nFilmler:");
        filmList.forEach(Film::BilgiGoster);
        System.out.println("\nMüşteriler:");
        musteriList.forEach(Musteri::BilgiGoster);
        System.out.println("\nSalonlar:");
        salonList.forEach(Salon::listele);
    }

    public static void kayitlariKaydet() {
        try {
            objectMapper.writeValue(new File("Film.json"), filmList);
            objectMapper.writeValue(new File("Musteri.json"), musteriList);
            objectMapper.writeValue(new File("Salon.json"), salonList);
            System.out.println("Veriler JSON dosyasına kaydedildi!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}