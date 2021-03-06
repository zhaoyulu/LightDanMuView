package com.example.lightdanmu

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class DanMuSurfaceView : SurfaceView, DanMuViewContact.View, SurfaceHolder.Callback,Runnable {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mDanMuSurfacePresenter: DanMuViewContact.Presenter? = null
    //用于绘图的canvas
    private var mCanvas: Canvas? = null
    //子线程标志位
    private var mIsDrawing: Boolean = false
    private var mThread: Thread? = null


    init {
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSLUCENT)
        mDanMuSurfacePresenter = DanMuSurfacePresenter(this)
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        mDanMuSurfacePresenter?.setHeight(height)
        mDanMuSurfacePresenter?.setWith(width)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mIsDrawing = false
    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        mIsDrawing = true
        mThread = Thread(this)
        mThread?.start()
    }


    override fun run() {
        while (mIsDrawing){
            draw()
        }
    }

    override fun draw() {
        try {
            mCanvas = holder.lockCanvas()
            mCanvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            mDanMuSurfacePresenter?.draw(mCanvas)
        } catch (e: Exception) {

        } finally {
            if (null != mCanvas) {
                holder.unlockCanvasAndPost(mCanvas)
            }
        }
    }

    override fun addDanMu(danmu: DanMu) {
        mDanMuSurfacePresenter?.addDanMu(danmu)
    }
}
