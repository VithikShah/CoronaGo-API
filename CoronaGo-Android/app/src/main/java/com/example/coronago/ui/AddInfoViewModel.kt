package com.example.coronago.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.data.repositories.PaymentRepository
import com.example.coronago.utils.ApiException
import com.example.coronago.utils.Coroutines
import com.example.coronago.utils.NoInternetExcepetion
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddInfoViewModel(
    val repository: InfoRepository,
    private val activity: Activity,
    val type: Int

) : ViewModel(
) {
    var image: ObservableField<Uri>? = ObservableField<Uri>()
    var status: String? = null
    var medicines: String? = null

    var status_f: Int? = 0

    var onCameraClickListener: GetInfoSetupClickListener? = null
    var addInfoListener: AddInfoListener? = null

    fun updatePic(view: View) {
        onCameraClickListener?.onCameraButtonClick(view)
    }

    fun onclickNext(view: View) {
        addInfoListener?.onStarted()
        Coroutines.main {
            val unit = try {
                Log.v("image", image?.get().toString())
                val file = File(image?.get()?.path)
                Log.v("after sttau : ", status)
                val filePart = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    RequestBody.create(MediaType.parse("image/*"), file)
                )

                if (status.equals("n")) {
                    status_f = 0
                }
                if (status.equals("q")) {
                    status_f = 1
                }
                if (status.equals("p")) {
                    status_f = 2
                }

                val imageResponse =
                    repository.addInfo(
                        filePart,
                        medicines,
                        status_f,
                        type
                    )//update this

                Log.v("after", "amount transaction")


                imageResponse.let {
                    addInfoListener?.onSuccessInfo(it)
                    return@main
                }
            } catch (e: ApiException) {
                addInfoListener?.onFailure(e.message!!)
            } catch (e: NoInternetExcepetion) {
                addInfoListener?.onFailure(e.message!!)

            }
//
        }


    }


    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            image?.set(fileUri)
            image?.notifyChange()
            Log.v("Uri", image.toString())

            //You can get File object from intent
//            val file:File = ImagePicker.getFile(data)
//
//            //You can also get File Path from intent
//            val filePath:String = ImagePicker.getFilePath(data)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Log.v("Uri", "error")
//            authListener?.onFailure(ImagePicker.getError(data))

        }

    }
}