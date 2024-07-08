package com.klyukvin.data.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import com.klyukvin.domain.usecase.CompressImageUseCase
import java.io.ByteArrayOutputStream

class CompressImageUseCaseImpl(
    private val context: Context
) : CompressImageUseCase {

    override suspend fun invoke(imageString: String): ByteArray {
        val request = ImageRequest.Builder(context).data(imageString).build()
        val drawable = ImageLoader.Builder(context).crossfade(true).build().execute(request).drawable

        val bitmap = (drawable as BitmapDrawable).bitmap
        val byteArray = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESS_QUALITY, byteArray)
        return byteArray.toByteArray()
    }

    private companion object {

        const val COMPRESS_QUALITY = 70
    }
}