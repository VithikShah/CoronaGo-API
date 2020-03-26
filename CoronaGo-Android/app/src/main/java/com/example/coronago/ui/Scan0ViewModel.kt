package com.example.coronago.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.coronago.data.network.responses.GetInfo0
import com.example.coronago.data.repositories.InfoRepository
import com.example.coronago.utils.ApiException
import com.example.coronago.utils.Coroutines
import com.example.coronago.utils.NoInternetExcepetion
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class Scan0ViewModel(
    val repository: InfoRepository,
    private val context: Context

) : ViewModel(
) {
    var image: ObservableField<Uri>? = ObservableField<Uri>()
    var onCameraClickListener: GetInfoSetupClickListener? = null
    var scan0Listener: Scan0Listener? = null


    fun updatePic(view: View) {
        onCameraClickListener?.onCameraButtonClick(view)
    }

    fun onclickNext(view: View) {
        scan0Listener?.onStarted()
        Coroutines.main {
            val unit = try {
                Log.v("image", image?.get().toString())
                val file = File(image?.get()?.path)
                val filePart = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    RequestBody.create(MediaType.parse("image/*"), file)
                )

                val imageResponse =
                    repository.getInfo0(
                        filePart,
                        0
                    )

                Log.v("after", "imageupload_scan0")

                imageResponse.let {
                    scan0Listener?.onSuccessInfo(it)
                    return@main
                }
            } catch (e: ApiException) {
                scan0Listener?.onFailure(e.message!!)
            } catch (e: NoInternetExcepetion) {
                scan0Listener?.onFailure(e.message!!)
            }
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

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Log.v("Uri", "error")
        }

    }

}