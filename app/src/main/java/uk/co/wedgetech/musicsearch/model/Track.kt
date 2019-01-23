package uk.co.wedgetech.musicsearch.model

import java.lang.StringBuilder

data class Track(val name:String, val duration:Int) {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(name)
        sb.append(" (")
        sb.append(duration/60)
        sb.append(":")
        sb.append((duration%60).toString().padStart(2, '0'))
        sb.append(")")
        return sb.toString()
    }
}