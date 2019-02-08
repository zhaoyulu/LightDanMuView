package com.example.lightdanmu

import android.graphics.Canvas
import android.graphics.Paint

class Item {
    @Volatile
    var mu: DanMu

    private val paint: Paint = Paint()
    @Volatile
    var muLength: Float = -1f
    @Volatile
    var x: Float = 0.0f
    @Volatile
    var y: Float = 0.0f
    var speed : Float = 3.0f

    constructor(mu: DanMu) {
        this.mu = mu
    }

    fun getPaint():Paint{
        return paint
    }

    //需要功能完善
    private fun move(speed: Float) {
        x  -=  speed
    }

    fun draw(canvas: Canvas?) {
        move(this.speed)
        canvas?.drawText(mu.getInfo(), x, y, paint)
    }

}