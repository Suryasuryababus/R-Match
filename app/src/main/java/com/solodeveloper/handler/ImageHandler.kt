package com.solodeveloper.handler

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.annotation.RequiresApi


class ImageHandler {
    companion object{
        private const val BITMAP_SCALE = 0.4f
        private const val BLUR_RADIUS = 3f
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun blur(context: Context?, image: Bitmap): Bitmap? {
            val width = Math.round(image.width * BITMAP_SCALE).toInt()
            val height = Math.round(image.height * BITMAP_SCALE).toInt()
            val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
            val outputBitmap = Bitmap.createBitmap(inputBitmap)
            val rs = RenderScript.create(context)
            val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
            theIntrinsic.setRadius(BLUR_RADIUS)
            theIntrinsic.setInput(tmpIn)
            theIntrinsic.forEach(tmpOut)
            tmpOut.copyTo(outputBitmap)
            return outputBitmap
        }
    }
}