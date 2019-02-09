package com.example.lightdanmu

import android.graphics.Canvas
import android.util.Log
import android.util.SparseArray
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue


class DanMuSurfacePresenter : DanMuViewContact.Presenter{

    private var mView:DanMuViewContact.View
    //仅外部线程可能访问
    private var mWaitingPool:ConcurrentLinkedQueue<Item>
    //仅内部线程可能访问
    private var mShowingPool:LinkedList<Item>
    private var mWith : Int = 0
    private var mHight : Int = 0
    private lateinit var mEmptyLine:SparseArray<Item?>

    //需要build模式的
    private val maxLine = 10
    private val mMinSplit = 20
    private val mEachHigh = 50

    constructor(view : DanMuViewContact.View){
        mView = view
        mWaitingPool = ConcurrentLinkedQueue()
        mShowingPool = LinkedList()
    }

    override fun  setWith(int: Int){
        mWith = int
        mEmptyLine = SparseArray()
        for (i in 0..maxLine){
            mEmptyLine.put(i*mEachHigh+mEachHigh,null)
        }
    }
    override fun  setHeight(int: Int){mHight = int}

    override fun draw(canvas: Canvas?) {
        for (item:Item in mShowingPool){
            item.draw(canvas)
            if (item.x + item.muLength <=0){
                mShowingPool.remove(item)
                ItemPool.intance.returnDanMu(item)
            }
        }
        trigger()
    }

    private fun trigger(){
        for (i in 0 until mEmptyLine.size()) {
            val key = mEmptyLine.keyAt(i)
            val item = mEmptyLine.get(key)
            if((item == null || item.muLength + item.x +mMinSplit < mWith)
                    && !mWaitingPool.isEmpty()){
                val tempItem = mWaitingPool.poll()
                tempItem.x = mWith.toFloat()
                tempItem.y = key.toFloat()

                mEmptyLine.put(key,tempItem)
                mShowingPool.add(tempItem)
                break
            }
        }
        Log.e("test_mShowingPool",mShowingPool.size.toString())
        Log.e("test_mWaitingPool",mWaitingPool.size.toString())

    }

    override fun addDanMu(danmu: DanMu) {
        var item: Item = ItemPool.intance.borrowItem(danmu) ?: return
        item.getPaint().color = danmu.getTextColor()
        item.getPaint().textSize = danmu.getTextSize()
        item.muLength = item.getPaint().measureText(item.mu.getInfo())
        mWaitingPool?.add(item)
    }

    override fun realease() {
        mWaitingPool.clear()
        mShowingPool.clear()
        ItemPool.intance.release()
    }

}