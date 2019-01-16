package com.example.lightdanmu

class DanMu {

    private val mDefaultColor: Int = -65535
    private val mDefaultTextSize: Float = 55.0F

    @Volatile
    private var mInfo: String;
    @Volatile
    private var mTextColor: Int;
    @Volatile
    private var mTextSize: Float;

    fun getTextColor(): Int {
        return mTextColor
    }

    fun getTextSize(): Float {
        return mTextSize
    }

    fun getInfo(): String {
        return mInfo
    }

    constructor(string: String) {
        this.mInfo = string
        this.mTextColor = mDefaultColor
        this.mTextSize = mDefaultTextSize
    }

    constructor(string: String, textSize: Float, textColor: Int) {
        this.mInfo = string
        this.mTextColor = textColor
        this.mTextSize = textSize
    }

}