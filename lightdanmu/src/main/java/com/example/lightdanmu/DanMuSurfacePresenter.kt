package com.example.lightdanmu

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class DanMuSurfacePresenter : DanMuViewContact.Presenter{

    private var mView:DanMuViewContact.View

    private var mWith : Int = 0
    private var mHight : Int = 0

    override fun  setWith(int: Int){mWith = int}
    override fun  setHeight(int: Int){mHight = int}

    constructor(view : DanMuViewContact.View){
        mView = view

    }

    override fun draw(canvas: Canvas?) {
        var paint =Paint()
        paint.color = Color.BLUE
        canvas?.drawText("aaaaaaaaaa",200f, 100f,paint)
    }
}