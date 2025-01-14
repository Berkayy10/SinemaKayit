Sinema Müşteri Kayıt Sistemi

Bu proje, bir sinema kayıt sistemi geliştirmek için yazılmış bir Java uygulamasıdır. Proje kapsamında film, müşteri ve salon kayıtları oluşturulabilir, görüntülenebilir ve JSON formatında dosyaya kaydedilebilir.

Proje Özellikleri

Film Ekleme: Sisteme yeni filmler eklenebilir.
Müşteri Ekleme: Sisteme yeni müşteriler eklenebilir.
Salon Ekleme: Sistem içinde salonlar oluşturulabilir ve her salon için bir film atanabilir.
Veri Listeleme: Eklenen filmler, müşteriler ve salonlar görüntülenebilir.
JSON Kaydetme: Tüm veriler Film.json, Musteri.json ve Salon.json dosyalarına kaydedilir.
Kullanım

Proje dosyalarını bir Java IDE'sine (örneğin IntelliJ IDEA veya Eclipse) yükleyin.
Projeyi çalıştırın ve menüden ilgili seçeneği seçin.
Menü seçenekleri:
1: Yeni bir film ekler.
2: Yeni bir müşteri ekler.
3: Yeni bir salon ekler ve bir film atar.
4: Mevcut tüm kayıtları listeler.
5: Verileri JSON dosyasına kaydederek programı sonlandırır.
Gereksinimler

Java JDK: 8 veya daha üstü
Jackson Library: JSON işleme için com.fasterxml.jackson.databind kütüphanesi kullanılmıştır.
JSON Dosya Yapıları

Film.json
[
  {
    "id": 1,
    "name": "Film Adı",
    "sure": 120,
    "tur": "Tür"
  }
]
Musteri.json
[
  {
    "id": 1,
    "name": "Müşteri Adı",
    "salonAdi": "Salon Adı"
  }
]
Salon.json
[
  {
    "id": 1,
    "name": "Salon Adı",
    "film": {
      "id": 1,
      "name": "Film Adı",
      "sure": 120,
      "tur": "Tür"
    },
    "musteriler": [
      {
        "id": 1,
        "name": "Müşteri Adı",
        "salonAdi": "Salon Adı"
      }
    ]
  }
]
Lisans

Bu proje açık kaynak kodludur ve herkes tarafından kullanılabilir ve geliştirilebilir.
