package com.example.lightdanmu

import java.util.concurrent.ConcurrentLinkedQueue


class ItemPool private constructor() {

    companion object {
        val intance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ItemPool() }
    }

    private val mMaxSize = 150

    @Volatile
    private var mItemNum = 0

    private val mConcurrentLinkedQueue: ConcurrentLinkedQueue<Item> = ConcurrentLinkedQueue()

    fun borrowItem(danMu: DanMu): Item? {
        if(mItemNum > mMaxSize){
            return null
        }
        mItemNum--
        if(mConcurrentLinkedQueue.size != 0){
            val tempItem =  mConcurrentLinkedQueue.peek()
            tempItem.mu = danMu
            return tempItem
        }
        return Item(danMu)
    }

    fun returnDanMu(item: Item){
        mConcurrentLinkedQueue?.add(item)
        item.x = 0.0f
        item.y = 0.0f
        mItemNum++
    }

    fun release(){
        mConcurrentLinkedQueue?.clear()
    }

}