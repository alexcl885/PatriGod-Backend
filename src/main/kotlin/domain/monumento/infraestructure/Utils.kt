package domain.monumento.infraestructure

import ktor.ApplicationContext
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.imageio.ImageIO

class Utils {
    companion object{
        fun createBase64ToImg(img: String?, dni: String): String? {
            if (img.isNullOrEmpty()) {
                println("Error: La imagen en Base64 es nula o vacía")
                return null
            }

            val regex = "data:(image/[^;]+);base64,(.+)".toRegex()
            val result = regex.find(img)

            if (result == null) {
                println("Error: No se encontró un formato válido en la imagen Base64")
                return null
            }

            val type = result.groupValues[1]
            var ext = type.split("/")[1]
            val body = result.groupValues[2]

            if (ext !in listOf("jpg", "jpeg", "png", "gif")) {
                println("Error: Formato de imagen no permitido ($ext)")
                return null
            }

            try {
                if (ext == "jpg") ext = "jpeg"

                val imgBytes = Base64.getDecoder().decode(body)
                val inputStream = ByteArrayInputStream(imgBytes)
                val bufferImage: BufferedImage = ImageIO.read(inputStream)

                val path = "uploads/images/$dni"  // Ruta donde guardar imágenes (cámbiala si es necesario)
                val dir = File(path)
                if (!dir.exists()) dir.mkdirs()

                val nameFile = "${dni}_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}.$ext"
                val fileImage = File("$path/$nameFile")

                ImageIO.write(bufferImage, ext, fileImage)

                return nameFile  // Devuelve el nombre del archivo guardado
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error al convertir Base64 a imagen")
                return null
            }
        }


        fun getNameFileBase64(img: String){

        }

        //todo
        fun deleteImage(dni: String, name: String):Boolean{
            try{
                val path = "${ApplicationContext.context.environment.config.property("ktor.path.images").getString()}/$dni"
                val img = File(path, name)
                return if (img.exists()){
                    img.delete()
                    true
                }
                else
                    false

            }catch (e: Exception){
                e.printStackTrace()
                return false
            }
        }

        fun createDir(dni: String) :Boolean{
            try{
                val path = ApplicationContext.context.environment.config.property("ktor.path.images").getString()
                val dir = File(path, dni)
                return if (!dir.exists()){
                    val created = dir.mkdirs()
                    if (created)
                        true
                    else
                        false
                }
                else
                    false
            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
        }

        fun deleteDirectory(dni: String) : Boolean{
            try{
                val path = ApplicationContext.context.environment.config.property("ktor.path.images").getString()+"/$dni"
                val dir = File(path)
                if (dir.exists()){
                    return dir.deleteRecursively()
                }

            }catch (e:Exception){
                e.printStackTrace()
                return false
            }
            return false
        }
    }
}