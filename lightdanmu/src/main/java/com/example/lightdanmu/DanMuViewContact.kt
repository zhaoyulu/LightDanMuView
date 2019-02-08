package com.example.lightdanmu

import android.graphics.Canvas

interface DanMuViewContact {

    interface View{
        fun draw()
        fun addDanMu(danmu:DanMu)
    }

    interface Presenter{
        fun draw(canvas: Canvas?)
        fun  setWith(int: Int)
        fun  setHeight(int: Int)
        fun addDanMu(danmu:DanMu)
        fun realease()
    }

}