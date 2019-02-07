package com.example.lightdanmu

import android.graphics.Canvas
import android.view.Choreographer

class DanMuSurfacePresenter : DanMuViewContact.Presenter{

    private var mView:DanMuViewContact.View
    private lateinit var frameCallback: Choreographer.FrameCallback

    private var mWith : Int = 0
    private var mHight : Int = 0

    override fun  setWith(int: Int){mWith = int}
    override fun  setHeight(int: Int){mHight = int}

    constructor(view : DanMuViewContact.View){
        mView = view

    }

    override fun draw(canvas: Canvas?) {

    }

    private fun start() {
        frameCallback = FrameCallback()
        Choreographer.getInstance().postFrameCallback(frameCallback)
    }

    private inner class FrameCallback internal constructor() : Choreographer.FrameCallback {

        private val mChoreographer: Choreographer = Choreographer.getInstance()

        override fun doFrame(frameTimeNanos: Long) {
            if (frameCallback != null)
                mChoreographer.postFrameCallback(frameCallback)
        }
    }

}