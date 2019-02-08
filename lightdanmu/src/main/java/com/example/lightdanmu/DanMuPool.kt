package com.example.lightdanmu

import java.util.concurrent.ConcurrentLinkedQueue


class DanMuPool private constructor() {

    companion object {
        val intance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { DanMuPool() }
    }

    private var mConcurrentLinkedQueue: ConcurrentLinkedQueue<DanMu>


    init {
        mConcurrentLinkedQueue = ConcurrentLinkedQueue()
    }

    protected fun borrowDanMu():DanMu{
        if(mConcurrentLinkedQueue.size != 0){
            return mConcurrentLinkedQueue.peek()
        }
        return DanMu("")
    }

    protected fun returnDanMu(danMu: DanMu){
        mConcurrentLinkedQueue.plus(danMu)
    }

}