package com.example.lightdanmu

import android.graphics.Canvas
import android.view.Choreographer

class DanMuSurfacePresenter : DanMuViewContact.Presenter{

    private var mView:DanMuViewContact.View
    private lateinit var frameCallback: Choreographer.FrameCallback

    constructor(view : DanMuViewContact.View){
        mView = view

    }

    override fun draw(canvas: Canvas) {

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