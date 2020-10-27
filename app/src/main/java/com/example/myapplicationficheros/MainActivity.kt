package com.example.myapplicationficheros

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textviewhello)
        var nombreFichero = "ficheroInterno.txt"
        var texto = "Texto a escribir"

        // Escritura interna
        var fileOutput = openFileOutput(nombreFichero, Context.MODE_PRIVATE)
        fileOutput.write(texto.toByteArray())
        fileOutput.close()

        // var outputStreamWriter = OutputStreamWriter(fileOutput)
        // outputStreamWriter.write(texto)

        // Lectura interna
        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombreFichero)))
        var textoLeido = bufferedReader.readLine()
        textView.text = textoLeido
        bufferedReader.close()

        //Lectura de fichero Recurso
        var bufferedReaderRecurso = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.fichero_recurso)))
        textoLeido = bufferedReaderRecurso.readLine()
        textView.text = textoLeido
        bufferedReaderRecurso.close()


        //Escritura ficherno externo
        var ruta:File? = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        var file = File(ruta!!.absolutePath, "MIDOCUMENTO.txt")

        var bufferedWriter = BufferedWriter(OutputStreamWriter(FileOutputStream(file)))
        bufferedWriter.write("Escribiendo en fichero externo")
        bufferedWriter.close()

        var bufferedReaderExterno = BufferedReader(InputStreamReader(FileInputStream(file)))
        var contenidoExterno = bufferedReaderExterno.readLine()
        textView.text = contenidoExterno
        bufferedReaderExterno.close()
    }
}