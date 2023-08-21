package com.example.sem_2_prac_4

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.app.DatePickerDialog as DatePickerDialog1

class MainActivity : AppCompatActivity() {

    lateinit var edtName: EditText
    lateinit var edtCity: EditText
    lateinit var edtDob: EditText
    lateinit var btnSubmit: Button
    lateinit var datePickerDialog: android.app.DatePickerDialog
    lateinit var progress: ProgressDialog

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtCity = findViewById(R.id.edtCity)
        edtDob = findViewById(R.id.edtDob)
        btnSubmit = findViewById(R.id.btnSubmit)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()
        edtCity.setOnClickListener {
            var selectedItmes = 0
            var city = arrayOf("Surat", "Mehasana", "Rajkot", "Ahemabad")

            var selectedCity = city[selectedItmes]

            var cityDialog = AlertDialog.Builder(this)
                .setTitle("Select your city:")
                .setSingleChoiceItems(city, selectedItmes) { dialog, which ->
                    selectedItmes = which
                    selectedCity = city[selectedItmes]
                }
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                    edtCity.setText("$selectedCity")
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, i ->
                    dialog.dismiss()
                })
            cityDialog.setCancelable(false)
            cityDialog.create()
            cityDialog.show()
        }

        edtDob.setOnClickListener {
            datePickerDialog = android.app.DatePickerDialog(
                this, android.app.DatePickerDialog.OnDateSetListener
                { view, year, month, dayOfMonth ->
                    edtDob.setText("$month/$dayOfMonth/$year")
                },
                2023, 1, 27
            )
            datePickerDialog.show()

        }

        btnSubmit.setOnClickListener {
            var confirmDialog = AlertDialog.Builder(this)
                .setTitle("Confirmed")
                .setIcon(R.drawable.baseline_done_24)
                .setMessage("Your details are confirmed")
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, i ->


                    //progress = ProgressDialog.show(this, "Processing..", "Please wait..")
                    progress = ProgressDialog(this)
                    progress.setTitle("TIT")
                    progress.setMessage("mess")
                    progress.show()

                    var handler = Handler()
                    handler.postDelayed(Runnable() {
                        kotlin.run {
                            Thread.sleep(3000)
                            progress.dismiss()
                              alert()
                        }
                       // Toast.makeText(this, "InSIde Handler", Toast.LENGTH_LONG).show()
                    }, 0)

                })
                .create()
            confirmDialog.show()

        }

    }

    fun alert() {

        var name = edtName.text.toString()
        var userCity = edtCity.text.toString()
        var DobUser = edtDob.text.toString()

        var alert = AlertDialog.Builder(this)
            .setTitle("Information")
            .setMessage("Name:-$name\n City:-$userCity\n DoB:-$DobUser")
            .setNeutralButton(
                "Cancel",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
        alert.create()
        alert.show()
    }
}






