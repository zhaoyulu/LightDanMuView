package com.example.lightdanmu

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue


class ItemPool private constructor() {

    companion object {
        val intance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ItemPool() }
    }

    private val mMaxSize = 150

    @Volatile
    private var mItemNum = 150

    private val mConcurrentLinkedQueue: ConcurrentLinkedQueue<Item> = ConcurrentLinkedQueue()

    fun borrowItem(danMu: DanMu): Item? {
        if (mItemNum<0){
            return null
        }
        mItemNum--
        Log.e("test_pool",mConcurrentLinkedQueue.size.toString())

        if(mConcurrentLinkedQueue.size != 0){
            val tempItem =  mConcurrentLinkedQueue.poll()
            tempItem.mu = danMu
            tempItem.x = 0.0f
            tempItem.y = 0.0f
            return tempItem
        }
        return Item(danMu)
    }

    fun returnDanMu(item: Item){
        mConcurrentLinkedQueue?.add(item)
        mItemNum++
    }

    fun release(){
        mConcurrentLinkedQueue?.clear()
    }

}