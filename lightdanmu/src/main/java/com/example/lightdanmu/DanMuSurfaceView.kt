package com.example.lightdanmu

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class DanMuSurfaceView : SurfaceView, DanMuViewContact.View, SurfaceHolder.Callback {

    constructor(context: Context) : this(context, null) {}

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    lateinit var mDanMuSurfacePresenter: DanMuSurfacePresenter

    init {
        mDanMuSurfacePresenter = DanMuSurfacePresenter(this)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
    }

    override fun draw() {
        var canvas: Canvas = holder.lockCanvas()
        mDanMuSurfacePresenter.draw(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }
}
