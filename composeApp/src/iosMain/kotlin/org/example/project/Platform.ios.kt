package org.example.project

class IOSPlatform: Platform {
    override val name: String = "IOS"
}

// iOS specific implementation of the Platform interface
actual fun getPlatform(): Platform = IOSPlatform()