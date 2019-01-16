package com.qiyi.kotlinqimu

import android.graphics.Canvas
import android.graphics.Paint

class Item {

    var mu: Mu
    private var paint: Paint
    var muLength: Int = -1

    var x: Float = 0.0f
    var y: Float = 100.0f
    var speed : Float = 0.0f
    fun getPaint(): Paint {
        return paint
    }


    constructor(mu: Mu) {
        this.mu = mu
        this.paint = Paint()
    }

    //需要功能完善
    private fun move(speed: Float) {
        x = x - speed
    }

    fun draw(canvas: Canvas) {
        move(this.speed)
        canvas.drawText(mu.getInfo(), this.x, y, paint)
    }

}