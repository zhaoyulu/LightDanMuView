package com.qiyi.kotlinqimu

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Choreographer
import java.util.LinkedHashSet
import java.util.concurrent.ConcurrentLinkedQueue



class World {

    private lateinit var qiMuConfig: QiMuConfig
    private var mShowingItems: LinkedHashSet<Item>;
    private var mWaitingItems: ConcurrentLinkedQueue<Item>;
    //private lateinit var frameCallback: FrameCallback
    private var qiMuView:QiMuView;

    private var path:Path

    private var with:Int=0
    private var high:Int=0
    private var x:Float=0.0F
    private var endX:Float=0.0F
    private var y:Float=0.0F
    private var endY:Float=0.0F
    private var path2:Path

    private var markTime:Int=0
    private lateinit var mValueAnimator: ValueAnimator
    private lateinit var LineMap:HashMap<Int,Item>;

    constructor(qiMuView:QiMuView) {
        this.qiMuView=qiMuView;
        mShowingItems = LinkedHashSet()
        mWaitingItems = ConcurrentLinkedQueue()
        initAnimator()
        path= Path()
        path2=Path()

    }

    fun initAnimator(){
        mValueAnimator = ValueAnimator.ofFloat(0.toFloat())
        mValueAnimator.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
            markTime++
            if(markTime%2==0) return@AnimatorUpdateListener
            inToShowQueue()
            qiMuView.invalidate()
        })
        mValueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animator: Animator) {
                mValueAnimator.start()
            }
        })
    }

    fun setConfig(qiMuConfig: QiMuConfig) {
        this.qiMuConfig = qiMuConfig
        LineMap=HashMap()
        qiMuConfig.eachHigh = high/qiMuConfig.mLines
        for (i: Int in 1..qiMuConfig.mLines) {
            var item = Item(Mu(""))
            item.x=with-qiMuConfig.mGap.toFloat()
            LineMap.put((qiMuConfig.eachHigh*i-0.5*qiMuConfig.eachHigh).toInt(),item)
        }
        for (item:Item in mShowingItems){
            item.getPaint().alpha=qiMuConfig.alpha
            item.getPaint().textSize=qiMuConfig.textSize.toFloat()
            item.speed = (with+item.muLength*2)/40/qiMuConfig.RetentionTime
            item.muLength=utils.getTextWidth(item.getPaint(),item.mu.getInfo())
        }
        for (item:Item in mWaitingItems){
            item.getPaint().alpha=qiMuConfig.alpha
            item.getPaint().textSize=qiMuConfig.textSize.toFloat()
            item.speed = (with+item.muLength*2)/40/qiMuConfig.RetentionTime
            item.muLength=utils.getTextWidth(item.getPaint(),item.mu.getInfo())
        }
    }

    fun setFreeArea(freeArea: FreeArea){
        path2=Path()
        path2.addCircle(freeArea.x.toFloat(),freeArea.y.toFloat(),freeArea.size.toFloat(),Path.Direction.CCW)
        path=path2
    }

    fun Draw(canvas: Canvas) {
        //canvas.clipPath(path,Region.Op.DIFFERENCE)
        val iterator = mShowingItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            item.draw(canvas)
            if(judgeRemove(item)){
                ItemPool.returnItem(item)
                iterator.remove()
            }
        }
    }

    fun judgeRemove(item: Item):Boolean{
        if(item.x+item.muLength<0)return true
        return false
    }

    fun addMu(mu: Mu) {
        if(mWaitingItems.size>=qiMuConfig.waitingSize||qiMuConfig.changingMark)return
        var item = ItemPool.borrowItem(mu)
        item.getPaint().alpha=qiMuConfig.alpha
        item.getPaint().textSize=qiMuConfig.textSize.toFloat()
        item.getPaint().color=mu.getTextColor()
        item.muLength=utils.getTextWidth(item.getPaint(),item.mu.getInfo())

        item.speed = (with+item.muLength*2)/40/qiMuConfig.RetentionTime

        mWaitingItems.add(item)
    }

    fun setSize(with:Int, high:Int){
        this.with=with
        this.high=high
        x=qiMuView.x
        y=qiMuView.y
        endX=x+with
        endY=y+high
        start()
    }

    fun inToShowQueue(){
        if(qiMuConfig.changingMark)return
        for(temp in LineMap){
            if(temp.value.x+temp.value.muLength+qiMuConfig.mGap<with&&
                    mWaitingItems.size>0&&
                    mShowingItems.size<=qiMuConfig.showSize){
                var tempItem =mWaitingItems.poll()
                tempItem.y= temp.key.toFloat()
                tempItem.x=with.toFloat()
                temp.setValue(tempItem)
                mShowingItems.add(tempItem)
                break
            }
        }
    }

    private fun start(){
        mValueAnimator?.start()
    }

//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
//    private fun start() {
//        frameCallback = FrameCallback()
//        Choreographer.getInstance().postFrameCallback(frameCallback)
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private inner class FrameCallback internal constructor() : Choreographer.FrameCallback {
//
//        private val mChoreographer: Choreographer
//
//        init {
//            mChoreographer = Choreographer.getInstance()
//        }
//        override fun doFrame(frameTimeNanos: Long) {
//            inToShowQueue()
//            markTime++
//            if(markTime*3!=0)qiMuView.invalidate()
//            if (frameCallback != null)
//                mChoreographer.postFrameCallback(frameCallback)
//        }
//    }

}