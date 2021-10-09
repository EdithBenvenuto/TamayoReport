package com.example.tamayoreport.controller

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.tamayoreport.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream

class Reporte : AppCompatActivity() {
    lateinit var reportCategory:TextView
    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val FINE_LOCATION_PERMISSION_CODE = 101
    }

    private lateinit var btnGetCurrentLocation: ImageButton
    private lateinit var btnTakePhoto: Button
    private lateinit var btnUbicacion: ImageButton
    //private lateinit var imgPhoto: ImageView
    private lateinit var btnSend: Button

    private lateinit var cancellationTokenSource: CancellationTokenSource
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var txtCoordinates: TextView
    var m_Text: String = ""
    val sb = StringBuffer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte)
        reportCategory=findViewById(R.id.reportCategory)
        val b = intent.extras
        val value = b?.getString("key")
        reportCategory.text = "Reporte "+value
        //Toast.makeText(this, value, Toast.LENGTH_SHORT).show()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@Reporte)


        btnGetCurrentLocation = findViewById(R.id.btnGetCurrentLocation)
        btnUbicacion = findViewById(R.id.btnUbicacion)
        btnTakePhoto = findViewById(R.id.btnTakePhoto)
        //imgPhoto = findViewById(R.id.imgPhoto)
        btnSend = findViewById(R.id.btnSend)

        btnGetCurrentLocation.setOnClickListener(btnCurrentLocationListener())
        btnUbicacion.setOnClickListener(btnUbicacion())
        btnTakePhoto.setOnClickListener(btnTakePhotoListener())
        btnSend.setOnClickListener(btnSend())

        txtCoordinates = findViewById(R.id.txtLocation)
    }

    private fun handlePermission(permission: String, requestCode: Int): Boolean {
        val permissionStatus = ContextCompat.checkSelfPermission(this@Reporte, permission)

        if (permissionStatus == PackageManager.PERMISSION_DENIED) {
            Log.i("tag", "we don't have the permission, wil ask for it")
            // If you are inside a fragment
            requestPermissions(arrayOf(permission), requestCode)
            return false
        } else {
            return true
        }
    }

    private fun btnCurrentLocationListener(): View.OnClickListener {
        return View.OnClickListener {
            if (handlePermission(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    FINE_LOCATION_PERMISSION_CODE
                )
            ) {
                Log.i("tag", "we have the permission, thanks")
                obtainCurrentLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun obtainCurrentLocation() {
        // this will do the latest one
        cancellationTokenSource = CancellationTokenSource()

        fusedLocationClient.getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        ).addOnCanceledListener {
            Log.i(
                "currentLocationListener",
                "The location update request has been cancelled"
            )
        }.addOnSuccessListener { location ->
            Log.i(
                "currentLocationListener",
                "Location obtained! ${location.toString()}"
            )
            //val sb = StringBuffer()

            sb.append("Latitude: ").append(location.latitude).append(", ")
                .append("Longitude: ").append(location.longitude).append(", ")
                .append("Time: ").append(location.time).append("\n")

            txtCoordinates.text = sb.toString()
        }.addOnFailureListener {
            Log.e("tag", "Location couldn't be found")
        }

        // TODO: use this anywhere you want to stop a single shot update:
        // cancellationTokenSource.cancel()
    }

    private fun btnUbicacion(): View.OnClickListener {
        return View.OnClickListener {
            Log.i("tag", "we enter")
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Reporte)
            builder.setTitle("Ubicación")

            // Set up the input
            val input = EditText(this@Reporte)
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setHint("Ingresa tu ubicación")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            // Set up the buttons
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // Here you get get input text from the Edittext
                m_Text = input.text.toString()
                Toast.makeText(this@Reporte, "Ubicación obtenida", Toast.LENGTH_SHORT).show()
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }
    }
    private fun btnSend(): View.OnClickListener {
        return View.OnClickListener {
            //Aquí se ponen los datos que se mandan a la base de datos, también se manda a la siguiente pantalla
            Toast.makeText(this@Reporte, "Datos enviados", Toast.LENGTH_SHORT).show()
            /*var m_Text: String = ""
            val sb = StringBuffer()*/
            val switchActivityIntent = Intent(applicationContext, PostReporteActivity::class.java)
            startActivity(switchActivityIntent);

        }
    }

    private fun btnTakePhotoListener(): View.OnClickListener {
        return View.OnClickListener {
            if (handlePermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)) {
                Log.i("tag", "we have the permission, thanks")
                takePhoto()
            }
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val stream = ByteArrayOutputStream()
                val data = result.data
                val bmp = data?.extras?.get("data") as Bitmap

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()

                // We have the image with new quality, we can do anything to it
                // For example, show it in an ImageView
                val bitmap = BitmapFactory.decodeByteArray(
                    byteArray, 0,
                    byteArray.size
                )
                Toast.makeText(this@Reporte, "Imagen tomada", Toast.LENGTH_SHORT).show()
                //imgPhoto.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this@Reporte, "Imagen no tomada", Toast.LENGTH_SHORT).show()
            }
        }

    // This will listen to the results of the permissions interaction
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkPermissionsResult(grantResults)
    }

    private fun checkPermissionsResult(grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Here you might want to continue with the pending operation or ask the user to do it again
            Snackbar.make(txtCoordinates, "Permission granted, thanks", Snackbar.LENGTH_SHORT)
                .show()

        } else {
            Toast.makeText(
                this@Reporte,
                "You will need to access the properties for this app and enable them manually",
                Toast.LENGTH_SHORT
            ).show()

            // Shows the app's config to select permissions
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", this@Reporte.packageName, null)
            intent.data = uri
            startActivity(intent)
        }
    }

}