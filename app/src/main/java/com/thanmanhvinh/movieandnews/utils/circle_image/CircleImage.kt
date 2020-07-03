package com.thanmanhvinh.movieandnews.utils.circle_image

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet

class CircleImage : androidx.appcompat.widget.AppCompatImageView {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context!!, attrs, defStyle)

    override fun onDraw(canvas: Canvas) {
        val drawable = drawable ?: return
        if (width == 0 || height == 0) {
            return
        }
        val b = (drawable as BitmapDrawable).bitmap
        val bitmap = b.copy(Bitmap.Config.ARGB_8888, true)
        val w = width
        val roundBitmap =
            getCroppedBitmap(
                bitmap,
                w
            )
        canvas.drawBitmap(roundBitmap, 0f, 0f, null)
    }

    companion object {
        fun getCroppedBitmap(bmp: Bitmap, radius: Int): Bitmap {
            val snmp: Bitmap = if (bmp.width != radius || bmp.height != radius) Bitmap.createScaledBitmap(
                bmp,
                radius,
                radius,
                false
            ) else bmp
            val output = Bitmap.createBitmap(
                snmp.width, snmp.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            val paint = Paint()
            val rect =
                Rect(0, 0, snmp.width, snmp.height)
            paint.isAntiAlias = true
            paint.isFilterBitmap = true
            paint.isDither = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = Color.parseColor("#BAB399")
            canvas.drawCircle(
                snmp.width / 2 + 0.7f,
                snmp.height / 2 + 0.7f, snmp.width / 2 + 0.1f, paint
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(snmp, rect, rect, paint)
            return output
        }
    }
}