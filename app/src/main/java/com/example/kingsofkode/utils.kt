package com.example.kingsofkode

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class utils {
    companion object {
        fun getDrawableFromString(
            context: Context,
            drawableName: String,
            resources: Resources
        ): Drawable? {
            return context.let {
                ContextCompat.getDrawable(
                    it, resources.getIdentifier(
                        drawableName,
                        "drawable", context.packageName
                    )
                )
            }
        }
    }
}