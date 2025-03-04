package org.example.project

class AndroidPlatform : Platform {
    override val name: String = "Android"
}

// Android specific implementation of the Platform interface
actual fun getPlatform(): Platform = AndroidPlatform()