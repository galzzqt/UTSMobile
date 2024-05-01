package com.example.uts_pemrogramanweb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText Nik, nama_lengkap, tempat_lahir, Alamat, Email, Usia, tanggal_lahir;
    String[] bankNames = {"Laki-laki", "Perempuan"};
    Spinner jenis_kelamin;
    RadioGroup kewarganegaraan;
    RadioButton wni, wna;
    CheckBox Development, AI_Services, Design_Creative, Writing, Finance_and_Accounting;
    Button reset_button, submit_button;
    private Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Nik = findViewById(R.id.nik);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        tempat_lahir = findViewById(R.id.tempat_lahir);
        Alamat = findViewById(R.id.alamat);
        Email = findViewById(R.id.email);
        Usia = findViewById(R.id.usia);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        kewarganegaraan = findViewById(R.id.kewarganegaraan);
        wni = findViewById(R.id.wni);
        wna = findViewById(R.id.wna);
        Development = findViewById(R.id.Development);
        AI_Services = findViewById(R.id.AI_Services);
        Design_Creative = findViewById(R.id.Design_Creative);
        Writing = findViewById(R.id.Writing);
        Finance_and_Accounting = findViewById(R.id.Finance_and_Accounting);
        reset_button = findViewById(R.id.reset_button);
        submit_button = findViewById(R.id.submit_button);

        // Set up Spinner for Gender
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bankNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenis_kelamin.setAdapter(arrayAdapter);

        // Initialize Calendar instance
        myCalendar = Calendar.getInstance();

        // Set up OnClickListener for Date EditText
        tanggal_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // Set up OnClickListener for Reset Button
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });

        // Set up OnClickListener for Submit Button
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }
    private void showDatePickerDialog() {
        new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // DatePickerDialog.OnDateSetListener
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            calculateAge();
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        tanggal_lahir.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void resetForm() {
        Nik.setText("");
        nama_lengkap.setText("");
        tempat_lahir.setText("");
        Alamat.setText("");
        Usia.setText("");
        Email.setText("");

        // Clear selected date
        tanggal_lahir.setText("");

        jenis_kelamin.setSelection(0);
        kewarganegaraan.clearCheck();
        Development.setChecked(false);
        AI_Services.setChecked(false);
        Design_Creative.setChecked(false);
        Writing.setChecked(false);
        Finance_and_Accounting.setChecked(false);
    }

    private void submitForm() {
        // Retrieve data from input fields
        String nik = Nik.getText().toString();
        String namaLengkap = nama_lengkap.getText().toString();
        String tanggalLahirStr = tanggal_lahir.getText().toString();
        String tempatLahir = tempat_lahir.getText().toString();
        String alamat = Alamat.getText().toString();
        String usia = Usia.getText().toString(); // Get age directly from EditText
        String email = Email.getText().toString();
        String jenisKelamin = jenis_kelamin.getSelectedItem().toString();

        // Check if all required fields are filled
        if (nik.isEmpty() || namaLengkap.isEmpty() || tempatLahir.isEmpty() || alamat.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua kolom yang diperlukan", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve selected radio button for kewarganegaraan
        int selectedKewarganegaraanId = kewarganegaraan.getCheckedRadioButtonId();
        RadioButton selectedKewarganegaraan = findViewById(selectedKewarganegaraanId);
        String kewarganegaraan = selectedKewarganegaraan.getText().toString();

        // Retrieve selected checkboxes for bidang kompetensi
        StringBuilder bidangKompetensi = new StringBuilder();
        if (Development.isChecked()) {
            bidangKompetensi.append("Development & IT, ");
        }
        if (AI_Services.isChecked()) {
            bidangKompetensi.append("AI Services, ");
        }
        if (Design_Creative.isChecked()) {
            bidangKompetensi.append("Design Creative, ");
        }
        if (Writing.isChecked()) {
            bidangKompetensi.append("Writing, ");
        }
        if (Finance_and_Accounting.isChecked()) {
            bidangKompetensi.append("Finance & Accounting, ");
        }

        // Remove trailing comma and space
        if (bidangKompetensi.length() > 0) {
            bidangKompetensi.setLength(bidangKompetensi.length() - 2);
        }

        // Validate age
        if (!isValidAge(usia)) {
            Toast.makeText(this, "Usia tidak boleh lebih besar dari tanggal hari ini", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display submitted data in Toast
        String submittedData = "NIK: " + nik + "\n" +
                "Nama Lengkap: " + namaLengkap + "\n" +
                "Tempat Lahir: " + tempatLahir + "\n" +
                "Alamat: " + alamat + "\n" +
                "Usia: " + usia + "\n" +
                "Jenis Kelamin: " + jenisKelamin + "\n" +
                "Kewarganegaraan: " + kewarganegaraan + "\n" +
                "Bidang Kompetensi: " + bidangKompetensi.toString() + "\n" +
                "Email: " + email;

        // Start TampilActivity and pass data via intent
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("NIK", nik);
        intent.putExtra("NamaLengkap", namaLengkap);
        intent.putExtra("TanggalLahir", tanggalLahirStr);
        intent.putExtra("TempatLahir", tempatLahir);
        intent.putExtra("Alamat", alamat);
        intent.putExtra("Usia", usia);
        intent.putExtra("JenisKelamin", jenisKelamin);
        intent.putExtra("Kewarganegaraan", kewarganegaraan);
        intent.putExtra("BidangKompetensi", bidangKompetensi.toString());
        intent.putExtra("Email", email);
        startActivity(intent);

        Toast.makeText(this, submittedData, Toast.LENGTH_LONG).show();
    }

    private boolean isValidAge(String age) {
        // Get current date
        Calendar today = Calendar.getInstance();

        // Parse the age as integer
        int enteredAge = Integer.parseInt(age);

        // Set the birth date to the selected date of birth
        Calendar birthDate = (Calendar) myCalendar.clone();

        // Subtract the entered age from the birth year to get the birth year
        birthDate.add(Calendar.YEAR, -enteredAge);

        // Check if the birth date is before or equal to today's date
        return birthDate.before(today) || birthDate.equals(today);
    }


    private boolean isValidEmail(String email) {
        // Simple email validation
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && (email.endsWith("@gmail.com") || email.endsWith("@mail.com"));
    }

    private void calculateAge() {
        // Get current date
        Calendar today = Calendar.getInstance();

        // Calculate age based on selected date of birth
        int age = today.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < myCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        // Set the calculated age to the EditText
        Usia.setText(String.valueOf(age));
    }

}