package com.example.uts_pemrogramanweb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nik = extras.getString("NIK");
            String namaLengkap = extras.getString("NamaLengkap");
            String tanggalLahir = extras.getString("TanggalLahir");
            String tempatLahir = extras.getString("TempatLahir");
            String alamat = extras.getString("Alamat");
            String usia = extras.getString("Usia");
            String jenisKelamin = extras.getString("JenisKelamin");
            String kewarganegaraan = extras.getString("Kewarganegaraan");
            String bidangKompetensi = extras.getString("BidangKompetensi");
            String email = extras.getString("Email");

            // Set data to TextViews
            TextView nikTextView = findViewById(R.id.nik);
            nikTextView.setText(nik);

            TextView namaLengkapTextView = findViewById(R.id.nama_lengkap);
            namaLengkapTextView.setText(namaLengkap);

            TextView tanggalLahirTextView = findViewById(R.id.tanggal_lahir);
            tanggalLahirTextView.setText(tanggalLahir);

            TextView tempatLahirTextView = findViewById(R.id.tempat_lahir);
            tempatLahirTextView.setText(tempatLahir);

            TextView alamatTextView = findViewById(R.id.alamat);
            alamatTextView.setText(alamat);

            TextView usiaTextView = findViewById(R.id.usia);
            usiaTextView.setText(usia);

            TextView jenisKelaminTextView = findViewById(R.id.jenis_kelamin);
            jenisKelaminTextView.setText(jenisKelamin);

            TextView kewarganegaraanTextView = findViewById(R.id.kewarganegaraan);
            kewarganegaraanTextView.setText(kewarganegaraan);

            TextView bidangKompetensiTextView = findViewById(R.id.BidangKompetensi);
            bidangKompetensiTextView.setText(bidangKompetensi);

            TextView emailTextView = findViewById(R.id.email);
            emailTextView.setText(email);
        }

        // Set up back button
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}
