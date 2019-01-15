package com.example.lightdanmu

import android.graphics.Canvas

interface DanMuViewContact {

    interface View{
        fun draw()
    }

    interface Presenter{
        fun draw(canvas: Canvas)

    }

}