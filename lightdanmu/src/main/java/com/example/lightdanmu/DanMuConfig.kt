package com.example.lightdanmu

import android.graphics.Path
import android.R.attr.name


class DanMuConfig {

    private var mMaxLine: Int = 0

    private var mRetentionTime: Int = 0

    private var mEmptyArea: Path? = null

    constructor(builder: Builder) {
        mMaxLine = builder.mMaxLine
        mRetentionTime = builder.mRetentionTime
        mEmptyArea = builder.mEmptyArea
    }

    constructor() {
        mMaxLine = 10
        mRetentionTime = 10
        mEmptyArea = null
    }

    class Builder {
        var mMaxLine: Int = 0

        var mRetentionTime: Int = 0

        var mEmptyArea: Path? = null

        fun setMaxLine(maxLine: Int): Builder {
            this.mMaxLine = maxLine
            return this
        }

        fun setRetentionTime(retentionTime: Int): Builder {
            this.mRetentionTime = retentionTime
            return this
        }

        fun setPath(emptyArea: Path): Builder {
            this.mEmptyArea = emptyArea
            return this
        }

        fun build(): DanMuConfig {
            return DanMuConfig(this);
        }
    }

}