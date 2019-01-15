package com.example.lightdanmu

import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.ConcurrentLinkedQueue

class DanMuPool private constructor(){

//    companion object {
//        val instance: SingletonDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            SingletonDemo() }
//    }


    private lateinit var mConcurrentLinkedQueue: ConcurrentLinkedQueue<DanMu>

}